package com.strong.tools.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by jia on 2019/1/22.
 * BaseRecyclerViewAdapter 简单RecyclerView.Adapter封装，使用的时候传入BaseRecyclerViewCallback和Item list
 */

public class BaseRecyclerViewAdapter<V> extends RecyclerView.Adapter {
    private BaseRecyclerViewCallback baseRecyclerViewCallback;
    private List<V> items;

    //region 构造函数
    public BaseRecyclerViewAdapter() {
    }

    public BaseRecyclerViewAdapter(BaseRecyclerViewCallback baseRecyclerViewCallback, List<V> items) {
        this.baseRecyclerViewCallback = baseRecyclerViewCallback;
        this.items = items;
    }
    //endregion

    //region setter getter
    public BaseRecyclerViewCallback getBaseRecyclerViewCallback() {
        return baseRecyclerViewCallback;
    }

    public void setBaseRecyclerViewCallback(BaseRecyclerViewCallback baseRecyclerViewCallback) {
        this.baseRecyclerViewCallback = baseRecyclerViewCallback;
    }

    public V getItem(int position) {
        if (items != null && position < items.size()) {
            return items.get(position);
        } else {
            return null;
        }
    }

    public List<V> getItems() {
        return items;
    }

    public void setItems(List<V> items) {
        this.items = items;
        notifyDataSetChanged();
    }
    //endregion

    //region 继承函数
    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(baseRecyclerViewCallback.getViewRes(), parent, false);
        return new BaseViewHolder(v) {

        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder != null && holder.itemView != null) {
            if (baseRecyclerViewCallback != null) {
                baseRecyclerViewCallback.onBindView((BaseViewHolder) holder, position, getItem(position));
            }
            holder.itemView.setOnClickListener(view -> {
                if (baseRecyclerViewCallback != null) {
                    baseRecyclerViewCallback.onClickItem(view, position, getItem(position));
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (baseRecyclerViewCallback != null) {
                        return baseRecyclerViewCallback.onLongClickItem(view, position, getItem(position));
                    }
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        } else {
            return items.size();
        }
    }
    //endregion
}
