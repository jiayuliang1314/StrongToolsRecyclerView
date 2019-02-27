StrongToolsRecyclerView简化RecyclerView使用
=============================================

## 使用方法：
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

implementation 'com.github.jiayuliang1314:StrongToolsRecyclerView:1.8'
```

## 1.简化RecyclerView.Adapter的创建,BaseRecyclerViewCallback包含了方法，只需实现此接口
```
public interface BaseRecyclerViewCallback<T> {
    /**
     * 绑定View，为view设置监听事件，显示view
     *
     * @param holder
     * @param position
     * @param item
     */
    void onBindView(BaseViewHolder holder, int position, T item);

    int getViewRes();

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
}
```
## 2.RecyclerViewUtils简化设置LayoutManager，添加Android自带的分割线等操作
```
        //RecyclerViewUtils简化设置LayoutManager，添加Android自带的分割线等操作
        RecyclerViewUtils.setLinearLayoutManager(this, recyclerView, LinearLayoutManager.VERTICAL, false);
        RecyclerViewUtils.addDividerItemDecoration(this, recyclerView, DividerItemDecoration.VERTICAL);
```
## 3.SwipeView可以左滑删除，参考 https://github.com/xytyl/SwipeView
*** 此处bug，SwipeView包含的子view居中问题，SwipeView包含的子view layout_margin失效，还未发现原因
```

    <com.strong.tools.recyclerview.SwipeView
        android:id="@+id/swip"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <RelativeLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:paddingTop="15dp"
            android:paddingBottom="15dp">

            <TextView
                android:id="@+id/text"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_alignParentLeft="true"
                android:layout_centerVertical="true"
                android:layout_marginRight="43dp"
                android:gravity="left"
                android:text=""
                android:textColor="#000000"
                android:textSize="16dp"
                tools:text="test" />

            <ImageView
                android:id="@+id/icon"
                android:layout_width="23dp"
                android:layout_height="23dp"
                android:layout_alignParentRight="true"
                android:layout_alignParentBottom="true"
                android:layout_centerVertical="true"
                android:src="@mipmap/ic_launcher" />
        </RelativeLayout>

        <LinearLayout
            android:id="@+id/delete"
            android:layout_width="80dp"
            android:layout_height="match_parent"
            android:background="#FF5A5F"
            android:gravity="center"
            android:orientation="horizontal">

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:gravity="center"
                android:text="Delete"
                android:textColor="#ffffff"
                android:textSize="17sp" />
        </LinearLayout>
    </com.strong.tools.recyclerview.SwipeView>
```