package com.strong.tools.strongtools;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.strong.tools.recyclerview.BaseRecyclerViewAdapter;
import com.strong.tools.recyclerview.BaseRecyclerViewCallback;
import com.strong.tools.recyclerview.RecyclerViewUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BaseRecyclerViewAdapter suggestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.suggestList);

        suggestAdapter = new BaseRecyclerViewAdapter();
        suggestAdapter.setBaseRecyclerViewCallback(new BaseRecyclerViewCallback<String>() {
            @Override
            public void onBindView(View view, int position, String item) {
                TextView textView = view.findViewById(R.id.text);
                textView.setText(item);
            }

            @Override
            public void onClickItem(View view, int position, String item) {
                Toast.makeText(MainActivity.this, "onClickItem", Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onLongClickItem(View view, int position, String item) {
                return false;
            }

            @Override
            public int getViewRes() {
                return R.layout.suggest_message_view_item;
            }
        });
        List<String> list = new ArrayList<>();
        list.add("123");
        list.add("234");
        list.add("345");
        suggestAdapter.setItems(list);

        RecyclerViewUtils.setLayoutManager(this, recyclerView, RecyclerViewUtils.LinearLayoutManagerVertical);
        RecyclerViewUtils.addItemDecoration(this, recyclerView, RecyclerViewUtils.DividerItemDecorationVertical);
        recyclerView.setAdapter(suggestAdapter);
    }
}