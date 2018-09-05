package zs.xmx.mvpframe.interceptor.net;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/9/5 12:26
 * @本类描述	   Url请求Body加密拦截器
 * @内容说明   todo 优化成外部重定向Url,具体参数看注释
 *            todo 看下是否做到网路请求类里面
 *
 */
public class HttpUrlBodyInterceptor  {
//    private String newHost = "127.0.0.1";
//
//
//    public static String requestBodyToString(RequestBody requestBody) throws IOException {
//        Buffer buffer = new Buffer();
//        requestBody.writeTo(buffer);
//        return buffer.readUtf8();
//    }
//
//    @Override
//    public Response intercept(Chain chain) throws IOException {
//        Request request = chain.request();
//        HttpUrl url = request.url();
//
//        //http://127.0.0.1/test/upload/img?userName=xiaoming&userPassword=12345
//        String scheme = url.scheme();//  http https
//        String host = url.host();//   127.0.0.1
//        String path = url.encodedPath();//  /test/upload/img
//        String query = url.encodedQuery();//  userName=xiaoming&userPassword=12345
//
//        StringBuffer sb = new StringBuffer();
//        sb.append(scheme).append(newHost).append(path).append("?");
//        Set<String> queryList = url.queryParameterNames();
//        Iterator<String> iterator = queryList.iterator();
//
//        for (int i = 0; i < queryList.size(); i++) {
//
//            String queryName = iterator.next();
//            sb.append(queryName).append("=");
//            String queryKey = url.queryParameter(queryName);
//            //对query的key进行加密
//          //  sb.append(CommonUtils.getMD5(queryKey));
//            if (iterator.hasNext()) {
//                sb.append("&");
//            }
//        }
//
//
//        String newUrl = sb.toString();
//
//
//        RequestBody body = request.body();
//        String bodyToString = requestBodyToString(body);
//        TestBean testBean = GsonTools.changeGsonToBean(bodyToString, TestBean.class);
//        String userPassword = testBean.getUserPassword();
//        //加密body体中的用户密码
//        testBean.setUserPassword(CommonUtils.getMD5(userPassword));
//
//        String testGsonString = GsonTools.createGsonString(testBean);
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), testGsonString);
//
//
//        Request.Builder builder = request.newBuilder()
//                .post(requestBody)
//                .url(newUrl);
//
//        return chain.proceed(builder.build());
//    }

}
