package com.strong.tools.recyclerview;

/**
 * BaseRecyclerViewCallback
 *
 * @param <T> T是item的类型
 */
public interface BaseRecyclerViewCallback<T> {
    /**
     * 绑定View，为view设置监听事件，显示view
     *
     * @param holder
     * @param position
     * @param item
     */
    void onBindView(BaseViewHolder holder, int position, T item);

    /**
     * 得到res id
     * @param viewType
     * @return
     */
    int getViewRes(int viewType);

    /**
     * 提供给DiffUtil类使用，用于刷新
     *
     * @param oldItem
     * @param newItem
     * @return
     */
    boolean areItemsTheSame(T oldItem, T newItem);

    /**
     * 提供给DiffUtil类使用，用于刷新
     *
     * @param oldItem
     * @param newItem
     * @return
     */
    boolean areContentsTheSame(T oldItem, T newItem);

    /**
     * 返回Item 的id
     *
     * @param item
     * @param position
     * @return
     */
    long getItemId(T item, int position);

    /**
     * 返回item type
     *
     * @param position
     * @return
     */
    int getItemViewType(int position);
}
