package zs.xmx.test;
/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2017/8/2
 * @本类描述	  ${TODO}
 * @内容说明   ${TODO}
 * @补充内容
 *
 * ---------------------------------     
 * @更新时间   
 * @新增内容   
 *
 */

import android.os.SystemClock;

/**
 * 发送用户输入数据
 */
public class UserLoginNet {
    public boolean sendUserLoginInfo(User user) {
        SystemClock.sleep(2000);//模拟登陆韩式操作
        if ("abc".equals(user.username) && "123".equals(user.password)) {
            return true;
        } else {
            return false;
        }
    }
}
