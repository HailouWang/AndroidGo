package com.github.hailouwang.demosforapi.widget.da.monitor;

import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 曝光数据采集：页面 + 元素。
 * - 页面的曝光数据采集，也就是常说的 PV 事件
 * - 元素曝光：
 * - 非列表元素曝光：标题栏等独立于页面列表元素之外的页面组成元素
 * - 列表元素的曝光：本次的重点，自动化采集
 * <p>
 * 数据曝光采集的时机
 * 1、屏幕滑动时，从滑动中到停止滑动时，采集当前展示给用户的元素，发生曝光
 * - 自动停止滚动
 * - 手动按下等手动触发的停止
 * - 无数据导致的停止
 * 2、屏幕未滑动时：
 * - 页面从不可见到可见：封装成 visibleShowToUser
 * - 从后台回前台
 * - ViewPager 来回切换，Fragment#setUserVisibleHint
 * <p>
 * 数据曝光采集机制
 * 一、数据发生变更
 * - 1、列表数据首次加载完成，显示在屏幕内，展示给用户的元素，发生曝光。
 * - 实现1：在界面展示给用户的时候，去做埋点数据采集。弊端：数据一般由异步来完成采集，此时可能还没有返回，RecyclerView 没有还没有完成渲染
 * - 实现2：为了兼容上述场景的埋点数据采集，需要监听视图树变化，当视图树首次加载完成时，触发埋点数据采集。目前采用：addOnGlobalLayoutListener，但此方法会回调多次，也是一个 todo
 * - 2、列表数据，下拉刷新完成，显示在屏幕内，展示给用户的元素，发生曝光。
 * - 此时，不论条目的内容是否发生了变化、不论之前是否曝光过
 * - 3、用户上滑，触发加载更多，此时，展示给用户的数据部分，发生曝光
 * - 已曝光的部分不再发生曝光
 * <p>
 * 二、数据未发生变更
 * 1、页面从不可见到可见
 * - 由于 埋点数据曝光，存在曝光状态监测，即：如果已经曝光过，再次触发埋点数据采集时，会监测是否曝光过，如果已经曝光过，那么，不再触发曝光。
 * - 实现1：每次，从不可见到可见，调用 埋点数据采集的强制刷新方法。
 * - 实现2：每次，从可见到不可见，调用 #resetStatShowPool，将item曝光状态，从true->false；从不可见到可见时，触发一次普通的数据曝光即可。
 * <p>
 * 2、页面元素Item，上下滑动时，元素由不可见到可见，需要再次曝光，一直处于可见的元素，不发生曝光。
 * - 实现步骤1：通过RecyclerView#addOnChildAttachStateChangeListener 来监听 attach/detach 来将列表Item 加入或者移除曝光池
 * - 实现步骤2：当停止刷新时，触发列表数据曝光采集
 * <p>
 * 三、刷新Api
 * - 1、notifiyDataChange
 * - 2、notifyItemChange
 * <p>
 * 四、注意事项：
 * 1、是否应该曝光
 *  - 1、item内容没变，但是索引 index 发生了改变，此时是否应该曝光？例如：元素上部插入了运营位
 *  - 2、AB实验等绑定具体业务场景，发生了变化，当前元素Item是否要曝光？
 * 2、注意给RecyclerView.Adapter 添加观察者的时机，RecyclerView.Adapter观察者遍历是有顺序，RecyclerView.Adapter 是后续遍历
 *
 * 五、logcat 日志命令
 * adb logcat -v time -s hlwang
 *
 * 04-09 10:28:10.173 D/DataStatManager(12901): RecyclerViewDataStatManager performStatRecyclerView first = 0, last = 14, forceShow = false
 * 04-09 10:28:10.174 D/hlwang  (12901): DataAnalysisRecyclerViewFragment onRecyclerViewStatView position : 0, content : 全部更新数据 - item : 1, size : 200
 * 04-09 10:28:10.174 D/hlwang  (12901): DataAnalysisRecyclerViewFragment onRecyclerViewStatView position : 1, content : 全部更新数据 - item : 2, size : 200
 * 04-09 10:28:10.175 D/hlwang  (12901): DataAnalysisRecyclerViewFragment onRecyclerViewStatView position : 2, content : 全部更新数据 - item : 3, size : 200
 * 04-09 10:28:10.175 D/hlwang  (12901): DataAnalysisRecyclerViewFragment onRecyclerViewStatView position : 3, content : 全部更新数据 - item : 4, size : 200
 * 04-09 10:28:10.175 D/hlwang  (12901): DataAnalysisRecyclerViewFragment onRecyclerViewStatView position : 4, content : 全部更新数据 - item : 5, size : 200
 * 04-09 10:28:10.175 D/hlwang  (12901): DataAnalysisRecyclerViewFragment onRecyclerViewStatView position : 5, content : 全部更新数据 - item : 6, size : 200
 * 04-09 10:28:10.175 D/hlwang  (12901): DataAnalysisRecyclerViewFragment onRecyclerViewStatView position : 6, content : 全部更新数据 - item : 7, size : 200
 * 04-09 10:28:10.176 D/hlwang  (12901): DataAnalysisRecyclerViewFragment onRecyclerViewStatView position : 7, content : 全部更新数据 - item : 8, size : 200
 * 04-09 10:28:10.176 D/hlwang  (12901): DataAnalysisRecyclerViewFragment onRecyclerViewStatView position : 8, content : 全部更新数据 - item : 9, size : 200
 * 04-09 10:28:10.176 D/hlwang  (12901): DataAnalysisRecyclerViewFragment onRecyclerViewStatView position : 9, content : 全部更新数据 - item : 10, size : 200
 * 04-09 10:28:10.176 D/hlwang  (12901): DataAnalysisRecyclerViewFragment onRecyclerViewStatView position : 10, content : 全部更新数据 - item : 11, size : 200
 * 04-09 10:28:10.177 D/hlwang  (12901): DataAnalysisRecyclerViewFragment onRecyclerViewStatView position : 11, content : 全部更新数据 - item : 12, size : 200
 * 04-09 10:28:10.177 D/hlwang  (12901): DataAnalysisRecyclerViewFragment onRecyclerViewStatView position : 12, content : 全部更新数据 - item : 13, size : 200
 * 04-09 10:28:10.177 D/hlwang  (12901): DataAnalysisRecyclerViewFragment onRecyclerViewStatView position : 13, content : 全部更新数据 - item : 14, size : 200
 * 04-09 10:28:10.178 D/hlwang  (12901): DataAnalysisRecyclerViewFragment onRecyclerViewStatView position : 14, content : 全部更新数据 - item : 15, size : 200
 *
 * RecyclerViewDataStatManager 工作日志
 * adb logcatd -v time -s DataStatManager
 *
 * 04-09 10:28:09.186 D/DataStatManager(12901): RecyclerViewDataStatManager onChildViewDetachedFromWindow 剥离 position = -1, viewHolder = TextRecyclerViewItem{b8517e position=1 id=-1, oldPos=-1, pLpos:-1 invalid update undefined adapter position}
 * 04-09 10:28:09.187 D/DataStatManager(12901): RecyclerViewDataStatManager onChildViewDetachedFromWindow 剥离 position = -1, viewHolder = TextRecyclerViewItem{644a901 position=0 id=-1, oldPos=-1, pLpos:-1 invalid update undefined adapter position}
 * 04-09 10:28:09.187 D/DataStatManager(12901): RecyclerViewDataStatManager onChildViewAttachedToWindow 附加 position = 0, viewHolder = TextRecyclerViewItem{ae9398f position=0 id=-1, oldPos=-1, pLpos:-1}
 * 04-09 10:28:09.191 D/DataStatManager(12901): RecyclerViewDataStatManager onChildViewAttachedToWindow 附加 position = 1, viewHolder = TextRecyclerViewItem{e37fab4 position=1 id=-1, oldPos=-1, pLpos:-1}
 * 04-09 10:28:09.193 D/DataStatManager(12901): RecyclerViewDataStatManager onChildViewAttachedToWindow 附加 position = 2, viewHolder = TextRecyclerViewItem{faaf495 position=2 id=-1, oldPos=-1, pLpos:-1}
 *
 */
public class RecyclerViewDataStatManager implements RecyclerView.OnChildAttachStateChangeListener {

    private final boolean enableLogs = true;
    private static final String TAG = "DataStatManager";

    protected RecyclerView recyclerView;

    private ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            if (enableLogs) {
                Log.d(TAG, "RecyclerViewDataStatManager addOnGlobalLayoutListener onGlobalLayout ... globalLayoutListenerWorked : " + globalLayoutListenerWorked);
            }

            if (!globalLayoutListenerWorked) {
                performStatRecyclerView(false);
                globalLayoutListenerWorked = true;
            } else {
                if (recyclerView != null && recyclerView.getViewTreeObserver() != null) {
                    recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
                }
            }
        }
    };

    protected RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.AdapterDataObserver mAdapterDataObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            if (enableLogs) {
                Log.d(TAG, "RecyclerViewDataStatManager AdapterDataObserver onChanged");
            }
            // 数据全部发生变化时，触发
//            performStatRecyclerView(false, TimeUnit.SECONDS.toMillis(1));
            performStatRecyclerView(false);
//            globalLayoutListenerWorked = false;
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            super.onItemRangeChanged(positionStart, itemCount);
            if (enableLogs) {
                Log.d(TAG, "RecyclerViewDataStatManager AdapterDataObserver onItemRangeChanged positionStart : " + positionStart
                        + ", itemCount : " + itemCount);
            }

            // todo
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
            super.onItemRangeChanged(positionStart, itemCount, payload);
            if (enableLogs) {
                Log.d(TAG, "RecyclerViewDataStatManager AdapterDataObserver onItemRangeChanged positionStart : " + positionStart
                        + ", itemCount : " + itemCount
                        + ", payload:" + payload);
            }

            // todo
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            if (enableLogs) {
                Log.d(TAG, "RecyclerViewDataStatManager AdapterDataObserver onItemRangeInserted positionStart : " + positionStart
                        + ", itemCount : " + itemCount);
            }

            // 数据局部添加时，触发
//            performStatRecyclerView(false, TimeUnit.SECONDS.toMillis(1));
            performStatRecyclerView(false);
//            globalLayoutListenerWorked = false;
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            if (enableLogs) {
                Log.d(TAG, "RecyclerViewDataStatManager AdapterDataObserver onItemRangeRemoved positionStart : " + positionStart
                        + ", itemCount : " + itemCount);
            }

            // 数据局部移除时，触发
//            performStatRecyclerView(false, TimeUnit.SECONDS.toMillis(1));
            performStatRecyclerView(false);
//            globalLayoutListenerWorked = false;
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            super.onItemRangeMoved(fromPosition, toPosition, itemCount);
            if (enableLogs) {
                Log.d(TAG, "RecyclerViewDataStatManager AdapterDataObserver onItemRangeMoved fromPosition : " + fromPosition
                        + ", toPosition : " + toPosition
                        + ", itemCount : " + itemCount);
            }
        }
    };

    protected RecyclerViewDataStatCallback dataCollectionCallback;

    // 曝光数据池，数据 AttachedToWindow 和 DetachedFromWindow 都是在主线程回调的，故：不需要考虑多线程的场景，
    // 但此时，考虑到外部有读取数据池的场景，我们依旧选择使用volitile修饰它
    private volatile Map<Integer, Boolean> mStatDataItemShowPool = new HashMap<>();

    // 数据采集状态是否合法
    private boolean isStatEnable = true;

    // 是否展现给用户
    private boolean isVisibleShowToUser;

    private boolean globalLayoutListenerWorked = false;

    private RecyclerViewDataStatManager(RecyclerView recyclerView, RecyclerView.Adapter recyclerViewAdapter) {
        this.recyclerView = recyclerView;
        if (recyclerView == null) {
            throw new IllegalArgumentException("RecyclerView must not null!!!");
        }

        recyclerView.addOnChildAttachStateChangeListener(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    performStatRecyclerView(false);
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);

        if (recyclerViewAdapter == null) {
            recyclerViewAdapter = recyclerView.getAdapter();
        }

        this.recyclerViewAdapter = recyclerViewAdapter;

        if (this.recyclerViewAdapter != null) {
            try {
                this.recyclerViewAdapter.unregisterAdapterDataObserver(mAdapterDataObserver);
            } catch (Exception e) {
                if (enableLogs) {
                    Log.d(TAG, "RecyclerViewDataStatManager AdapterDataObserver exception : " + e.getMessage());
                }
            }
            this.recyclerViewAdapter.registerAdapterDataObserver(mAdapterDataObserver);
        }
    }

    public static RecyclerViewDataStatManager newMonitorRecyclerView(RecyclerView recyclerView,
                                                                     RecyclerViewDataStatCallback dataCollectionCallback) {
        return newMonitorRecyclerView(recyclerView, dataCollectionCallback, recyclerView == null ? null : recyclerView.getAdapter());
    }

    public static RecyclerViewDataStatManager newMonitorRecyclerView(RecyclerView recyclerView,
                                                                     RecyclerViewDataStatCallback dataCollectionCallback, RecyclerView.Adapter recyclerViewAdapter) {
        return new RecyclerViewDataStatManager(recyclerView, recyclerViewAdapter).setDataStatCollectionCallback(dataCollectionCallback);
    }

    public void performStatRecyclerView(boolean force) {
        performStatRecyclerView(force, 0);
    }

    public void performStatRecyclerView(boolean force, long delay) {
        boolean needPostRunnable = recyclerView != null && recyclerView.isInLayout();

        if (enableLogs) {
            Log.d(TAG, "RecyclerViewDataStatManager performStatRecyclerView force : " + force
                    + ", isStatEnable : " + isStatEnable
                    + ", recyclerView : " + recyclerView
                    + ", isRecyclerViewStateValid : " + isRecyclerViewStateValid()
                    + ", needPost : " + needPostRunnable);
        }

        if (delay < 0) {
            delay = 0;
        }

        if (recyclerView != null) {
            recyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (enableLogs) {
                        Log.d(TAG, "RecyclerViewDataStatManager performStatRecyclerView performStatRecyclerViewInternal runnable...");
                    }
                    performStatRecyclerViewInternal(force);
                }
            }, delay);
        }
    }

    /**
     * 外部触发数据采集
     *
     * @param force
     */
    private void performStatRecyclerViewInternal(boolean force) {
        if (enableLogs) {
            Log.d(TAG, "RecyclerViewDataStatManager performStatRecyclerView force : " + force
                    + ", isStatEnable : " + isStatEnable
                    + ", isRecyclerViewStateValid : " + isRecyclerViewStateValid());
        }

        if (isStatEnable && isVisibleShowToUser && isRecyclerViewStateValid() && dataCollectionCallback != null) {
            RecyclerViewRangeOp recyclerViewRangeOp = RecyclerViewRangeOpFactory.createRecyclerViewRangeOp(recyclerView);

            if (recyclerViewRangeOp == null) {
                return;
            }

            //计算数据item显示范围
            int[] rangeArray = recyclerViewRangeOp.getRecyclerViewDataItemRange(recyclerView, recyclerViewAdapter);
            if (rangeArray == null || rangeArray.length < 2) {
                return;
            }

            int dataItemFirstPos = rangeArray[0];
            int dataItemLastPos = rangeArray[1];

            if (enableLogs) {
                Log.d(TAG, "RecyclerViewDataStatManager performStatRecyclerView first = " + dataItemFirstPos + ", last = " + dataItemLastPos + ", forceShow = " + force);
            }

            for (int i = dataItemFirstPos; i <= dataItemLastPos; i++) {

                // 如果不在曝光池中，则不曝光
                Boolean showState = obtainStatShowPoolState(i);
                if (showState == null) {
                    continue;
                }

                RecyclerView.ViewHolder vh = null;

                if (recyclerView != null) {
                    vh = recyclerView.findViewHolderForAdapterPosition(i);
                }

                // 如果未真实出现在屏幕上，则不曝光
                if (!checkViewHolderRectShow(i)) {
                    putStatShowPoolItem(i, false);
                    continue;
                }

                //如果在曝光池中且未曝光过，标记曝光
                if (!showState) {
                    putStatShowPoolItem(i, true);
                }

                //强制曝光或item未曝光则给外部曝光回调
                if (force || !showState) {
                    dataCollectionCallback.onRecyclerViewStatView(recyclerView, recyclerViewAdapter,
                            (vh == null ? null : vh.itemView), vh, i,
                            (recyclerViewAdapter == null ? -1 : recyclerViewAdapter.getItemViewType(i)));
                }
            }
        }
    }

    private boolean isRecyclerViewStateValid() {
        return recyclerView != null /*&& recyclerView.isInLayout()*/
                && recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_IDLE;
    }

    @Override
    public void onChildViewAttachedToWindow(@NonNull View view) {
        RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(view);
        int position = RecyclerView.NO_POSITION;
        if (viewHolder != null) {
            position = viewHolder.getAdapterPosition();
        }

        if (enableLogs) {
            Log.d(TAG, "RecyclerViewDataStatManager onChildViewAttachedToWindow 附加 position = " + position + ", viewHolder = " + viewHolder);
        }

        putStatShowPoolItem(position, false);
    }

    @Override
    public void onChildViewDetachedFromWindow(@NonNull View view) {
        RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(view);
        int position = RecyclerView.NO_POSITION;
        if (viewHolder != null) {
            position = viewHolder.getAdapterPosition();
        }

        if (enableLogs) {
            Log.d(TAG, "RecyclerViewDataStatManager onChildViewDetachedFromWindow 剥离 position = " + position + ", viewHolder = " + viewHolder);
        }

        removeStatShowPoolItem(position);
    }

    private boolean isInStatShowPool(int key) {
        return mStatDataItemShowPool.containsKey(key);
    }

    private void clearStatShowPool() {
        if (mStatDataItemShowPool == null || mStatDataItemShowPool.isEmpty()) {
            return;
        }
        mStatDataItemShowPool.clear();
    }

    public void resetStatShowPool() {
        if (mStatDataItemShowPool == null || mStatDataItemShowPool.isEmpty()) {
            return;
        }

        for (Map.Entry<Integer, Boolean> entry : mStatDataItemShowPool.entrySet()) {
            entry.setValue(false);
        }
    }

    private Boolean obtainStatShowPoolState(int key) {
        return mStatDataItemShowPool.get(key);
    }

    private void removeStatShowPoolItem(int key) {
        mStatDataItemShowPool.remove(key);
    }

    /**
     * @param key
     * @param viewShowState true：已曝光，false，未曝光
     */
    private void putStatShowPoolItem(int key, boolean viewShowState) {
        mStatDataItemShowPool.put(key, viewShowState);
    }

    public RecyclerViewDataStatManager setDataStatCollectionCallback(RecyclerViewDataStatCallback dataCollectionCallback) {
        this.dataCollectionCallback = dataCollectionCallback;
        return this;
    }

    // 数据搜集是否开启
    public void setStatEnable(boolean statEnable) {
        isStatEnable = statEnable;
    }

    // 是否展现给了用户
    public void setVisibleShowToUser(boolean visibleShowToUser) {
        isVisibleShowToUser = visibleShowToUser;
    }

    /**
     * 检测指定数据元素是否真实在屏幕中露出
     *
     * @param dataPos
     * @return
     */
    private boolean checkViewHolderRectShow(int dataPos) {
        if (recyclerView == null) {
            return false;
        }

        RecyclerView.ViewHolder vh = recyclerView.findViewHolderForAdapterPosition(dataPos);
        boolean itemShow = checkViewHolderRectShow(vh);

        return itemShow;
    }

    /**
     * 检测viewholder是否真实出现在屏幕中露出
     *
     * @param vh
     * @return
     */
    private boolean checkViewHolderRectShow(RecyclerView.ViewHolder vh) {
        boolean itemShow = false;
        if (vh != null && vh.itemView != null) {
            itemShow = vh.itemView.getLocalVisibleRect(new Rect());
        }

        return itemShow;
    }
}
