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

implementation 'com.github.jiayuliang1314:StrongToolsRecyclerView:2.9'
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
```
## BaseRecyclerViewCallbackAdapter是BaseRecyclerViewCallback的基础实现类，如果只是简单列表，可以只需继承BaseRecyclerViewCallbackAdapter<T> ，覆盖onBindView,getViewRes方法
```
public class BaseRecyclerViewCallbackAdapter<T> implements BaseRecyclerViewCallback<T> {

    @Override
    public void onBindView(BaseViewHolder holder, int position, T item) {

    }

    @Override
    public int getViewRes(int viewType) {
        return 0;
    }
    ...
}
```
## 2.RecyclerViewUtils简化设置LayoutManager，添加Android自带的分割线等操作
```
        //RecyclerViewUtils简化设置LayoutManager，添加Android自带的分割线等操作
        RecyclerViewUtils.setLinearLayoutManager(this, recyclerView, LinearLayoutManager.VERTICAL, false);
        RecyclerViewUtils.addDividerItemDecoration(this, recyclerView, DividerItemDecoration.VERTICAL);
```
## 3.setHasStableIds
//只在有图片的时候，需要设置为true，解决闪烁问题
//setHasStableIds设置为true的时候，getItemId方法需要返回id,不建议返回position位置，应返回item的唯一id，否则插入删除可能混乱

## 4.item的事件位置获取要使用getAdapterPosition()
item的事件点击要使用getAdapterPosition获取当前位置，这是因为DiffUtil在使用的时候，元素位置只是切换，但内容不变的情况下，onBindViewHolder不会调用，onBindViewHolder的参数position也会失效
```
int positionWhenOnClick = holder.getAdapterPosition();
if (positionWhenOnClick == RecyclerView.NO_POSITION) {
return;
}
```

## 5.databinding下代码支持databinding

## 6.不规则使用diffutils可能会出现闪烁问题，反而直接设置元素，notifyDataSetChanged不会出现闪烁
```
    public void setItemsDirectly(List<V> items){
        this.mItems = new ArrayList<V>();
        //防止浅拷贝
        this.mItems.addAll(items);
        notifyDataSetChanged();
    }
```