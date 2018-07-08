package zs.xmx.net.download;

import android.os.AsyncTask;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import zs.xmx.net.RestCreator;
import zs.xmx.net.callback.IError;
import zs.xmx.net.callback.IFailure;
import zs.xmx.net.callback.IRequest;
import zs.xmx.net.callback.ISuccess;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/6/14 16:13
 * @本类描述	  下载处理类
 * @内容说明   //todo 可以在这里把单点续存什么的处理完
 *
 */
public class DownloadHandler {
    private final HashMap<String, Object> PARAMS;
    private final String                  URL;
    private final IRequest                REQUEST;
    private final ISuccess                SUCCESS;
    private final IFailure                FAILURE;
    private final IError                  ERROR;
    private final String                  DOWNLOAD_DIR;
    private final String                  EXTENSION;
    private final String                  FILENAME;

    public DownloadHandler(HashMap<String, Object> params, String url, IRequest request, ISuccess success, IFailure failure, IError error, String downloadDir, String extension, String filename) {
        this.PARAMS = params;
        this.URL = url;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.FILENAME = filename;
    }

    public final void handleDownload() {
        RestCreator.getRestService().download(URL, PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            //开始保存文件(异步任务来做)
                            SaveFileTask task = new SaveFileTask(REQUEST, SUCCESS);
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                                    DOWNLOAD_DIR, EXTENSION, response.body(), FILENAME);
                            //如果下载完成
                            if (task.isCancelled()) {
                                if (REQUEST != null) {
                                    REQUEST.onRequestEnd();
                                }
                            }
                        } else {
                            if (ERROR != null) {
                                ERROR.onError(response.code(), response.message());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (FAILURE != null) {
                            FAILURE.onFailure();
                        }
                    }
                });
    }
}
