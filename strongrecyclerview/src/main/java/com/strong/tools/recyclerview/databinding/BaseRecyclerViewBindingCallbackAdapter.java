package com.strong.tools.recyclerview.databinding;

import android.databinding.ViewDataBinding;
import android.view.ViewGroup;

/**
 * @author jia
 * 如果只是一个简单显示用的列表，可以只需继承BaseRecyclerViewCallbackAdapter<T> ，覆盖onBindView getViewRes方法
 */
public class BaseRecyclerViewBindingCallbackAdapter<T> implements BaseRecyclerViewBindingCallback<T> {

    @Override
    public void onBindView(BaseViewBindngHolder holder, int position, T item) {

    }

    @Override
    public ViewDataBinding getViewDatabinding(ViewGroup parent, int viewType) {
        return null;
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
     *
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