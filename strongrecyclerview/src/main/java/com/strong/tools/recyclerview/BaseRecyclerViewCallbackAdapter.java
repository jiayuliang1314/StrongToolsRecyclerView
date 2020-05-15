package com.strong.tools.recyclerview;

/**
 * @author jia
 * 如果只是一个简单显示用的列表，可以只需继承BaseRecyclerViewCallbackAdapter<T> ，覆盖onBindView getViewRes方法
 */
public class BaseRecyclerViewCallbackAdapter<T> implements BaseRecyclerViewCallback<T> {

    @Override
    public void onBindView(BaseViewHolder holder, int position, T item) {

    }

    @Override
    public int getViewRes(int viewType) {
        return 0;
    }

    @Override
    public boolean areItemsTheSame(T oldItem, T newItem) {
        return false;
    }

    @Override
    public boolean areContentsTheSame(T oldItem, T newItem) {
        return false;
    }

    /**
     * 返回item的唯一标示
     * @param item
     * @param position
     * @return
     */
    @Override
    public long getItemId(T item, int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }
}