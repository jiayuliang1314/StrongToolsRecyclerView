package com.strong.tools.recyclerview;

import android.view.View;

/**
 * BaseRecyclerViewCallback
 * @param <T> T是item的类型
 */
public interface BaseRecyclerViewCallback<T> {
    void onBindView(View view, int position, T item);

    void onClickItem(View view, int position, T item);

    boolean onLongClickItem(View view, int position, T item);

    int getViewRes();
}
