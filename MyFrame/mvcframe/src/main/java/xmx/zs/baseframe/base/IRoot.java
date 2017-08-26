package xmx.zs.baseframe.base;
/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2017/8/26
 * @本类描述	  ${TODO}
 * @内容说明   ${TODO}
 * @补充内容
 *
 * ---------------------------------     
 * @更新时间   
 * @新增内容   
 *
 */

import java.util.List;
import java.util.Map;

public interface IRoot {
    public abstract void initData();

    public abstract void doRequest(String action, ReParam params, final int type,
                                   String title, final boolean dialog);

    public abstract void onComplete(String result, int type);

    public abstract void onFailure(String msg);

    public abstract Map getContent(String result);

    public abstract List<Map> getLists(String result);

    public abstract boolean isSuccess(String result);
}
