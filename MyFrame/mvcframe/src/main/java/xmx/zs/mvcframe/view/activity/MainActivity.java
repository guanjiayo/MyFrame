package xmx.zs.mvcframe.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import xmx.zs.mvcframe.R;
import xmx.zs.mvcframe.isolation.net.HttpRequestPresenter;
import xmx.zs.mvcframe.isolation.net.async.AsyncHttpRequest;
import xmx.zs.mvcframe.model.domain.WeatherCallback;
import xmx.zs.mvcframe.model.domain.WeatherInfo;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HttpRequestPresenter.init(new AsyncHttpRequest());

        //json
        Map<String, String> params = new HashMap<>();
        params.put("city", "长沙");
        params.put("key", "13cb58f5884f9749287abbead9c658f2");
        HttpRequestPresenter.getInstance().get("http://restapi.amap.com/v3/weather/weatherInfo"
                , params, new WeatherCallback<WeatherInfo>() {

                    @Override
                    public void onSuccess(WeatherInfo weatherInfo) {
                        Log.e(TAG, weatherInfo.toString());
                    }

                    @Override
                    public void onFailure(int code, Throwable t) {
                        t.printStackTrace();
                    }
                });


    }


}
