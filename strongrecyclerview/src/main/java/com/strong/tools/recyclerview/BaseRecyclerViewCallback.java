package com.strong.tools.recyclerview;

/**
 * BaseRecyclerViewCallback
 *
 * @param <T> T是item的类型
 */
public interface BaseRecyclerViewCallback<T> {
    void onBindView(BaseViewHolder holder, int position, T item);

//    /**
//     * onClickItem 不建议使用，建议在onBindView里设置
//     *
//     * @param view
//     * @param position
//     * @param item
//     */
//    @Deprecated
//    void onClickItem(View view, int position, T item);
//
//    /**
//     * onLongClickItem 不建议使用，建议在onBindView里设置
//     *
//     * @param view
//     * @param position
//     * @param item
//     * @return
//     */
//    @Deprecated
//    boolean onLongClickItem(View view, int position, T item);

    int getViewRes();

    boolean areItemsTheSame(T oldItem, T newItem);

    boolean areContentsTheSame(T oldItem, T newItem);
}
