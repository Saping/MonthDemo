package com.example.administrator.a1511amonthdemo01.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.a1511amonthdemo01.R;
import com.example.administrator.a1511amonthdemo01.util.My_api;
import com.example.administrator.a1511amonthdemo01.util.OkHttp3Util;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Generating_Order extends AppCompatActivity {

    private String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generating__order);

        TextView My_price = findViewById(R.id.price);

        //获取到接收到的数据

        Intent intent = getIntent();
        //得到价钱
        price = intent.getStringExtra("price");
        My_price.setText(price);
        //得到数目
        String count = intent.getStringExtra("count");


    }

    //点击创建订单
    public void createOrder(View view) {
        //如果创建成功就跳转到展示页面
        HashMap<String, String> map = new HashMap<>();

        map.put("uid", "4123");
        map.put("price", price);

        OkHttp3Util.doPost(My_api.CreateOrder, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //
                if (response.isSuccessful()) {
                    final String string = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Generating_Order.this, string, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Generating_Order.this, MyOrder.class);
                            startActivity(intent);

                        }
                    });
                }
            }
        });
    }
}
