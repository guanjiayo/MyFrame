## 非粘性事件

```

//接受事件的类
public class YourActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 注册 String 类型事件
        RxBus.getDefault().subscribe(this, new RxBus.Callback<String>() {
            @Override
            public void onEvent(String s) {
                Log.e("eventTag", s);
            }
        });

        // 注册带 tag 为 "my tag" 的 String 类型事件
        RxBus.getDefault().subscribe(this, "my tag", new RxBus.Callback<String>() {
            @Override
            public void onEvent(String s) {
                Log.e("eventTag", s);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 注销
        RxBus.getDefault().unregister(this);
    }
}

---------------------------------

//发送事件的类

// 发送 String 类型事件
RxBus.getDefault().post("without tag");

// 发送带 tag 为 "my tag" 的 String 类型事件
RxBus.getDefault().post("with tag", "my tag");

```

## 粘性事件（也就是先发送事件，在之后注册的时候便会收到之前发送的事件）

```
// 发送 String 类型的粘性事件
RxBus.getDefault().postSticky("without tag");

// 发送带 tag 为 "my tag" 的 String 类型的粘性事件
RxBus.getDefault().postSticky("with tag", "my tag");

-----------------------------------

//接受事件的类
public class YourActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 注册 String 类型事件
        RxBus.getDefault().subscribeSticky(this, new RxBus.Callback<String>() {
            @Override
            public void onEvent(String s) {
                Log.e("eventTag", s);
            }
        });

        // 注册带 tag 为 "my tag" 的 String 类型事件
        RxBus.getDefault().subscribeSticky(this, "my tag", new RxBus.Callback<String>() {
            @Override
            public void onEvent(String s) {
                Log.e("eventTag", s);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 注销
        RxBus.getDefault().unregister(this);
    }
}

```

## 多个事件总线处理

### 把接受者放到一个Manager统一处理

```
public class RxBusManager {

    private static final String MY_TAG = "MY_TAG";

    public static void subscribeRxBusManagerActivity(final RxBusManagerActivity activity){
        RxBus.getDefault().subscribe(activity, new RxBus.Callback<String>() {
            @Override
            public void onEvent(String s) {
                activity.updateText("without " + s);
            }
        });

        RxBus.getDefault().subscribe(activity, MY_TAG, new RxBus.Callback<String>() {
            @Override
            public void onEvent(String s) {
                activity.updateText("with " + s);
            }
        });
    }

    public static void postToRxBusManagerActivity(final String event) {
        RxBus.getDefault().post(event);
    }

    public static void postWithMyTagToRxBusManagerActivity(final String event) {
        RxBus.getDefault().post(event, MY_TAG);
    }

    public static void postStickyToRxBusManagerActivity(final String event) {
        RxBus.getDefault().postSticky(event);
    }

    public static void postStickyWithMyTagToRxBusManagerActivity(final String event) {
        RxBus.getDefault().postSticky(event, MY_TAG);
    }

    public static void unregisterRxBusManagerActivity(final RxBusManagerActivity activity) {
        RxBus.getDefault().unregister(activity);
    }
}
```