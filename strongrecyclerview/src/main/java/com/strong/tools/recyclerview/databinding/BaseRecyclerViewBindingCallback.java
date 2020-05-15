package com.strong.tools.recyclerview.databinding;

import android.databinding.ViewDataBinding;
import android.view.ViewGroup;

/**
 * BaseRecyclerViewCallback
 *
 * @param <T> T是item的类型
 * @author jia
 */
public interface BaseRecyclerViewBindingCallback<T> {
    /**
     * 绑定View，为view设置监听事件，显示view
     *
     * @param holder
     * @param position
     * @param item
     */
    void onBindView(BaseViewBindngHolder holder, int position, T item);

    /**
     * 得到res id
     *
     * @param viewType
     * @return
     */
    ViewDataBinding getViewDatabinding(ViewGroup parent, int viewType);

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
     * 返回Item 的id，唯一标示
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
