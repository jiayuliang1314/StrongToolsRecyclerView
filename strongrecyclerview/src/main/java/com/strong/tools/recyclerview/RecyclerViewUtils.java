package com.strong.tools.recyclerview;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * @author jia
 */
public class RecyclerViewUtils {
    private RecyclerViewUtils() {
    }

    //region set LayoutManager

    public static void setLinearLayoutManager(Context context, RecyclerView recyclerView, int orientation, boolean reverseLayout) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context, orientation, reverseLayout));
    }

    public static void setGridLayoutManager(Context context, RecyclerView recyclerView, int spanCount, int orientation, boolean reverseLayout) {
        recyclerView.setLayoutManager(new GridLayoutManager(context, spanCount, orientation, reverseLayout));
    }

    public static void setStaggeredGridLayoutManager(Context context, RecyclerView recyclerView, int spanCount, int orientation, boolean reverseLayout) {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(spanCount, orientation));
    }
    //endregion

    //region set DividerItemDecoration

    public static void addDividerItemDecoration(Context context, RecyclerView recyclerView, int orientation) {
        recyclerView.addItemDecoration(new DividerItemDecoration(context, orientation));
    }
    //endregion
}
