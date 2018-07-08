package zs.xmx.net.Rx;

import java.io.File;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/6/14 12:05
 * @本类描述	  RestClientBuilder和RestClient写成建造者模式让外部类链式调用
 * @内容说明   结合RxJava使用
 *
 */
public class RxRestClientBuilder {
    private HashMap<String, Object> mParams;
    private String                  mUrl;
    private RequestBody             mBody;
    //上传与下载
    private File                    mFile;

    RxRestClientBuilder() {

    }

    public final RxRestClientBuilder lastUrl(String url) {
        this.mUrl = url;
        return this;
    }

    public final RxRestClientBuilder params(HashMap<String, Object> params) {
        this.mParams = params;
        return this;
    }

    public final RxRestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    public final RxRestClientBuilder uploadFile(File file) {
        this.mFile = file;
        return this;
    }

    /**
     * 上传文件路径
     *
     * @param filePath
     * @return
     */
    public final RxRestClientBuilder uploadFile(String filePath) {
        this.mFile = new File(filePath);
        return this;
    }


    public final RxRestClient build() {
        return new RxRestClient(mParams, mUrl, mBody, mFile);
    }
}
