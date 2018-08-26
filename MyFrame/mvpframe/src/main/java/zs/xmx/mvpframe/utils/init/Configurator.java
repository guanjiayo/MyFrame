package zs.xmx.mvpframe.utils.init;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/6/11 0:42
 * @本类描述	  全局配置文件
 * @内容说明
 *
 */
public class Configurator {
    private static final HashMap<Object, Object> CONFIGS      = new HashMap<>();
    private static final ArrayList<Interceptor>  INTERCEPTORS = new ArrayList<>();

    /**
     * 静态内部类单例,特别在请求网络时要处理并发问题
     **/
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    private Configurator() {
        CONFIGS.put(ConfigKeys.CONFIG_READY.name(), false);
    }

    //------------上面三句,静态单例-----------

    /**
     * 获取配置信息
     **/
    final HashMap<Object, Object> getConfigs() {
        return CONFIGS;
    }

    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) CONFIGS.get(key);
    }

    /**
     * 配置API主机名(返回自己,实现链式调度)
     **/
    public final Configurator withApiHost(String Host) {
        CONFIGS.put(ConfigKeys.API_HOST, Host);
        return this;
    }

    /**
     * 设置拦截器(可以处理多个API Host的情景)
     **/
    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    /**
     * 检查配置是否完成
     */
    private void checkConfiguration() {
        final boolean isReady = (boolean) CONFIGS.get(ConfigKeys.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure()");
        }
    }

    /**
     * 配置完成
     * <p>
     * 结尾必须要配置
     **/
    public final void configure() {
        CONFIGS.put(ConfigKeys.CONFIG_READY.name(), true);
    }
}

