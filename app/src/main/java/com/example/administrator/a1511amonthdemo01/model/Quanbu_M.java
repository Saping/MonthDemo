package com.example.administrator.a1511amonthdemo01.model;

import com.example.administrator.a1511amonthdemo01.presenter.Quanbu_P;
import com.example.administrator.a1511amonthdemo01.presenter.inter_F.QuanbuP_I;
import com.example.administrator.a1511amonthdemo01.util.OkHttp3Util;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/1/17.
 */

public class Quanbu_M {

    private QuanbuP_I quanbuP_i;

    public Quanbu_M(QuanbuP_I quanbuP_i) {
        this.quanbuP_i = quanbuP_i;
    }

    public void getdata(String showOrder, int page) {

        HashMap<String, String> map = new HashMap<>();
        map.put("uid", "4123");
        map.put("page", page + "");

        OkHttp3Util.doPost(showOrder, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String string = response.body().string();

                    quanbuP_i.success(string);
                }
            }
        });
    }
}
