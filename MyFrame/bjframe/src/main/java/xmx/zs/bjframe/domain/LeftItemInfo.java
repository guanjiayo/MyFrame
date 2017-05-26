package xmx.zs.bjframe.domain;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2016/11/6 21:56
 * @本类描述	  Item bean类
 * @内容说明
 * ---------------------------------------------     
 * @更新时间   2016/10/26 
 * @更新说明
 */
public class LeftItemInfo {
    private String item;

    /**
     * 构造函数
     *
     * @param item
     */
    public LeftItemInfo(String item) {

        this.item = item;
    }

    /**
     * 防止实例化
     */
    public LeftItemInfo() {

        this.item = item;
    }

    public String getItem() {
        return item;
    }

    public LeftItemInfo setItem(String item) {
        this.item = item;
        return this;
    }

    @Override
    public String toString() {
        return "LeftItemInfo{" +
                "item='" + item + '\'' +
                '}';
    }
}
