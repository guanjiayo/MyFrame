package zs.xmx.constant;
/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2017/8/5
 * @本类描述	  URL常量类
 * @内容说明   ${TODO}
 * @补充内容
 *
 * ---------------------------------     
 * @更新时间   
 * @新增内容   
 *
 */

public interface UrlConstant {
    //分析URL,将?前的字段全部拆分出来
    //http://localhost:8080/TakeoutService/login? username="itheima"&password="bj"
    String BASEURL = "http://localhost:8080/";
    String LOGIN   = "TakeoutService/login";
}
