package com.leon.dev_demo.view.recyclerview;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.leon.dev_demo.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class RecyclerViewActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private List<RecyclerBean> data;
    private RecyclerAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        setTitle("RecyclerView示例");

        data = getMockData();

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new RecyclerAdapter(data, new OnRecyclerViewClick());
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        setItemDecoration(recyclerView);

        recyclerView.setAdapter(adapter);
        RecyclerViewDragCallBack viewDragCallBack = new RecyclerViewDragCallBack(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(viewDragCallBack);
        touchHelper.attachToRecyclerView(recyclerView);

        //万能适配器
//        MagicAdapter<String> magicAdapter = new MagicAdapter<String>(null) {
//            @Override
//            public int getLayoutId(int viewType) {
//                return R.layout.layout_item_recycler_view;
//            }
//
//            @Override
//            public void convert(MagicViewHolder holder,String data, int viewType) {
//                if (viewType == 0){
//                    TextView textView = holder.getView(R.id.titleTv);
//                    textView.setText(data);
//                }
//            }
//        };
//        recyclerView.setAdapter(magicAdapter);

        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private List<RecyclerBean> getMockData() {
        List<RecyclerBean> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            RecyclerBean bean = new RecyclerBean(i,"名字"+i,"说明"+i);
            data.add(bean);
        }
        return data;
    }

    private static final int TYPE_LINEAR = 0;
    private static final int TYPE_GRID = 1;
    private static final int TYPE_STAGGERED_GRID = 2;
    /**
     * @param type 0:线性布局  1:表格布局   2:瀑布流布局
     */
    private void setLayoutManager(int type,RecyclerView recyclerView){
        if (type == TYPE_LINEAR){
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
        }else if (type == TYPE_GRID){
            GridLayoutManager layoutManager = new GridLayoutManager(this,3);
            recyclerView.setLayoutManager(layoutManager);
        }else if (type == TYPE_STAGGERED_GRID){
            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
        }
    }

    /**
     * 设置分割线
     */
    private void setItemDecoration(RecyclerView recyclerView){
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, LinearLayout.VERTICAL);
//        itemDecoration.setDrawable();//设置分割线drawable属性
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onRefresh() {
        //模拟耗时操作
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        },1000);
    }

    /**
     * 自定义 点击事件
     * RecyclerView 设置item点击事件最佳实践
     */
    class OnRecyclerViewClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder vh = recyclerView.getChildViewHolder(view);
            int position = vh.getAdapterPosition();
            actionPosition(position);
        }
    }

    /**
     * item点击事件处理方法
     * @param position 索引
     */
    private void actionPosition(int position){
        Toast.makeText(RecyclerViewActivity.this, "点击:"+position, Toast.LENGTH_SHORT).show();
        RecyclerBean bean = new RecyclerBean(0,"插入数据","");
//        bean.setName(bean.getName()+"修改后");
//        data.remove(position);
//        data.remove(position+1);
//        adapter.notifyItemMoved(position,position+1);
//        adapter.notifyItemRemoved(position);
        data.add(position,bean);
        adapter.notifyItemInserted(position);
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
//                adapter.notifyDataSetChanged();
            }
        },500);
    }
}