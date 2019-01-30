package com.strong.tools.recyclerview;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class RecyclerViewUtils {
    public static final int LinearLayoutManagerVertical = 1;
    public static final int LinearLayoutManagerHORIZONTAL = 2;
    public static final int DividerItemDecorationVertical = 1;

    private RecyclerViewUtils() {
    }

    /**
     * 设置LayoutManager
     *
     * @param context
     * @param recyclerView
     * @param type         1为LinearLayoutManager VERTICAL
     */
    public static void setLayoutManager(Context context, RecyclerView recyclerView, int type) {
        if (type == LinearLayoutManagerVertical) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        } else if (type == LinearLayoutManagerVertical) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        }
    }

    /**
     * 设置addItemDecoration,添加Android自带的分割线
     *
     * @param context
     * @param recyclerView
     * @param type
     */
    public static void addItemDecoration(Context context, RecyclerView recyclerView, int type) {
        if (type == DividerItemDecorationVertical) {
            recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        }
    }
}
