package com.ulang.router.db;

import android.test.AndroidTestCase;

import com.ulang.router.MyApp;

import org.junit.Test;

import java.sql.SQLException;

public class OrmSqlHelperTest  extends AndroidTestCase{

    @Test
    public void ormInsertData() {


        try {
            User user = new User("weimin", 26);
            OrmSqlHelper helper = new OrmSqlHelper(getContext());
            helper.getUserDao().create(user);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}
