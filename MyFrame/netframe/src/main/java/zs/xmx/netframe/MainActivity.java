package zs.xmx.netframe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.google.gson.Gson;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.SSLSocketFactory1;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.http.HttpManager;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.upload.ProgressRequestBody;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.upload.UploadProgressListener;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import zs.xmx.netframe.api.SubjectGetApi;
import zs.xmx.netframe.api.SubjectHttpsApi;
import zs.xmx.netframe.api.SubjectPostApi;
import zs.xmx.netframe.api.UploadApi;
import zs.xmx.netframe.base.BaseActivity;
import zs.xmx.netframe.domain.DataResult;

/**
 * //TODO 该框架有多少API要写多少个API类,只能统一API请求设置
 * 如何统一处理请求头（例如tokean过期之类的需求）
 * <p>
 * 比如tokean过期这样的处理，可以将失败的统一处理逻辑放入到RxJava的flatMap中处理
 * <p>
 * 可以进群，群文件分享中有此功能的解决demo，可以自行查看；不懂可以问群里的兄弟
 * <p>
 * 自行下载研究github：https://github.com/wzgiceman/RxRetrofit-tokean
 * //TODO Fragment中如何使用HttpManager
 * <p>
 * 可调用(RxAppCompatActivity)getContext()得到宿主RxAppCompatActivity
 * <p>
 * new HttpManager(this,(RxAppCompatActivity)getContext());
 * 如何在Service中调用
 * <p>
 * service里面一般都是无生命周期的循环调用,可以copy一个HttpManager出来，去掉RxAppCompatActivity参数就可以了,以及注释掉Ober对象中的
 * <p>
 * xxx.compose(appCompatActivity.get().bindUntilEvent(ActivityEvent.DESTROY))
 */
public class MainActivity extends BaseActivity implements HttpOnNextListener, View.OnClickListener {

    private String TAG = MainActivity.class.getSimpleName();
    private Button         mBtn_get;
    private Button         mBtn_post;
    private TextView       mTv_data;
    private HttpManager    mHttpManager;
    private SubjectPostApi mPostApi;
    private SubjectGetApi  mGetApi;

    private TextView mTv_netStata;

    private SubjectHttpsApi   mHttpsApi;
    private Button            mBtn_https;
    private InputStream       mTest_client;
    private InputStream       mCertificates;
    private Button            mBtn_upload;
    private Button            mBtn_download;
    private UploadApi         mUplaodApi;
    private NumberProgressBar mProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }


    /**
     * 初始化数据
     */
    private void initData() {
        mHttpManager = new HttpManager(this, this);
        mPostApi = new SubjectPostApi();
        mGetApi = new SubjectGetApi();
        mHttpsApi = new SubjectHttpsApi();
        /*上传接口内部接口有token验证，所以需要换成自己的接口测试，检查file文件是否手机存在*/
        mUplaodApi = new UploadApi();
        File file = new File("/storage/emulated/0/Download/timg.jpeg");
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file_name", file.getName(), new ProgressRequestBody
                (requestBody,
                        new UploadProgressListener() {
                            @Override
                            public void onProgress(long currentBytesCount, long totalBytesCount) {
                                mProgressBar.setMax((int) totalBytesCount);
                                mProgressBar.setProgress((int) currentBytesCount);
                            }
                        }));
        mUplaodApi.setPart(part);

        //TODO 想办法把证书的数据流传给Factory
        try {
            //拿到证书文件
            mCertificates = getResources().getAssets().open("wea.cer");
            //拿到bks文件
            mTest_client = getResources().getAssets().open("test_client");
            SSLSocketFactory1.getSslSocketFactory(new InputStream[]{mCertificates}, mTest_client, "123456");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mOnAssertsListener != null) {
            mOnAssertsListener.getAssertFile(new InputStream[]{mCertificates}, mTest_client);
        }

    }

    //TODO
    public interface onAssertsListener {
        void getAssertFile(InputStream[] certificates, InputStream bks);
    }

    private onAssertsListener mOnAssertsListener;

    public void setOnAssertsListener(onAssertsListener listener) {
        this.mOnAssertsListener = listener;
    }

    /**
     * 初始化界面
     */
    private void initView() {
        mBtn_get = (Button) findViewById(R.id.btn_get);
        mBtn_post = (Button) findViewById(R.id.btn_post);
        mBtn_https = (Button) findViewById(R.id.btn_https);
        mBtn_upload = (Button) findViewById(R.id.btn_upload);
        mBtn_download = (Button) findViewById(R.id.btn_download);
        mTv_data = (TextView) findViewById(R.id.tv_data);
        mTv_netStata = (TextView) findViewById(R.id.tv_netStata);
        mBtn_get.setOnClickListener(this);
        mBtn_post.setOnClickListener(this);
        mBtn_https.setOnClickListener(this);
        mBtn_upload.setOnClickListener(this);
        mBtn_download.setOnClickListener(this);
        mProgressBar = (NumberProgressBar) findViewById(R.id.number_progress_bar);

    }

    /**
     * 通过Method参数判断接口,动态解析返回数据
     *
     * @param resulte
     * @param method
     */
    @Override
    public void onNext(String resulte, String method) {

        if (method.equals(mPostApi.getMethod())) {
            //解析JSON字符串
            Gson gson = new Gson();
            DataResult dataResult = gson.fromJson(resulte, DataResult.class);
            String city = dataResult.getHeWeather5().get(0).getBasic().getCity();
            mTv_data.setText("city ==" + city + "\n" + "POST返回json数据: \n" + resulte);
        } else if (method.equals(mGetApi.getMethod())) {
            //解析JSON字符串
            Gson gson = new Gson();
            DataResult dataResult = gson.fromJson(resulte, DataResult.class);
            String city = dataResult.getHeWeather5().get(0).getBasic().getCity();
            mTv_data.setText("city ==" + city + "\n" +
                    "GET返回json数据: \n" + resulte);
        } else if (method.equals(mHttpsApi.getMethod())) {
            mTv_data.setText(resulte);
        }

    }

    @Override
    public void onError(ApiException e) {
        mTv_data.setText("Failed: \n" + "异常== " + e.getMessage() +
                "\n" + "错误码== " + e.getCode() + "\n"
                + "错误信息== " + e.getDisplayMessage());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get:
                mHttpManager.doHttpDeal(mGetApi);
                break;
            case R.id.btn_post:
                mHttpManager.doHttpDeal(mPostApi);
                break;
            case R.id.btn_https:
                mHttpManager.doHttpDeal(mHttpsApi);
                break;
            case R.id.btn_upload:
                mHttpManager.doHttpDeal(mUplaodApi);
                break;
            case R.id.btn_download:
                startActivity(new Intent(this, DownLoadActivity.class));
                break;
        }
    }


    @Override
    public void onNetWorkStataChanged(String mNetWorkTypeName) {
        super.onNetWorkStataChanged(mNetWorkTypeName);
        if (mNetWorkTypeName.equals("NETWORK_UNKNOWN") || mNetWorkTypeName.equals("NETWORK_NO")) { //没网
            //显示TextView
            mTv_netStata.setVisibility(View.VISIBLE);
            mTv_netStata.setText("当前网络不可用");
        } else {
            mTv_netStata.setVisibility(View.GONE);
        }
    }
}
