package xmx.zs.mvcframe.base;

import android.text.TextUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ReParam {

    private Map<String, Object> mMap;

    public ReParam() {
        mMap = new HashMap();
    }

    public void put(String key, String value) {
        if (!TextUtils.isEmpty(key)) {
            mMap.put(key, value);
        }
    }

    public void put(String key, File value) {
        if (value != null) {
            mMap.put(key, value);
        }
    }

    public void put(String key, Object value) {
        if (value != null) {
            mMap.put(key, value);
        }
    }

    public void clear(){
        if(mMap.size()>0){
            mMap.clear();
        }
    }

    public String getParamsLog(){
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : mMap.entrySet()) {
            sb.append("&"+entry.getKey()+"="+entry.getValue());
        }
        return sb.toString();
    }

//    public RequestParams getRP(Context mContext, String uri) {
//        RequestParams mParams = new RequestParams(uri);
//        mParams.setMultipart(true);
//        mParams.addBodyParameter("driver_id", CookieUtil.getDriverId());
////        Logger.e(CookieUtil.getDriverId());
//        if (mMap != null && mMap.size() > 0) {
//            for (String key : mMap.keySet()) {
//                Object file = mMap.get(key);
//                if (file instanceof File) {
//                    mParams.addBodyParameter(key, file, null);
//                } else {
//                    mParams.addBodyParameter(key, file.toString());
//                }
//            }
//        }
//        StringBuilder sbCookie = new StringBuilder();
//        sbCookie.append(SharedPreUtil.getString(mContext,SharedPreUtil.COOKIE)).append("; path=/; domain=");
//        mParams.setUseCookie(true);
//        mParams.addHeader("Cookie", sbCookie.toString());
////        Logger.e("sbCookie=" + sbCookie.toString());
//        return mParams;
//    }
}
