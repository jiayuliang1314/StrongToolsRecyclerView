package com.strong.tools.recyclerview.databinding;

import android.databinding.ViewDataBinding;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * @author : ZhouYou
 * Date: 2017/11/3.
 */
public abstract class BaseViewBindngHolder<D extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private D mViewDataBinding;

    protected BaseViewBindngHolder(D viewDataBinding) {
        super(viewDataBinding.getRoot());
        mViewDataBinding = viewDataBinding;
    }
}
