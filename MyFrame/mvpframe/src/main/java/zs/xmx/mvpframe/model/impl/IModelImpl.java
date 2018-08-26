package zs.xmx.mvpframe.model.impl;

import java.util.HashMap;

import io.reactivex.functions.Function;
import zs.xmx.mvpframe.model.IModel;
import zs.xmx.mvpframe.net.RestClient;
import zs.xmx.mvpframe.net.Rx.databus.RxBus;
import zs.xmx.mvpframe.net.callback.ISuccess;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/8/26 12:08
 * @本类描述	  获取网络数据的实现类
 * @内容说明   //todo 什么拦截器啊,数据库操作到时全放在这操作
 *
 */
public class IModelImpl implements IModel {
    @Override
    public void loadDataFromNet(HashMap<String, Object> params) {
        RestClient.create()
                .lastUrl("/xiandu")
                .params(params)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(final String response) {
                        RxBus.getInstance().chainProcess(new Function() {


                            @Override
                            public Object apply(Object o) throws Exception {

                                return response;
                            }
                        });
                    }
                })
                .build()
                .post();

    }


}
