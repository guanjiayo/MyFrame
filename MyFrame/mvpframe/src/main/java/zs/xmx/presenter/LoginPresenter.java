package zs.xmx.presenter;
/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2017/8/2
 * @本类描述	  LoginPresenter
 * @内容说明   与登陆相关的业务处理
 * @补充内容
 *
 * ---------------------------------     
 * @更新时间   
 * @新增内容   
 *
 */


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zs.xmx.test.User;
import zs.xmx.test.UserLoginNet;
import zs.xmx.constant.UrlConstant;
import zs.xmx.model.domian.net.ResponseInfo;
import zs.xmx.presenter.api.IResponseInfoApi;
import zs.xmx.view.activity.LoginActivity;

public class LoginPresenter {
    private       LoginActivity    activity;
    private final IResponseInfoApi mResponseInfoApi;

    public LoginPresenter(LoginActivity activity) {
        this.activity = activity;


        /**
         * 访问网络逻辑
         **/
        //第一步:创建Builder,指定baseUrl和数据解析器
        Retrofit.Builder mBuilder = new Retrofit.Builder()
                .baseUrl(UrlConstant.BASEURL)
                .addConverterFactory(GsonConverterFactory.create());//Gson解析

        //第二步:创建Retrofit
        Retrofit mRetrofit = mBuilder.build();

        //第三步:指定请求方式(get或post)和参数,通过接口的形式指定
        //第四部:将Retrofit和第三步的联网参数联系起来
        mResponseInfoApi = mRetrofit.create(IResponseInfoApi.class);

    }

    /**
     * 用户登陆
     *
     * @param username
     * @param password
     */
    public void login(String username, String password) {
        Call<ResponseInfo> infoCall = mResponseInfoApi.login(username, password);
        infoCall.enqueue(new Callback<ResponseInfo>() {
            @Override
            public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {
                //处理服务器返回的内容
                if (response != null) {
                    if (response.isSuccessful()) {
                        //登陆成功
                        activity.success();
                    } else {
                        //登陆失败,错误提示
                    }
                } else {
                    //登陆失败,错误提示
                    onFailure(call, new RuntimeException("服务器忙,请稍后重试"));
                }
            }

            @Override
            public void onFailure(Call<ResponseInfo> call, Throwable t) {
                activity.failed();
            }
        });
    }

    //TODO 测试用的
    public void login1(String username, String password) {
        final User user = new User();
        user.username = username;
        user.password = password;
        new Thread() {
            @Override
            public void run() {
                UserLoginNet net = new UserLoginNet();
                if (net.sendUserLoginInfo(user)) {
                    activity.success();
                } else {
                    activity.failed();
                }
            }
        }.start();
    }


}
