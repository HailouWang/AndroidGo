
1、Fresco使用优化事项

- RecyclerView 滚动，上滑，下滑时，可以设定加载策略。


switch (newState) {
            //停止滑动
            case RecyclerView.SCROLL_STATE_IDLE:

                if (Fresco.getImagePipeline().isPaused())
                    Fresco.getImagePipeline().resume();

                break;
            //触摸滑动
            case RecyclerView.SCROLL_STATE_DRAGGING:

                if (newState == RecyclerView.SCROLL_STATE_SETTLING) {

                    //触摸滑动不需要加载
                    Fresco.getImagePipeline().pause();
                } else {

                    //触摸滑动需要加载
                    if (Fresco.getImagePipeline().isPaused())
                        Fresco.getImagePipeline().resume();
                }
                break;
            //惯性滑动
            case RecyclerView.SCROLL_STATE_SETTLING:
                Fresco.getImagePipeline().pause();
                break;
        }