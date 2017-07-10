package zs.xmx.netframe;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.downlaod.DownInfo;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.downlaod.DownState;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.downlaod.HttpDownManager;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.utils.DbDwonUtil;

import java.io.File;
import java.util.List;

import zs.xmx.netframe.adapter.DownAdapter;
import zs.xmx.netframe.base.BaseActivity;
import zs.xmx.netframe.utils.PermissionsUtils;

public class DownLoadActivity extends BaseActivity {
    List<DownInfo> listData;
    DbDwonUtil     dbUtil;
    private TextView        mTv_netStata;
    private HttpDownManager mHttpDownManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load);
        initView();
        initResource();
        initWidget();
    }

    private void initView() {
        mTv_netStata = (TextView) findViewById(R.id.tv_netStata);
        PermissionsUtils.requestPermission(this, PermissionsUtils.CODE_WRITE_EXTERNAL_STORAGE, mPermissionGrant);
        PermissionsUtils.requestPermission(this, PermissionsUtils.CODE_READ_EXTERNAL_STORAGE, mPermissionGrant);
    }

    /*数据*/
    private void initResource() {
        mHttpDownManager = HttpDownManager.getInstance();
        dbUtil = DbDwonUtil.getInstance();
        listData = dbUtil.queryDownAll();
        /*第一次模拟服务器返回数据掺入到数据库中*/
        if (listData.isEmpty()) {
            String[] downUrl = new String[]{"http://www.izaodao.com/app/izaodao_app.apk",
                    "http://www.deng.com/app/Manufactor/Deng_Manufactor_Android.apk"};
            for (int i = 0; i < downUrl.length; i++) {
                File outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                        "test" + i + ".apk");
                DownInfo apkApi = new DownInfo(downUrl[i]);
                apkApi.setId(i);
                apkApi.setState(DownState.START);
                apkApi.setSavePath(outputFile.getAbsolutePath());
                dbUtil.save(apkApi);
            }
            listData = dbUtil.queryDownAll();
        }
    }

    /*加载控件*/
    private void initWidget() {
        EasyRecyclerView recyclerView = (EasyRecyclerView) findViewById(R.id.rv);
        DownAdapter adapter = new DownAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.addAll(listData);
    }

    /**
     * 动态申请权限后结果处理
     */
    private PermissionsUtils.PermissionGrant mPermissionGrant = new PermissionsUtils.PermissionGrant() {
        @Override
        public void onPermissionGranted(int requestCode) {
            switch (requestCode) {
                case PermissionsUtils.CODE_READ_EXTERNAL_STORAGE:
                   // showToast("Result Permission Grant CODE_READ_EXTERNAL_STORAGE");
                    break;
                case PermissionsUtils.CODE_WRITE_EXTERNAL_STORAGE:
                    //showToast("Result Permission Grant CODE_WRITE_EXTERNAL_STORAGE");
                    break;
            }
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsUtils.requestPermissionsResult(this, requestCode, permissions, grantResults, mPermissionGrant);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*记录退出时下载任务的状态-复原用*/
        for (DownInfo downInfo : listData) {
            dbUtil.update(downInfo);
        }
    }

    @Override
    public void onNetWorkStataChanged(String mNetWorkTypeName) {
        super.onNetWorkStataChanged(mNetWorkTypeName);
        if (mNetWorkTypeName.equals("NETWORK_UNKNOWN") || mNetWorkTypeName.equals("NETWORK_NO")) { //没网
            //显示TextView
            mTv_netStata.setVisibility(View.VISIBLE);
            mTv_netStata.setText("当前网络不可用");
            //暂停全部下载
            mHttpDownManager.pauseAll();
        } else {
            mTv_netStata.setVisibility(View.GONE);
        }

    }

}
