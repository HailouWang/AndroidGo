
## RecyclerView 级联滑动的难点

1、埋点数据统计问题

2、级联滑动抖动：DOWN 事件，点击区域分属两个View时，要处理 Fling 与 Down事件之间的冲突问题
- 级联滑动抖动：模拟 MOVE 事件，传递给SubView

3、