package com.example.administrator.a1511amonthdemo01.presenter;

import com.example.administrator.a1511amonthdemo01.model.Quanbu_M;
import com.example.administrator.a1511amonthdemo01.presenter.inter_F.QuanbuP_I;
import com.example.administrator.a1511amonthdemo01.view.fragment.Quanbu_fragment;
import com.example.administrator.a1511amonthdemo01.view.inter_F.QuanbuV_I;

/**
 * Created by Administrator on 2018/1/17.
 */

public class Quanbu_P implements QuanbuP_I {

    private QuanbuV_I quanbuV_i;
    private Quanbu_M quanbu_m;

    public Quanbu_P(QuanbuV_I quanbuV_i) {
        quanbu_m = new Quanbu_M(this);
        this.quanbuV_i = quanbuV_i;
    }

    public void getdata(String showOrder, int page) {
        quanbu_m.getdata(showOrder, page);
    }

    @Override
    public void success(String s) {
        quanbuV_i.success(s);
    }

    //销毁  解决MVP内存泄漏问题
    public void destory() {
        if (quanbuV_i != null) {
            quanbuV_i = null;
        }
    }
}
