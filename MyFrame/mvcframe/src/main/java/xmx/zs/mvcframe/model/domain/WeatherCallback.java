package xmx.zs.mvcframe.model.domain;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import xmx.zs.mvcframe.isolation.net.ICallback;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/4/30 22:20
 * @本类描述	  ${TODO} 让数据直接返回解析过的json数据
 * @内容说明
 *
 */
public abstract class WeatherCallback<T> implements ICallback {

    @Override
    public void onSuccess(String result) {
        Class<? extends T> geneticClass = getGeneticClass(this);
        T t = new Gson().fromJson(result, geneticClass);
        //重定向到新的success函数
        onSuccess(t);
    }

    protected Class<? extends T> getGeneticClass(Object object) {
        //获得带有泛型的直接父类的type   WeatherCallback
        Type genericSuperclass = object.getClass().getGenericSuperclass();
        // ParameterizedType 带参数的类型 泛型
        //getActualTypeArguments 参数的类型 泛型类型
        return (Class<? extends T>) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
    }

    public abstract void onSuccess(T t);
}
