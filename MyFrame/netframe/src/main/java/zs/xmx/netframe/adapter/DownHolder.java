package zs.xmx.netframe.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.downlaod.DownInfo;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.downlaod.HttpDownManager;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.HttpDownOnNextListener;

import java.util.Iterator;
import java.util.Set;

import zs.xmx.netframe.R;
import zs.xmx.netframe.utils.Logger;


public class DownHolder extends BaseViewHolder<DownInfo> implements View.OnClickListener {
    private static final String TAG = DownHolder.class.getSimpleName();
    private TextView          tvMsg;
    private NumberProgressBar progressBar;
    private DownInfo          apkApi;
    private HttpDownManager   manager;

    public DownHolder(ViewGroup parent) {
        super(parent, R.layout.view_item_holder);
        manager = HttpDownManager.getInstance();
        $(R.id.btn_rx_down).setOnClickListener(this);
        $(R.id.btn_rx_pause).setOnClickListener(this);
        $(R.id.btn_rx_stop).setOnClickListener(this);
        progressBar = $(R.id.number_progress_bar);
        tvMsg = $(R.id.tv_msg);
    }

    @Override
    public void setData(DownInfo data) {
        super.setData(data);
        data.setListener(httpProgressOnNextListener);
        apkApi = data;
        progressBar.setMax((int) apkApi.getCountLength());
        progressBar.setProgress((int) apkApi.getReadLength());
        /*第一次恢复 */
        switch (apkApi.getState()) {
            case START:
                /*起始状态*/
                break;
            case PAUSE:
                tvMsg.setText("暂停中");
                break;
            case DOWN:
                manager.startDown(apkApi);
                break;
            case STOP:
                tvMsg.setText("下载停止");
                break;
            case ERROR:
                tvMsg.setText("下载错误");
                break;
            case FINISH:
                tvMsg.setText("下载完成");
                break;
            case RESTART:
                tvMsg.setText("重新下载");
                break;
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_rx_down:
                manager.startDown(apkApi);
                break;
            case R.id.btn_rx_pause:
                manager.pause(apkApi);
                break;
            case R.id.btn_rx_stop:
                manager.restarDown(apkApi);
                break;
        }
    }

    /*下载回调*/
    HttpDownOnNextListener<DownInfo> httpProgressOnNextListener = new HttpDownOnNextListener<DownInfo>() {
        @Override
        public void onNext(DownInfo baseDownEntity) {
            tvMsg.setText("提示：下载完成");
            Toast.makeText(getContext(), baseDownEntity.getSavePath(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStart() {
            tvMsg.setText("提示:开始下载");
        }

        @Override
        public void onComplete() {
            tvMsg.setText("提示：下载结束");
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            tvMsg.setText("失败:" + e.toString());
            Toast.makeText(getContext(), "下载失败,连接超时", Toast.LENGTH_SHORT).show();
        }


        @Override
        public void onPuase() {
            super.onPuase();
            tvMsg.setText("提示:暂停");
        }

        @Override
        public void onStop() {
            super.onStop();
            tvMsg.setText("提示：停止下载");

        }

        @Override
        public void onRestart() {
            super.onRestart();
            tvMsg.setText("提示：重新下载");
            Set<DownInfo> downInfos = manager.getDownInfos();
            Iterator<DownInfo> it = downInfos.iterator();
            while (it.hasNext()) {
                DownInfo str = it.next();
                Logger.i(TAG, "保存路径== " + str.getSavePath() + "\n"
                        + "已下载== " + str.getReadLength() + "\n"
                        + "下载地址== " + str.getUrl() + "\n"
                        + "文件总大小== " + str.getCountLength()
                );
            }


        }

        @Override
        public void updateProgress(long readLength, long countLength) {
            tvMsg.setText("提示:下载中");
            progressBar.setMax((int) countLength);
            progressBar.setProgress((int) readLength);
        }
    };


}