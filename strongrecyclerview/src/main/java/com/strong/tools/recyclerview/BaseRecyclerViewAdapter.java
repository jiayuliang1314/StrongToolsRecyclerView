package com.strong.tools.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jia on 2019/1/22.
 * BaseRecyclerViewAdapter 简单RecyclerView.Adapter封装，使用的时候传入BaseRecyclerViewCallback和Item list
 */

public class BaseRecyclerViewAdapter<V> extends RecyclerView.Adapter {
    private BaseRecyclerViewCallback mBaseRecyclerViewCallback;
    private List<V> mItems;

    //region 构造函数
    public BaseRecyclerViewAdapter() {
        setHasStableIds(true);
    }

    public BaseRecyclerViewAdapter(BaseRecyclerViewCallback baseRecyclerViewCallback, List<V> items) {
        this.mBaseRecyclerViewCallback = baseRecyclerViewCallback;
        this.mItems = items;
        setHasStableIds(true);
    }
    //endregion

    //region setter getter
    public BaseRecyclerViewCallback getBaseRecyclerViewCallback() {
        return mBaseRecyclerViewCallback;
    }

    public void setBaseRecyclerViewCallback(BaseRecyclerViewCallback baseRecyclerViewCallback) {
        this.mBaseRecyclerViewCallback = baseRecyclerViewCallback;
    }

    public V getItem(int position) {
        if (mItems != null && position < mItems.size()) {
            return mItems.get(position);
        } else {
            return null;
        }
    }

    public List<V> getItems() {
        return mItems;
    }

    public void setItems(List<V> items) {
        if (mItems == null) {
            this.mItems = new ArrayList<V>();
            this.mItems.addAll(items);      //防止浅拷贝
            notifyDataSetChanged();
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mItems.size();
                }

                @Override
                public int getNewListSize() {
                    return items.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mBaseRecyclerViewCallback.areItemsTheSame(mItems.get(oldItemPosition), items.get(newItemPosition));
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    return mBaseRecyclerViewCallback.areContentsTheSame(mItems.get(oldItemPosition), items.get(newItemPosition));
                }
            });
            this.mItems = new ArrayList<V>();
            this.mItems.addAll(items);          //防止浅拷贝
            result.dispatchUpdatesTo(this);
        }
    }
    //endregion

    //region 继承函数
    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(mBaseRecyclerViewCallback.getViewRes(), parent, false);
        return new BaseViewHolder(v) {

        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder != null && holder.itemView != null) {
            if (mBaseRecyclerViewCallback != null) {
                mBaseRecyclerViewCallback.onBindView((BaseViewHolder) holder, position, getItem(position));
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mItems == null) {
            return 0;
        } else {
            return mItems.size();
        }
    }

    @Override
    public long getItemId(int position) {
        if (mBaseRecyclerViewCallback != null) {
            return mBaseRecyclerViewCallback.getItemId(getItem(position), position);
        }
        return position;
    }
    //endregion
}
