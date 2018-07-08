package zs.xmx.model;

import java.util.HashMap;

import io.reactivex.functions.Function;
import zs.xmx.net.RestClient;
import zs.xmx.net.Rx.databus.RxBus;
import zs.xmx.net.callback.ISuccess;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/6/10 23:02
 * @本类描述	  真实的数据操作类
 * @内容说明
 *
 */
public class IRegisterModelImpl implements IRegisterModel {
    @Override
    public void loadRegisterData() {
        RxBus.getInstance().chainProcess(new Function() {
            String responseData;

            @Override
            public Object apply(Object o) throws Exception {
                //在这里面进行网络操作
                HashMap params = new HashMap();
                params.put("category", "android");
                RestClient.create()
                        .lastUrl("/xiandu")
                        .params(params)
                        .success(new ISuccess() {
                            @Override
                            public void onSuccess(String response) {
                                responseData = response;
                            }
                        })
                        .build()
                        .get();

                return "djaskjdkas";
            }
        });
    }

    //用了RxBus就不需要接口回调的方式来传递数据
    //    @Override
    //    public void loadRegisterData(final OnRegisterLoadListener onRegisterLoadListener) {
    //        //        HashMap params = new HashMap();
    //        //        params.put("category", "android");
    //        //        RestClient.create()
    //        //                .lastUrl("/xiandu")
    //        //                .params(params)
    //        //                .success(new ISuccess() {
    //        //                    @Override
    //        //                    public void onSuccess(String response) {
    //        //                        onRegisterLoadListener.onComplete(response);
    //        //                    }
    //        //                })
    //        //                .build()
    //        //                .get();

    //下面是RxJava实现的retrofit框架
    //        HashMap params = new HashMap();
    //        params.put("category", "android");
    //        RxRestClient.create()
    //                .lastUrl("/xiandu")
    //                .params(params)
    //                .build()
    //                .get()
    //                .subscribeOn(Schedulers.io())
    //                .observeOn(AndroidSchedulers.mainThread())
    //                .subscribe(new Observer<String>() {
    //                    @Override
    //                    public void onSubscribe(Disposable d) {
    //
    //                    }
    //
    //                    @Override
    //                    public void onNext(String s) {
    //                        onRegisterLoadListener.onComplete(s);
    //                    }
    //
    //                    @Override
    //                    public void onError(Throwable e) {
    //
    //                    }
    //
    //                    @Override
    //                    public void onComplete() {
    //
    //                    }
    //                });
    //
    //
    //    }
}
