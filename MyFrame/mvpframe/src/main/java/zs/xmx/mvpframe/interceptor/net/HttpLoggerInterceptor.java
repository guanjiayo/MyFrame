package zs.xmx.mvpframe.interceptor.net;

import com.orhanobut.logger.Logger;

import okhttp3.logging.HttpLoggingInterceptor;
import zs.xmx.mvpframe.utils.JsonUtils;


/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/9/5 10:52
 * @本类描述	  网络请求拦截器
 * @内容说明
 *
 */
public class HttpLoggerInterceptor implements HttpLoggingInterceptor.Logger {

    private StringBuilder mMessage = new StringBuilder();

    @Override
    public void log(String message) {
        // 请求或者响应开始
        if (message.startsWith("--> POST")) {
            mMessage.setLength(0);
        }
        // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
        if ((message.startsWith("{") && message.endsWith("}"))
                || (message.startsWith("[") && message.endsWith("]"))) {
            message = JsonUtils.formatJson(JsonUtils.decodeUnicode(message));
        }
        mMessage.append(message.concat("\n"));
        // 响应结束，打印整条日志
        if (message.startsWith("<-- END HTTP")) {
            Logger.d(mMessage.toString());
        }


    }
}
