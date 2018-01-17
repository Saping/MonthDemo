package com.example.administrator.a1511amonthdemo01.view.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.a1511amonthdemo01.R;
import com.example.administrator.a1511amonthdemo01.model.bean.Detail_bean;
import com.example.administrator.a1511amonthdemo01.presenter.AddShoppingCart_P;
import com.example.administrator.a1511amonthdemo01.presenter.Detail_P;
import com.example.administrator.a1511amonthdemo01.util.My_api;
import com.example.administrator.a1511amonthdemo01.view.inter_F.AddShoppingCartV_I;
import com.example.administrator.a1511amonthdemo01.view.inter_F.DetailV_I;
import com.google.gson.Gson;

public class SecondActivity extends AppCompatActivity implements DetailV_I , AddShoppingCartV_I {

    private ImageView image;
    private TextView content;
    private TextView price;
    private TextView beginPrice;
    private AddShoppingCart_P addShoppingCart_p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        image = findViewById(R.id.image);
        content = findViewById(R.id.content);
        price = findViewById(R.id.price);
        beginPrice = findViewById(R.id.beginPrice);
        //文字中间加横线
        price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );

        //获取数据展示
        Detail_P detail_p = new Detail_P(this);
        detail_p.getdata(My_api.Detail);
    }

    @Override
    public void success(final String s) {
        if(s!=null){
            //获取到数据进行解析展示
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Gson gson = new Gson();
                    Detail_bean detail_bean = gson.fromJson(s, Detail_bean.class);

                    Detail_bean.DataBean data = detail_bean.getData();

                    //设置图片
                    if(data!=null){
                        String[] split = data.getImages().split("\\|");
                        Glide.with(SecondActivity.this).load(split[0]).into(image);
                        content.setText(data.getTitle());
                        price.setText("原价 : "+data.getPrice());
                        beginPrice.setText("优惠价 : "+data.getBargainPrice()+"");
                    }

                }
            });
        }
        }


    //跳转到购物车
    public void goShoppingCart(View view) {
        Intent intent = new Intent(SecondActivity.this,MyShoppingCart.class);
        startActivity(intent);
    }

    //添加到购物车
    public void addShoppingCart(View view) {
         //访问接口添加
        addShoppingCart_p = new AddShoppingCart_P(this);
        addShoppingCart_p.getdata(My_api.AddShoppingCart);
    }

    @Override
    public void successAdd(final String s) {
        if(s!=null){
            //添加购物车返回的数据
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(SecondActivity.this,s,Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
