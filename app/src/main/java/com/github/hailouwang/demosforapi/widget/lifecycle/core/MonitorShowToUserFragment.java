package com.github.hailouwang.demosforapi.widget.lifecycle.core;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 添加Fragment的方法如下：
 * FragmentManager fragmentManager = getFragmentManager();
 * FragmentTransaction transaction = fragmentManager. beginTransaction();
 * transaction.add(R.id.right_layout, fragment);
 * transaction.commit();
 * <p>
 * 这样让我们先看看getFragmentManger()内部是怎么获取到FragmentManger的？
 * <p>
 * FragmentController mFragments = FragmentController.createController(new HostCallbacks());
 * public FragmentManager getFragmentManager() {
 * return mFragments.getFragmentManager();
 * }
 * <p>
 * 通过获取fragmentManger就获取到了FragmentTranction对象，这个一个事务对象，他是一个抽象类，实现是在BackStackRecord中。
 * 我们继续向下看：
 * （FragmentManger#add）
 * public abstract FragmentTransaction add(@IdRes int containerViewId, Fragment fragment);
 * <p>
 * 因为这是一个抽象方法，所以我们到BackStackRecord中看add()方法。
 *
 * @Override public FragmentTransaction add(int containerViewId, Fragment fragment) {
 * doAddOp(containerViewId, fragment, null, OP_ADD);
 * return this;
 * }
 * 进入到doAddOp()方法中看下：
 * private void doAddOp(int containerViewId, Fragment fragment, String tag, int opcmd) {
 * fragment.mFragmentManager = mManager;
 * fragment.mTag = tag;
 * fragment.mContainerId = fragment.mFragmentId = containerViewId;
 * addOp(new Op(opcmd, fragment));
 * }
 * 这个方法整体就做了一件事情，就是给穿进来的fragment的全局变量赋值，然后将fragment封装到了Op这个对象中。继续addOp方法中：
 * ArrayList<Op> mOps = new ArrayList<>();
 * void addOp(Op op) {
 * mOps.add(op);
 * }
 * 将新生成的op对象放入了数组中。这样add()方法的流程就完成了。
 * 总结：
 * 将我们的fragment的全局变量进行赋值，封装到op对象后，放入缓存数组中。
 * <p>
 * 现在我们看看commit()方法做了哪些事情？
 * 我们直接进入到BackStackRecord类中看下commit的实现
 * @Override public int commit() {
 * return commitInternal(false);
 * }
 * <p>
 * int commitInternal(boolean allowStateLoss) {
 * mManager.enqueueAction(this, allowStateLoss);
 * return mIndex;
 * }
 * <p>
 * 使用了FramgentManger的方法，这样我们在看看这个方法的处理：
 * <p>
 * public void enqueueAction(OpGenerator action, boolean allowStateLoss) {
 * synchronized (this) {
 * if (mPendingActions == null) {
 * mPendingActions = new ArrayList<>();
 * }
 * mPendingActions.add(action);
 * scheduleCommit();
 * }
 * }
 * <p>
 * private void scheduleCommit() {
 * //以下的操作进入到了ui线程处理
 * mHost.getHandler().post(mExecCommit);
 * }
 * <p>
 * Runnable mExecCommit = new Runnable() {
 * @Override public void run() {
 * execPendingActions();
 * }
 * };
 * <p>
 * public boolean execPendingActions() {
 * doPendingDeferredStart();
 * return didSomething;
 * }
 * <p>
 * void doPendingDeferredStart() {
 * startPendingDeferredFragments();
 * }
 * <p>
 * void startPendingDeferredFragments() {
 * if (mActive == null) return;
 * for (int i=0; i<mActive.size(); i++) {
 * //取出所有的frament进行状态的修改
 * Fragment f = mActive.valueAt(i);
 * if (f != null) {
 * performPendingDeferredStart(f);
 * }
 * }
 * }
 * <p>
 * public void performPendingDeferredStart(Fragment f) {
 * moveToState(f, mCurState, 0, 0, false);
 * }
 * <p>
 * void moveToState(Fragment f, int newState, int transit, int transitionStyle,
 * boolean keepActive) {
 * <p>
 * switch (f.mState) {
 * case Fragment.INITIALIZING:
 * <p>
 * f.onAttach(mHost.getContext()); //调用了fragment的onattach方法
 * f.performCreate(f.mSavedFragmentState);  //oncreate
 * <p>
 * case Fragment.CREATED:
 * container = (ViewGroup) mContainer.onFindViewById(f.mContainerId);
 * f.mContainer = container;
 * f.mView = f.performCreateView(); //onCreateView
 * f.onViewCreated(f.mView, f.mSavedFragmentState); //onViewCreate
 * f.performActivityCreated(f.mSavedFragmentState); //onActivityCreate
 * <p>
 * case Fragment.ACTIVITY_CREATED:
 * <p>
 * case Fragment.STOPPED:
 * <p>
 * case Fragment.STARTED:
 * <p>
 * }
 * }
 * <p>
 * 总结：
 * 每一个Fragment都被封装到BackStackRecord中（内部类OP），FragmentManger内部保存着BackStackRecord的集合，Fragment的添加是通过FragmentManger来控制。
 * ————————————————
 * 版权声明：本文为CSDN博主「BunnyCoffer」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/bunnycoffer/article/details/82735560
 */
public class MonitorShowToUserFragment extends Fragment {
    private boolean LOG = true;

    public static final int SHOW_TO_USER_FROM_ONSTART = 1;
    public static final int SHOW_TO_USER_FROM_VIEWPAGER_SELECT = 2;
    public static final int SHOW_TO_USER_FROM_HIDDEN = 3;
    public static final int SHOW_TO_USER_FROM_PARENT = 4;

    private static final String TAG = "VisibleToUser";

    // 本地状态变量
    private volatile boolean localFragmentStart = false;

    // Fragment 存在嵌套，默认不存在嵌套，即：默认父类页面就是显示的
    private boolean localParentFragmentVisibleShowToUser = true;

    private boolean currentVisibleToUser;

    public MonitorShowToUserFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "VisibleToUserChangeFragment onCreate isHidden : " + isHidden()
                + ", isVisibleShowToUser : " + isVisibleShowToUser());
    }

    @Override
    public void onStart() {
        super.onStart();
        localFragmentStart = true;
        Log.d(TAG, "VisibleToUserChangeFragment onStart isHidden : " + isHidden()
                + ", isVisibleShowToUser : " + isVisibleShowToUser());
        callbackOnVisibleShowToUserChange(isVisibleShowToUser(), SHOW_TO_USER_FROM_ONSTART);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "VisibleToUserChangeFragment onResume isHidden : " + isHidden()
                + ", isVisibleShowToUser : " + isVisibleShowToUser());
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "VisibleToUserChangeFragment onPause isHidden : " + isHidden()
                + ", isVisibleShowToUser : " + isVisibleShowToUser());
    }

    @Override
    public void onStop() {
        super.onStop();
        localFragmentStart = false;
        Log.d(TAG, "VisibleToUserChangeFragment onStop isHidden : " + isHidden()
                + ", isVisibleShowToUser : " + isVisibleShowToUser());
        callbackOnVisibleShowToUserChange(isVisibleShowToUser(), SHOW_TO_USER_FROM_ONSTART);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "VisibleToUserChangeFragment onDestroy isHidden : " + isHidden()
                + ", isVisibleShowToUser : " + isVisibleShowToUser());
    }

    /**
     * case OP_HIDE:
     * f.setNextAnim(op.mExitAnim);
     * mManager.hideFragment(f);
     * break;
     * case OP_SHOW:
     * f.setNextAnim(op.mEnterAnim);
     * mManager.showFragment(f);
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d(TAG, "VisibleToUserChangeFragment onHiddenChanged hidden : " + hidden);
        callbackOnVisibleShowToUserChange(isVisibleShowToUser(), SHOW_TO_USER_FROM_HIDDEN);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d(TAG, "VisibleToUserChangeFragment setUserVisibleHint isVisibleToUser : " + isVisibleToUser);
        if (isAdded()) {
            callbackOnVisibleShowToUserChange(isVisibleShowToUser(), SHOW_TO_USER_FROM_VIEWPAGER_SELECT);
        }
    }

    protected void onVisibleShowToUserChange(boolean isVisibleShowToUser, int stage) {
        Log.d(TAG, "VisibleToUserChangeFragment onVisibleShowToUserChange isVisibleToUser : " + isVisibleShowToUser
                + ", stage : " + stage);
    }

    public boolean isVisibleShowToUser() {
        return localParentFragmentVisibleShowToUser && localFragmentStart && isAdded() && !isHidden();
    }

    private void callbackOnVisibleShowToUserChange(boolean isShowToUser, int from) {
        Log.d(TAG, "VisibleToUserChangeFragment callbackOnVisibleShowToUserChange isShowToUser : " + isShowToUser
                + ", from : " + from);
        if (currentVisibleToUser != isShowToUser) {
            currentVisibleToUser = isShowToUser;
            onVisibleShowToUserChange(isShowToUser, from);
        }
    }

    public void setLocalParentFragmentVisibleShowToUser(boolean localParentFragmentVisibleShowToUser) {
        this.localParentFragmentVisibleShowToUser = localParentFragmentVisibleShowToUser;
        callbackOnVisibleShowToUserChange(isVisibleShowToUser(), SHOW_TO_USER_FROM_PARENT);
    }
}
