package com.example.administrator.a1511amonthdemo01.model;

import com.example.administrator.a1511amonthdemo01.presenter.inter_F.DaizhifuP_I;
import com.example.administrator.a1511amonthdemo01.util.OkHttp3Util;
import com.example.administrator.a1511amonthdemo01.view.fragment.Daizhifu_fragment;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/1/17.
 */

public class Daizhifu_M {

    private DaizhifuP_I daizhifuP_i;

    public Daizhifu_M(DaizhifuP_I daizhifuP_i) {

        this.daizhifuP_i = daizhifuP_i;
    }

    public void getdata(String showOrder, int page) {
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", "4123");
        map.put("page", page + "");
        map.put("status", "0");

        OkHttp3Util.doPost(showOrder, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String string = response.body().string();
                    //回传
                    daizhifuP_i.success(string);
                }

            }
        });
    }
}
