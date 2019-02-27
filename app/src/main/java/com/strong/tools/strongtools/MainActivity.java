package com.strong.tools.strongtools;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.strong.tools.recyclerview.BaseRecyclerViewAdapter;
import com.strong.tools.recyclerview.BaseRecyclerViewCallback;
import com.strong.tools.recyclerview.BaseViewHolder;
import com.strong.tools.recyclerview.RecyclerViewUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BaseRecyclerViewAdapter suggestAdapter;
    private List<String> list = new ArrayList<>();
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.suggestList);

        //简化RecyclerView.Adapter的创建
        suggestAdapter = new BaseRecyclerViewAdapter();
        suggestAdapter.setBaseRecyclerViewCallback(new BaseRecyclerViewCallback<String>() {
            @Override
            public void onBindView(BaseViewHolder holder, int position, String item) {
                TextView textView = (TextView) holder.getView(R.id.text);
                textView.setText(item);
                holder.itemView.setOnClickListener(view -> {
                    Toast.makeText(MainActivity.this, "onClickItem " + item, Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public int getViewRes() {
                return R.layout.suggest_message_view_item;
            }

            @Override
            public boolean areItemsTheSame(String oldItem, String newItem) {
                return oldItem != null && oldItem.equals(newItem);
            }

            @Override
            public boolean areContentsTheSame(String oldItem, String newItem) {
                return oldItem != null && oldItem.equals(newItem);
            }

            @Override
            public long getItemId(String item, int position) {
                return position;
            }
        });

        list.add("" + i++);
        list.add("" + i++);
        list.add("" + i++);
        suggestAdapter.setItems(list);

        //RecyclerViewUtils简化设置LayoutManager，添加Android自带的分割线等操作
        RecyclerViewUtils.setLinearLayoutManager(this, recyclerView, LinearLayoutManager.VERTICAL, false);
        RecyclerViewUtils.addDividerItemDecoration(this, recyclerView, DividerItemDecoration.VERTICAL);
        recyclerView.setAdapter(suggestAdapter);

        findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add("" + i++);
                suggestAdapter.setItems(list);
            }
        });

        findViewById(R.id.removeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(list.size() - 1);
                suggestAdapter.setItems(list);
            }
        });
    }
}
