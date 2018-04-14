package zs.xmx.model.domian.bean;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
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
@DatabaseTable(tableName = "t_user")
public class UserBean {
    //如果列名和设置的变量名一致,可以不设置columnName
    @DatabaseField(columnName = "_id", id = true)
    private int _id;

    //需要有一个集合用来存放当前用户的所有地址信息(一个用户可能有多个地址)
    @ForeignCollectionField(eager = true)
    private ForeignCollection<AddressBean> addressList;

    public UserBean() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public ForeignCollection<AddressBean> getAddressList() {
        return addressList;
    }

    public void setAddressList(ForeignCollection<AddressBean> addressList) {
        this.addressList = addressList;
    }
}
