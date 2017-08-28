package xmx.zs.mvcframe.base;
/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2017/8/26
 * @本类描述	  参考别人的基类封装
 * @内容说明   ${TODO}
 * @补充内容
 *
 * ---------------------------------     
 * @更新时间   
 * @新增内容   
 *
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class RootActivity extends AppCompatActivity implements IRoot {

   // private LoadingDialog       mDialog;
    private Bundle              savedInstanceState;
    private boolean isGet = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        initData();
    }


    protected Bundle getSaveBundle() {
        return savedInstanceState;
    }

    protected void setOnStart(boolean is) {
        this.isGet = is;
    }

    @Override
    public void doRequest(final String action, ReParam params, final int type,
                          String title, final boolean dialog) {
//        if (!NetWorkUtil.checkNet(this)) {
//            onFailure(getString(R.string.check_network));
//            return;
//        }
//        if (dialog) {
//            createDialog(title);
//        }
//        if (params == null) {
//            params = new ReParam();
//        }
//        final RequestParams mParams = params.getRP(this, action);
//        final String paramsLog = params.getParamsLog();
//        mCancelable = x.http().post(mParams, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                Log.i("driver", action + "   " + paramsLog + "   " + result);
//                if (dialog) {
//                    closeDialog();
//                }
//                DbCookieStore instance = DbCookieStore.INSTANCE;
//                List<HttpCookie> cookies = instance.getCookies();
//                if (cookies.size() > 0) {
//                    String cookieString = cookies.toString().substring(1, cookies.toString().length() - 1);
//                    cookieString = cookieString.replaceAll(",", ";");
//                    SharedPreUtil.saveString(RootApp.getApp().getApplicationContext(), SharedPreUtil.COOKIE, cookieString);
//                    //                    Logger.e("cooikerTag是多少=" + cookieString);
//                }
//                String msg = getMsg(result);
//                if (msg.contains("过期")) {
//                    Map data = getContent(result);
//                    String login = getData(data, "login");
//                    if ("timeout".equals(login)) {
//                        SharedPreUtil.clearString(RootApp.getApp().getApplicationContext(), SharedPreUtil.COOKIE);
//                        instance.removeAll();
//                        ToastUtil.show(RootActivity.this, msg);
//                        IntentUtil.jump(RootActivity.this, LoginAct.class);
//                        return;
//                    }
//                }
//                onComplete(result, type);
//            }

//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                if (dialog) {
//                    closeDialog();
//                }
//                onFailure("网络开小差了，请稍后再试哦！" + ex.getMessage());
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//                if (dialog) {
//                    closeDialog();
//                }
//                // onFailure("您已经取消请求");
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
//    }
//
//    public void doRequest(String action, ReParam params, int type, boolean dialog) {
//        doRequest(action, params, type, "数据加载中...", dialog);
//    }
//
//    public void doRequest(String action, ReParam params, int type) {
//        doRequest(action, params, type, null, false);
//    }
//
//    public void doRequest(String action, ReParam params) {
//        doRequest(action, params, 0, null, false);
//    }
//
//    public void doRequest(String action, int type) {
//        doRequest(action, null, type, null, false);
//    }
//
//    public void doRequest(String action) {
//        doRequest(action, null, 0, null, false);
//    }

//    @Override
//    public Map getContent(String result) {
//        return ResultUitl.getContent(result);
//    }
//
//    @Override
//    public List<Map> getLists(String result) {
//        return ResultUitl.getLists(result);
//    }
//
//    public List<Map> getRowsList(String result) {
//        Map map = getContent(result);
//        return ResultUitl.getTargetLists(map.toString(), "rows");
//    }
//
//    public String getString(String result, String dataName) {
//        return ResultUitl.getString(result, dataName);
//    }
//
//    @Override
//    public boolean isSuccess(String result) {
//        return ResultUitl.isSuccess(result);
//    }
//
//    public String getStatecodeStr(String result) {
//        return ResultUitl.getStatecodeStr(result);
//    }
//
//    public String getMsg(String result) {
//        return ResultUitl.getMsg(result);
//    }
//
//    public String getData(Map data, String key) {
//        return ResultUitl.getData(data, key);
//    }

//    public void createDialog(String title) {
//        if (mDialog == null) {
//            mDialog = new LoadingDialog(this, title);
//        }
//        mDialog.show();
//    }
//
//    // 关闭对话框
//    public void closeDialog() {
//        try {
//            if (mDialog != null && mDialog.isShowing()) {
//                mDialog.dismiss();
//                mDialog = null;
//            }
//        } catch (Exception e) {
//            mDialog = null;
//        }
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        if (mCancelable != null) {
//            mCancelable.cancel();
//            mCancelable = null;
//        }
   }







}
