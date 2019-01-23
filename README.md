# StrongToolsRecyclerView简化RecyclerView使用

## 使用方法：
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

implementation 'com.github.jiayuliang1314:StrongToolsRecyclerView:1.1'

## 1.简化RecyclerView.Adapter的创建,BaseRecyclerViewCallback包含了方法，只需实现此接口
```
public interface BaseRecyclerViewCallback<T> {
    void onBindView(BaseViewHolder holder, int position, T item);

    void onClickItem(View view, int position, T item);

    boolean onLongClickItem(View view, int position, T item);

    int getViewRes();
}
```
## 2.RecyclerViewUtils简化设置LayoutManager，添加Android自带的分割线等操作
```
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BaseRecyclerViewAdapter suggestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.suggestList);

        //简化RecyclerView.Adapter的创建
        suggestAdapter = new BaseRecyclerViewAdapter();
        suggestAdapter.setBaseRecyclerViewCallback(new BaseRecyclerViewCallback<String>() {
            @Override
            public void onBindView(BaseViewHolder holder, int position, String item) {
                TextView textView = (TextView) holder.getView(R.id.text);
                textView.setText(item);
            }

            @Override
            public void onClickItem(View view, int position, String item) {
                Toast.makeText(MainActivity.this, "onClickItem", Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onLongClickItem(View view, int position, String item) {
                return false;
            }

            @Override
            public int getViewRes() {
                return R.layout.suggest_message_view_item;
            }
        });
        List<String> list = new ArrayList<>();
        list.add("123");
        list.add("234");
        list.add("345");
        suggestAdapter.setItems(list);

        //RecyclerViewUtils简化设置LayoutManager，添加Android自带的分割线等操作
        RecyclerViewUtils.setLayoutManager(this, recyclerView, RecyclerViewUtils.LinearLayoutManagerVertical);
        RecyclerViewUtils.addItemDecoration(this, recyclerView, RecyclerViewUtils.DividerItemDecorationVertical);
        recyclerView.setAdapter(suggestAdapter);
    }
}

```
