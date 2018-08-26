package zs.xmx.mvpframe.net;

import java.io.File;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import zs.xmx.mvpframe.net.callback.IError;
import zs.xmx.mvpframe.net.callback.IFailure;
import zs.xmx.mvpframe.net.callback.IRequest;
import zs.xmx.mvpframe.net.callback.ISuccess;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/6/14 12:05
 * @本类描述	  RestClientBuilder和RestClient写成建造者模式让外部类链式调用
 * @内容说明
 *
 */
public class RestClientBuilder {
    private HashMap<String, Object> mParams;
    private String                  mUrl;
    private IRequest                mRequest;
    private ISuccess                mSuccess;
    private IFailure                mFailure;
    private IError                  mError;
    private RequestBody             mBody;
    //上传与下载
    private File                    mFile;
    private String                  mDownloadDir;
    private String                  mExtension;
    private String                  mFilename;

    RestClientBuilder() {

    }

    public final RestClientBuilder lastUrl(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(HashMap<String, Object> params) {
        this.mParams = params;
        return this;
    }

    public final RestClientBuilder request(IRequest request) {
        this.mRequest = request;
        return this;
    }

    public final RestClientBuilder success(ISuccess success) {
        this.mSuccess = success;
        return this;
    }

    public final RestClientBuilder failure(IFailure failure) {
        this.mFailure = failure;
        return this;
    }

    public final RestClientBuilder error(IError error) {
        this.mError = error;
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    /**
     * 上传文件
     * @param file
     * @return
     */
    public final RestClientBuilder uploadFile(File file) {
        this.mFile = file;
        return this;
    }

    /**
     * 上传文件路径
     * @param filePath
     * @return
     */
    public final RestClientBuilder uploadFile(String filePath) {
        this.mFile = new File(filePath);
        return this;
    }

    /**
     * 下载目录
     *
     * @param dir
     * @return
     */
    public final RestClientBuilder downDir(String dir) {
        this.mDownloadDir = dir;
        return this;
    }

    /**
     * 下载文件名
     *
     * @param fileName
     * @return
     */
    public final RestClientBuilder downFileName(String fileName) {
        this.mFilename = fileName;
        return this;
    }

    /**
     * 下载文件扩展名
     * @param extension
     * @return
     */
    public final RestClientBuilder downExtension(String extension) {
        this.mExtension = extension;
        return this;
    }


    public final RestClient build() {
        return new RestClient(mParams, mUrl, mRequest, mSuccess, mFailure, mError, mBody, mFile, mDownloadDir, mExtension, mFilename);
    }
}
