package com.example.administrator.a1511amonthdemo01.presenter;

import com.example.administrator.a1511amonthdemo01.model.Daizhifu_M;
import com.example.administrator.a1511amonthdemo01.presenter.inter_F.DaizhifuP_I;
import com.example.administrator.a1511amonthdemo01.view.inter_F.DaizhifuV_I;

/**
 * Created by Administrator on 2018/1/17.
 */

public class Daizhifu_P implements DaizhifuP_I {

    private Daizhifu_M daizhifu_m;
    private DaizhifuV_I daizhifuV_i;

    public Daizhifu_P(DaizhifuV_I daizhifuV_i) {
        daizhifu_m = new Daizhifu_M(this);
        this.daizhifuV_i = daizhifuV_i;
    }

    public void getdata(String showOrder, int page) {
        daizhifu_m.getdata(showOrder,page);
    }

    @Override
    public void success(String s) {
        daizhifuV_i.success(s);
    }
}
