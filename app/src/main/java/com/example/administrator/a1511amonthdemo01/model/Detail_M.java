package com.example.administrator.a1511amonthdemo01.model;

import com.example.administrator.a1511amonthdemo01.presenter.inter_F.DetailP_I;
import com.example.administrator.a1511amonthdemo01.util.OkHttp3Util;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/1/16.
 */

public class Detail_M {

    private DetailP_I detailP_i;

    public Detail_M(DetailP_I detailP_i) {
     this.detailP_i = detailP_i;
    }

    public void getdata(String detail) {
        HashMap<String, String> map = new HashMap<>();
        map.put("pid","1");
        OkHttp3Util.doPost(detail, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
               if(response.isSuccessful()){
                   String string = response.body().string();
                   detailP_i.success(string);
               }
            }
        });
    }
}
