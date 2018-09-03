package zs.xmx.mvpframe.net.retrofit_normal;

import java.io.File;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import zs.xmx.mvpframe.net.HttpMethod;
import zs.xmx.mvpframe.net.callback.IError;
import zs.xmx.mvpframe.net.callback.IFailure;
import zs.xmx.mvpframe.net.callback.IRequest;
import zs.xmx.mvpframe.net.callback.ISuccess;
import zs.xmx.mvpframe.net.callback.RequestCallBacks;
import zs.xmx.mvpframe.net.download.DownloadHandler;


/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/6/14 12:05
 * @本类描述	  RestClientBuilder和RestClient写成建造者模式让外部类链式调用
 * @内容说明   真正外部使用的是这个类:
 *            HashMap params=new HashMap();
 *            RestClient.create()
 *            .url("域名后面的")
 *            .params(params)
 *            .build()
 *            .get();
 *
 */
public class RestClient {
    private final HashMap<String, Object> mParams;
    private final String                  mUrl;
    private final IRequest                mRequest;
    private final ISuccess                mSuccess;
    private final IFailure                mFailure;
    private final IError                  mError;
    private final RequestBody             mBody;
    //上传与下载
    private final File                    FILE;
    private final String                  DOWNLOAD_DIR;
    private final String                  EXTENSION;
    private final String                  FILENAME;

    public RestClient(HashMap<String, Object> params,
                      String url,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      File file,
                      String download_dir,
                      String extension,
                      String filename) {
        this.mParams = params;
        this.mUrl = url;
        this.mRequest = request;
        this.mSuccess = success;
        this.mFailure = failure;
        this.mError = error;
        this.mBody = body;
        this.FILE = file;
        this.DOWNLOAD_DIR = download_dir;
        this.EXTENSION = extension;
        this.FILENAME = filename;
    }

    public static RestClientBuilder create() {
        return new RestClientBuilder();
    }

    //todo 这里剩了几个方法没实现,看Retrofit文档实现回来
    //Retrofit实现请求数据
    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if (mRequest != null) {
            //开始请求服务器数据
            mRequest.onRequestStart();
        }
        switch (method) {
            case GET:
                call = service.get(mUrl, mParams);
                break;
            case POST:
                call = service.post(mUrl, mParams);
                break;
            case PUT:
                call = service.put(mUrl, mParams);
                break;
            case DELETE:
                call = service.delete(mUrl, mParams);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData(
                        "file", FILE.getName(), requestBody);
                call = service.upload(mUrl, body);
                break;
            case DOWNLOAD:
                new DownloadHandler(mParams, mUrl, mRequest, mSuccess,
                        mFailure, mError, DOWNLOAD_DIR,
                        EXTENSION, FILENAME).handleDownload();
                break;
            case PUT_RAW:
                break;
            case POST_RAW:
                break;
            default:
                break;
        }
        if (call != null) {
            call.enqueue(getRequestCallback());
        }

    }

    private Callback<String> getRequestCallback() {
        return new RequestCallBacks(mRequest, mSuccess, mFailure, mError);
    }

    //下面是各种请求
    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    public final void download() {
        request(HttpMethod.DOWNLOAD);
    }

    public final void upload() {
        request(HttpMethod.UPLOAD);
    }
}
