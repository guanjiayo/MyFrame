package zs.xmx.net.Rx;

import java.io.File;
import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import zs.xmx.net.HttpMethod;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/6/14 12:05
 * @本类描述	  RestClientBuilder和RestClient写成建造者模式让外部类链式调用
 *            (这边结合RxJava实现)
 * @内容说明   真正外部使用的是这个类:
 *            HashMap params=new HashMap();
 *            RxRestClient.create()
 *            .url("域名后面的")
 *            .params(params)
 *            .build()
 *            .get()
 *            .subscribeOn(Schedulers.io()){
 *
 *            }
 *
 */
public class RxRestClient {
    private final HashMap<String, Object> mParams;
    private final String                  mUrl;
    private final RequestBody             mBody;
    //上传与下载
    private final File                    FILE;

    public RxRestClient(HashMap<String, Object> params,
                        String url,
                        RequestBody body,
                        File file) {
        this.mParams = params;
        this.mUrl = url;
        this.mBody = body;
        this.FILE = file;
    }

    public static RxRestClientBuilder create() {
        return new RxRestClientBuilder();
    }

    //todo 这里剩了几个方法没实现,看Retrofit文档实现回来
    //Retrofit实现请求数据
    private Observable<String> request(HttpMethod method) {
        final RxRestService service = RxRestCreator.getRxRestService();
        Observable<String> observable = null;
        switch (method) {
            case GET:
                observable = service.get(mUrl, mParams);
                break;
            case POST:
                observable = service.post(mUrl, mParams);
                break;
            case PUT:
                observable = service.put(mUrl, mParams);
                break;
            case DELETE:
                observable = service.delete(mUrl, mParams);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData(
                        "file", FILE.getName(), requestBody);
                observable = service.upload(mUrl, body);
                break;
            case PUT_RAW:
                break;
            case POST_RAW:
                break;
            default:
                break;
        }
        return observable;

    }

    //下面是各种请求
    public final Observable<String> get() {
        return request(HttpMethod.GET);
    }

    public final Observable<String> post() {
        return request(HttpMethod.POST);
    }

    public final Observable<String> put() {
        return request(HttpMethod.PUT);
    }

    public final Observable<String> delete() {
        return request(HttpMethod.DELETE);
    }

    public final Observable<ResponseBody> download() {
        return RxRestCreator.getRxRestService().download(mUrl, mParams);
    }

    public final Observable<String> upload() {
        return request(HttpMethod.UPLOAD);
    }
}
