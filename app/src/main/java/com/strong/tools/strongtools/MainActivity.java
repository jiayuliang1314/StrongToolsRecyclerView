package com.strong.tools.strongtools;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
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
        suggestAdapter.setHasStableIds(false);
        suggestAdapter.setBaseRecyclerViewCallback(new BaseRecyclerViewCallback<String>() {
            @Override
            public void onBindView(BaseViewHolder holder, int position, String item) {
                Log.i("onBindView", "position " + position + " item " + item);

                TextView textView = (TextView) holder.getView(R.id.text);
                textView.setText(item);
                LinearLayout delete = (LinearLayout) holder.getView(R.id.delete);
                LinearLayout add = (LinearLayout) holder.getView(R.id.add);
                holder.itemView.setOnClickListener(view -> {
                    int positionWhenOnClick = holder.getAdapterPosition();
                    if (positionWhenOnClick == RecyclerView.NO_POSITION) {
                        Log.i("onBindViewOnClick", "positionWhenOnClick  NO_POSITION");
                        return;
                    }
                    Log.i("onBindViewOnClick", "position " + position + " item " + item);
                    Log.i("onBindViewOnClick", "positionWhenOnClick " + positionWhenOnClick + " itemWhenOnClick " + list.get(positionWhenOnClick));
                    Toast.makeText(MainActivity.this, "onBindViewOnClick " + list.get(positionWhenOnClick), Toast.LENGTH_SHORT).show();
                });
                delete.setOnClickListener(view -> {
                    int positionWhenOnClick = holder.getAdapterPosition();
                    if (positionWhenOnClick == RecyclerView.NO_POSITION) {
                        Log.i("onBindViewOnDelete", "positionWhenOnClick  NO_POSITION");
                        return;
                    }
                    list.remove(positionWhenOnClick);
                    suggestAdapter.setItems(list);
                    Log.i("onBindViewOnDelete", "position " + position + " item " + item);
//                    Log.i("onBindViewOnDelete", "positionWhenOnClick " + positionWhenOnClick + " itemWhenOnClick " + list.get(positionWhenOnClick));
                    Toast.makeText(MainActivity.this, "onBindViewOnDelete " + positionWhenOnClick, Toast.LENGTH_SHORT).show();

                });
                add.setOnClickListener(view -> {
                    int positionWhenOnClick = holder.getAdapterPosition();
                    if (positionWhenOnClick == RecyclerView.NO_POSITION) {
                        Log.i("onBindViewOnAdd", "positionWhenOnClick  NO_POSITION");
                        return;
                    }
                    list.add(positionWhenOnClick, list.get(positionWhenOnClick) + "");
                    for(String i:list){
                        Log.i("onBindViewOnAdd i ", "positionWhenOnClick "+i);
                    }
                    suggestAdapter.setItems(list);
                    Log.i("onBindViewOnAdd", "position " + position + " item " + item);
                    Log.i("onBindViewOnAdd", "positionWhenOnClick " + positionWhenOnClick + " itemWhenOnClick " + list.get(positionWhenOnClick));
                    Toast.makeText(MainActivity.this, "onBindViewOnAdd " + list.get(positionWhenOnClick), Toast.LENGTH_SHORT).show();

                });
            }

            @Override
            public int getViewRes(int viewType) {
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

            @Override
            public int getItemViewType(int position) {
                return 0;
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
                if (list.size() == 0) {
                    Toast.makeText(MainActivity.this, "List now is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                list.remove(list.size() - 1);
                suggestAdapter.setItems(list);
            }
        });
    }
}
