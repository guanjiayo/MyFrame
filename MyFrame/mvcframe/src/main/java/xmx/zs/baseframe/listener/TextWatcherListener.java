package xmx.zs.baseframe.listener;

import android.text.Editable;
import android.text.TextWatcher;

/*
 * @创建者     mqm
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2017/6/16
 * @本类描述	   接口的适配器模式简化TextWatcher
 * @内容说明
 * @补充内容
 *
 * ---------------------------------     
 * @更新时间   
 * @新增内容   
 *
 */

public abstract class TextWatcherListener implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }


    public abstract void afterTextChanged(Editable s);


}
