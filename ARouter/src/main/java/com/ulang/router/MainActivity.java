package com.ulang.router;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ulang.router.db.OrmSqlHelper;
import com.ulang.router.db.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView userText = findViewById(R.id.user);
        try {
            User user = new User("weimin", 26);
            OrmSqlHelper helper = new OrmSqlHelper(this);
            helper.getUserDao().create(user);
            user = new User("weimin2", 27);
            helper.getUserDao().create(user);

            List<User> users = helper.getUserDao().queryForAll();
            userText.setText(users.toString());

            for (User user1 : users) {
                Log.d("weiminsir",user1.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }




    }
    /**
     * 网络连接的操作
     */
    private void postNetwork() {
        try {
            URL url = new URL("http://www.wooyun.org");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String lines = null;
            StringBuffer sb = new StringBuffer();
            while ((lines = reader.readLine()) != null) {
                sb.append(lines);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}