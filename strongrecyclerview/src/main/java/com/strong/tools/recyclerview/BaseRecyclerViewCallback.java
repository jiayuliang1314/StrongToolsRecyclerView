package com.strong.tools.recyclerview;

/**
 * BaseRecyclerViewCallback
 *
 * @param <T> T是item的类型
 */
public interface BaseRecyclerViewCallback<T> {
    void onBindView(BaseViewHolder holder, int position, T item);

    int getViewRes();

    boolean areItemsTheSame(T oldItem, T newItem);

    boolean areContentsTheSame(T oldItem, T newItem);

    long getItemId(T item, int position);
}
