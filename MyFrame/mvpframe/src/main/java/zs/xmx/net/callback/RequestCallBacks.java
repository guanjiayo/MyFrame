package zs.xmx.net.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/6/14 12:18
 * @本类描述	  实现Retrofit原来的callback回调,然后封装成我们自己的回调
 * @内容说明
 *
 */
public class RequestCallBacks implements Callback<String> {
    private final IRequest mRequest;
    private final ISuccess mSuccess;
    private final IFailure mFailure;
    private final IError   mError;

    public RequestCallBacks(IRequest request, ISuccess success, IFailure failure, IError error) {
        this.mRequest = request;
        this.mSuccess = success;
        this.mFailure = failure;
        this.mError = error;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) { //请求成功
            if (call.isExecuted()) {
                if (mSuccess != null) {
                    mSuccess.onSuccess(response.body());
                }
            } else {
                if (mError != null) { //请求失败
                    mError.onError(response.code(), response.message());
                }
            }
        }

    }

    /**
     * 请求服务器失败(没网)的情况
     */
    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (mFailure != null) {
            mFailure.onFailure();
        }
        if (mRequest != null) {
            mRequest.onRequestEnd();
        }
    }
}
