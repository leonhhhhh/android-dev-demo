package com.leon.dev_demo.view.recyclerview;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 万能适配器
 */
public abstract class MagicAdapter<T> extends RecyclerView.Adapter<MagicAdapter.MagicViewHolder> {
    private List<T> data;

    public MagicAdapter(List<T> data) {
        this.data = data;
    }
    public abstract int getLayoutId(int viewType);
    @NonNull
    @Override
    public MagicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return MagicViewHolder.getVH(parent,getLayoutId(viewType));
    }

    @Override
    public void onBindViewHolder(@NonNull MagicViewHolder holder, int position) {
        convert(holder,data.get(position),getItemViewType(position));
    }
    public abstract void convert(MagicViewHolder holder,T itemData,int viewType);

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class MagicViewHolder extends RecyclerView.ViewHolder {
        private SparseArray<View> views;
        private View itemViews;
        private MagicViewHolder(@NonNull View itemView) {
            super(itemView);
            views = new SparseArray<>();
            this.itemViews = itemView;
        }
        public static MagicViewHolder getVH(ViewGroup parent, int layoutId){
            View itemView = LayoutInflater.from(parent.getContext()).inflate(layoutId,parent,false);
            return new MagicViewHolder(itemView);
        }
        public <T extends View>T getView(int id){
            View view = views.get(id);
            if (view == null){
                view = itemViews.findViewById(id);
                views.put(id,view);
            }
            return (T) view;
        }
    }
}
