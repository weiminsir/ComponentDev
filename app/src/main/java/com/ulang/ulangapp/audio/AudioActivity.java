package com.ulang.ulangapp.audio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ulang.ulangapp.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class AudioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        findViewById(R.id.btn_audio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SocketThread().start();
            }
        });

    }

    class SocketThread extends Thread {
        @Override
        public void run() {
            try {
                //1.创建监听指定服务器地址以及指定服务器监听的端口号
                Socket socket = new Socket("192.168.1.21", 9000);//111.111.11.11为我这个本机的IP地址，端口号为12306.
                //2.拿到客户端的socket对象的输出流发送给服务器数据
                OutputStream os = socket.getOutputStream();
                //写入要发送给服务器的数据
                os.write(new String("weiminsir 加油").getBytes());
                os.flush();
                socket.shutdownOutput();
                //拿到socket的输入流，这里存储的是服务器返回的数据
                InputStream is = socket.getInputStream();
                //解析服务器返回的数据
                InputStreamReader reader = new InputStreamReader(is);
                BufferedReader bufReader = new BufferedReader(reader);
                String s = null;
                final StringBuffer sb = new StringBuffer();
                while ((s = bufReader.readLine()) != null) {
                    sb.append(s);
                }

                Log.d("weiminsir", "result=" + sb.toString());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                                tv.setText(sb.toString());
                        Toast.makeText(AudioActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                //3、关闭IO资源（注：实际开发中需要放到finally中）
                bufReader.close();
                reader.close();
                is.close();
                os.close();
                socket.close();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}
