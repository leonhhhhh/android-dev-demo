package com.leon.dev_demo.view.recyclerview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewDragCallBack extends ItemTouchHelper.Callback {
    private AdapterDragCallBack adapterDragCallBack;
    public RecyclerViewDragCallBack(AdapterDragCallBack adapterDragCallBack) {
        this.adapterDragCallBack = adapterDragCallBack;
    }
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(dragFlags,swipeFlags);
    }
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        //非相同viewType拖拽会瞬间重置
        if (viewHolder.getItemViewType() != target.getItemViewType()){
            return false;
        }
        adapterDragCallBack.onItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
        return true;
    }
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        adapterDragCallBack.onItemSwiped(viewHolder.getAdapterPosition());
    }
}
