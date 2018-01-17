package com.example.administrator.a1511amonthdemo01.presenter;

import com.example.administrator.a1511amonthdemo01.model.AddShoppingCart_M;
import com.example.administrator.a1511amonthdemo01.presenter.inter_F.AddShoppingCartP_I;
import com.example.administrator.a1511amonthdemo01.view.inter_F.AddShoppingCartV_I;

/**
 * Created by Administrator on 2018/1/16.
 */

public class AddShoppingCart_P implements AddShoppingCartP_I{

    private AddShoppingCartV_I addShoppingCartV_i;
    private AddShoppingCart_M addShoppingCart_m;

    public AddShoppingCart_P(AddShoppingCartV_I addShoppingCartV_i) {
        addShoppingCart_m = new AddShoppingCart_M(this);
        this.addShoppingCartV_i = addShoppingCartV_i;
    }

    public void getdata(String addShoppingCart) {
        addShoppingCart_m.getdata(addShoppingCart);
    }

    @Override
    public void success(String s) {
        addShoppingCartV_i.successAdd(s);
    }
}
