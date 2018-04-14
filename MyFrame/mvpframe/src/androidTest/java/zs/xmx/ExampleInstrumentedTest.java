package zs.xmx;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.j256.ormlite.dao.Dao;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.SQLException;

import zs.xmx.model.dao.DBHelper;
import zs.xmx.model.domian.bean.AddressBean;
import zs.xmx.model.domian.bean.UserBean;

import static android.support.test.InstrumentationRegistry.getContext;
import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("zs.xmx", appContext.getPackageName());
    }

    @Test
    public void testCreateDB() {
        DBHelper dbHelper = new DBHelper(getContext());
        dbHelper.getWritableDatabase();
    }

    @Test
    public void testUser() throws SQLException {
        DBHelper dbHelper = new DBHelper(getContext());

        Dao<UserBean, Integer> dao = dbHelper.getDao(UserBean.class);

        UserBean userBean1 = new UserBean();
        userBean1.set_id(1);
        dao.create(userBean1);

        UserBean userBean2 = new UserBean();
        userBean2.set_id(2);
        dao.create(userBean2);


    }

    public void testAddress() throws SQLException {
        DBHelper dbHelper = new DBHelper(getContext());
        Dao<AddressBean, Integer> dao = dbHelper.getDao(AddressBean.class);

        UserBean userBean = new UserBean();
        userBean.set_id(2);

        for (int i = 0; i < 10; i++) {
            AddressBean addressBean = new AddressBean();
            addressBean.set_id(i);
            addressBean.setGoodsAddress("送货地址" + i);
            addressBean.setVillage("小区" + i);
            dao.create(addressBean);
        }


    }

    public void testFindById() throws SQLException {
        DBHelper dbHelper = new DBHelper(getContext());

        Dao<UserBean, Integer> dao = dbHelper.getDao(UserBean.class);

        UserBean userBean=dao.queryForId(2);
        System.out.println(userBean.toString());

    }
}
