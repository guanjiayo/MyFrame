package zs.xmx.mvpframe.model.impl;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import zs.xmx.mvpframe.bus.rx_todo.RxBus_todo;
import zs.xmx.mvpframe.model.IModel;
import zs.xmx.mvpframe.net.callback.ISuccess;
import zs.xmx.mvpframe.net.retrofit_normal.RestClient;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/8/26 12:08
 * @本类描述	  获取网络数据的实现类
 * @内容说明   //todo 什么拦截器啊,数据库操作到时全放在这操作
 * //todo 将这里封装的方法放到我们的isolation方法中去
 *
 */
public class IModelImpl implements IModel {

    @Override
    public void loadDataFromNet(@NotNull String lastUrl, @NotNull HashMap<String, Object> params) {
        RestClient.create()
                .lastUrl(lastUrl)
                .params(params)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(final String response) {
                        //                        RxBus_todo.getDefault().chainProcess(new Function() {
                        //
                        //
                        //                            @Override
                        //                            public Object apply(Object o) throws Exception {
                        //
                        //                                return response;
                        //                            }
                        //                        });
                        RxBus_todo.getDefault().post(response);

                    }
                })
                .build()
                .post();
    }
}
