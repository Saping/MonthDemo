package com.example.administrator.a1511amonthdemo01.presenter;

import com.example.administrator.a1511amonthdemo01.model.Detail_M;
import com.example.administrator.a1511amonthdemo01.presenter.inter_F.DetailP_I;
import com.example.administrator.a1511amonthdemo01.view.inter_F.DetailV_I;

/**
 * Created by Administrator on 2018/1/16.
 */

public class Detail_P implements DetailP_I {

    private DetailV_I detailV_i;
    private Detail_M detail_m;

    public Detail_P(DetailV_I detailV_i) {
        detail_m = new Detail_M(this);
        this.detailV_i = detailV_i;
    }

    public void getdata(String detail) {
        detail_m.getdata(detail);

    }

    @Override
    public void success(String s) {
        detailV_i.success(s);
    }
}
