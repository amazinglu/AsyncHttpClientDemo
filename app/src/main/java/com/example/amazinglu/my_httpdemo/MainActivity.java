package com.example.amazinglu.my_httpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Request request = new Request.Builder()
//                .url("http://www.google.com")
//                .build();
//
//        OkHttpClient client = new OkHttpClient();
//        try {
//            Response response = client.newCall(request).execute();
//            Log.i("guojing_demo", response.body().string());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        /**
         * use OkHttp to make synchronous HTTP request
         * the request cannot run in mian thread
         * */
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                .url("http://www.google.com")
                .build();

                OkHttpClient client = new OkHttpClient();
                try {
                    Response response = client.newCall(request).execute();
                    final String reponseString = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView textView = (TextView) findViewById(R.id.text_view);
                            textView.setText(reponseString);
                        }
                    });
//                    TextView textView = (TextView) findViewById(R.id.text_view);
//                    textView.setText(response.body().string());
//                    Log.i("guojing_demo", response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
