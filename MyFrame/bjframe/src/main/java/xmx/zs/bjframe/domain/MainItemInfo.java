package xmx.zs.bjframe.domain;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2016/10/26 21:56
 * @本类描述	  Item bean类
 * @内容说明
 * ---------------------------------------------     
 * @更新时间   2016/10/26 
 * @更新说明
 */
public class MainItemInfo {
    private String title;
    private String desc;
    private String data;

    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }


    /**
     * 防止实例化
     */
    public MainItemInfo() {

    }

    public MainItemInfo(String title, String desc, String data) {
        this.title = title;
        this.desc = desc;
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "MainItemInfo{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
