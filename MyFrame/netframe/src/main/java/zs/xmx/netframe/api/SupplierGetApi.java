package zs.xmx.netframe.api;

import com.wzgiceman.rxretrofitlibrary.retrofit_rx.Api.BaseApi;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Retrofit;
import rx.Observable;
import zs.xmx.netframe.ISupplierGetService;
import zs.xmx.netframe.constants.MyConstants;

/*
 * @创建者     mqm
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2017/5/22
 * @本类描述	  供应商接口
 * @内容说明   ${TODO}
 * @补充内容
 *
 * ---------------------------------     
 * @更新时间   
 * @新增内容   
 *
 */

public class SupplierGetApi extends BaseApi {
    //要请求的参数
    private String supplierUrl = MyConstants.supplierUrl;
    private String Secet       = "aa67229a0a71e28ebcde8e0f5bbce253";
    private String Key         = "ManufactorApk";
    private String pwdCodeOne  = "oyicoderdengmembe[";
    private String pwdCodeTwo  = "]rpass";
    private String RgbKey      = "ManufactorKey";
    private String RgbIv       = "ManufactorIv";
    private String code        = "Manufactor";
    private String time        = getCurrentTime();

    /**
     * 默认初始化需要给定回调和rx周期类
     * 可以额外设置请求设置加载框显示，回调等（可扩展）
     */
    public SupplierGetApi() {
        setBaseUrl(supplierUrl);//baseUrl
        setCache(true);//设置缓存处理
        setMethod("SUPPLIERURL_GET");//接口标记

    }

    public static String getMD5(String val) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(val.getBytes());
        byte[] m = md5.digest();//加密
        return getString(m);
    }

    private static String getString(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            sb.append(b[i]);
        }
        return sb.toString();
    }

    public String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        ISupplierGetService supplierGetService = retrofit.create(ISupplierGetService.class);
        return supplierGetService.getDataList(null);
    }
}
