package zs.xmx.lib_permission.menu.base;

import android.content.Context;
import android.content.Intent;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/6/25 1:21
 * @本类描述	  权限被永久禁止,然后让用户手动去设置权限回调接口
 * @内容说明
 *
 */

public interface IMenu {
    Intent getMenuIntent(Context context);
}