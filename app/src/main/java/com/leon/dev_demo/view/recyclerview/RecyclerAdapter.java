package com.leon.dev_demo.view.recyclerview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leon.dev_demo.R;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

public class RecyclerAdapter extends Adapter<RecyclerView.ViewHolder> implements AdapterDragCallBack{
    private static final String TAG = "RecyclerAdapter";

    @Override
    public void onItemMove(int srcPosition, int targetPosition) {
        Collections.swap(data,srcPosition,targetPosition);
        notifyItemMoved(srcPosition,targetPosition);
    }

    @Override
    public void onItemSwiped(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * ItemType常量
     */
    public enum ITEM_TYPE {
        ITEM_TYPE_NORMAL,
        ITEM_TYPE_OTHER
    }
    /**
     * 数据源
     */
    private List<RecyclerBean> data;
    private View.OnClickListener onClickListener;
    /**
     * @param data 数据源
     */
    public RecyclerAdapter(List<RecyclerBean> data) {
        this.data = data;
    }
    /**
     * @param data 数据源
     */
    public RecyclerAdapter(List<RecyclerBean> data,View.OnClickListener onClickListener) {
        this.data = data;
        this.onClickListener = onClickListener;
    }

    /**
     * 根据不同的viewType创建对应的布局
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = null;
        if (viewType == ITEM_TYPE.ITEM_TYPE_NORMAL.ordinal()){
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_recycler_view,parent,false);
            if (onClickListener != null){
                itemView.setOnClickListener(onClickListener);
            }
            return new RecyclerHolder(itemView);
        }else if (viewType == ITEM_TYPE.ITEM_TYPE_OTHER.ordinal()){
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_recycler_view_other,parent,false);
            if (onClickListener != null){
                itemView.setOnClickListener(onClickListener);
            }
            return new RecyclerOtherHolder(itemView);
        }
        Log.e(TAG,"调用onCreateViewHolder");
        return null;
    }

    /**
     * 根据position返回不同的ViewType
     */
    @Override
    public int getItemViewType(int position) {
        return position==0?ITEM_TYPE.ITEM_TYPE_NORMAL.ordinal():ITEM_TYPE.ITEM_TYPE_OTHER.ordinal();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        int viewType = getItemViewType(position);
        RecyclerBean bean = data.get(position);
        if (viewType == ITEM_TYPE.ITEM_TYPE_NORMAL.ordinal()){
            RecyclerHolder recyclerHolder = (RecyclerHolder) viewHolder;
            recyclerHolder.titleTv.setText(bean.getName());
        }else if (viewType == ITEM_TYPE.ITEM_TYPE_OTHER.ordinal()){
            RecyclerOtherHolder recyclerOtherHolder = (RecyclerOtherHolder) viewHolder;
            recyclerOtherHolder.titleTv.setText(bean.getName());
        }
        Log.e(TAG,"调用onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * ViewHolder类
     */
    public static class RecyclerHolder extends RecyclerView.ViewHolder {
        private TextView titleTv;
        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.titleTv);
        }
    }
    /**
     * 第二种ViewHolder类
     */
    public static class RecyclerOtherHolder extends RecyclerView.ViewHolder {
        private TextView titleTv;
        public RecyclerOtherHolder(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.titleTv);
        }
    }
}
