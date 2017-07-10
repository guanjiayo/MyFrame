package zs.xmx.netframe.api;
/*
 * @创建者     mqm
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2017/5/18
 * @本类描述	  上传接口配置
 * @内容说明
 * @补充内容
 *
 * ---------------------------------     
 * @更新时间   
 * @新增内容   
 *
 */

import com.wzgiceman.rxretrofitlibrary.retrofit_rx.Api.BaseApi;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import rx.Observable;
import zs.xmx.netframe.IUpload;

public class UploadApi extends BaseApi {
    /*需要上传的文件*/
    private MultipartBody.Part part;

    public UploadApi() {
        setShowProgress(true);
        setMethod("AppYuFaKu/uploadHeadImg");
        setCache(false);
    }

    public MultipartBody.Part getPart() {
        return part;
    }

    public void setPart(MultipartBody.Part part) {
        this.part = part;
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        IUpload httpService = retrofit.create(IUpload.class);
        RequestBody uid= RequestBody.create(MediaType.parse("text/plain"), "4811420");
        RequestBody key = RequestBody.create(MediaType.parse("text/plain"), "2bd467f727cdf2138c1067127e057950");
        return httpService.uploadImage(uid,key,getPart());
    }
}
