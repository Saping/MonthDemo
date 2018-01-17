package com.example.administrator.a1511amonthdemo01.model;

import com.example.administrator.a1511amonthdemo01.presenter.inter_F.AddShoppingCartP_I;
import com.example.administrator.a1511amonthdemo01.util.OkHttp3Util;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/1/16.
 */

public class AddShoppingCart_M {

    private AddShoppingCartP_I addShoppingCartP_i;

    public AddShoppingCart_M(AddShoppingCartP_I addShoppingCartP_i) {
         this.addShoppingCartP_i = addShoppingCartP_i;
    }

    public void getdata(String addShoppingCart) {
        HashMap<String, String> map = new HashMap<>();
        map.put("uid","4123");
        map.put("pid","1");

        OkHttp3Util.doPost(addShoppingCart, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
              if(response.isSuccessful()){
                  String string = response.body().string();
                  addShoppingCartP_i.success(string);
              }
            }
        });
    }
}
