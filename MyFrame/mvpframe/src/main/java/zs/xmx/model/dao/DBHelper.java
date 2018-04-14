package zs.xmx.model.dao;
/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2017/8/9
 * @本类描述	  ${TODO}
 * @内容说明   ${TODO}
 * @补充内容   我们可以在自带的单例测试:
 *
 *             public void testCreateDB(){
 *             DBHelper dbHelper=new DBHelper(getContext());
 *             dbHelper.getWritableDatabase();
 *             }
 *
 * ---------------------------------     
 * @更新时间   
 * @新增内容   
 *
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import zs.xmx.MyApplication;
import zs.xmx.model.domian.bean.AddressBean;
import zs.xmx.model.domian.bean.UserBean;

public class DBHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASENAME    = "xmx.db";
    private static final int    DATABASEVERSION = 1;

    public DBHelper(Context context) {

        super(context, DATABASENAME, null, DATABASEVERSION);
    }

    /**
     * 数据库
     * <p>
     * 使用双重校验的单例模式实现(效率高)
     * <p>
     * 如果在方法上加锁,那么每次调用都需要排队
     **/
    private static DBHelper instant;

    public static DBHelper getInstant() {
        if (instant == null) { //第一次校验:提高效率
            //考虑加锁
            synchronized (DBHelper.class) {
                if (instant == null) { //第二次校验:防止对象的多次创建
                    instant = new DBHelper(MyApplication.getAPPContext());
                }
            }
        }
        return instant;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, UserBean.class);
            TableUtils.createTable(connectionSource, AddressBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }


}
