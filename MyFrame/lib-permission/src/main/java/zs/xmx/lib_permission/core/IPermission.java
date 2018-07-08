package zs.xmx.lib_permission.core;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/6/24 23:47
 * @本类描述	  授权状态接口
 * @内容说明
 *
 */
public interface IPermission {

    /**
     * 已经授权
     */
    void ganted();

    /**
     * 取消授权
     */
    void cancled();

    /**
     * 被拒绝 点击了不再提示
     */
    void denied();

}