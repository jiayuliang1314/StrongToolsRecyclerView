package com.strong.tools.recyclerview;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Author: ZhouYou
 * Date: 2017/11/3.
 */
public abstract class BaseViewHolder<K, V> extends RecyclerView.ViewHolder {

    private SparseArray<View> views = new SparseArray<>();

    protected BaseViewHolder(View itemView) {
        super(itemView);
    }

    public <T extends View> T getView(@IdRes int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }
}
