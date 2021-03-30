package com.leon.dev_demo.view.recyclerview;

public interface AdapterDragCallBack {
    void onItemMove(int srcPosition,int targetPosition);
    void onItemSwiped(int position);
}
