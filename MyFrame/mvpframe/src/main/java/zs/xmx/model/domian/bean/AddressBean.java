package zs.xmx.model.domian.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2017/8/9
 * @本类描述	  ${TODO}
 * @内容说明   数据库实体类
 * @补充内容
 *
 * ---------------------------------     
 * @更新时间   
 * @新增内容   
 *
 */
@DatabaseTable(tableName = "t_address")
public class AddressBean {
    @DatabaseField(id = true)
    private int _id;

    @DatabaseField(canBeNull = false)
    private String goodsAddress;

    @DatabaseField(canBeNull = false)
    private String village;

    //将User表的_id作为外键,让User表和Address表建立联系
    @DatabaseField(canBeNull = false, foreign = true, foreignColumnName = "_id", columnName = "user_id")
    private UserBean user;

    public AddressBean() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getGoodsAddress() {
        return goodsAddress;
    }

    public void setGoodsAddress(String goodsAddress) {
        this.goodsAddress = goodsAddress;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }
}
