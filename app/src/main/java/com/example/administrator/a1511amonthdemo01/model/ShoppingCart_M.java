package com.example.administrator.a1511amonthdemo01.model;

import com.example.administrator.a1511amonthdemo01.presenter.inter_F.ShoppingCartP_I;
import com.example.administrator.a1511amonthdemo01.util.OkHttp3Util;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/1/16.
 */

public class ShoppingCart_M {

    private ShoppingCartP_I shoppingCartP_i;

    public ShoppingCart_M(ShoppingCartP_I shoppingCartP_i) {
        this.shoppingCartP_i = shoppingCartP_i;
    }

    public void getdata(String shoppingCart) {
        HashMap<String, String> map = new HashMap<>();
        map.put("uid","4123");
        OkHttp3Util.doPost(shoppingCart, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
               if(response.isSuccessful()){
                   String string = response.body().string();
                   shoppingCartP_i.success(string);
               }
            }
        });
    }
}
