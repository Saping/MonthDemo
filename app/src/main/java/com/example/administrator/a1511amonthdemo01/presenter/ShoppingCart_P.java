package com.example.administrator.a1511amonthdemo01.presenter;

import com.example.administrator.a1511amonthdemo01.model.ShoppingCart_M;
import com.example.administrator.a1511amonthdemo01.presenter.inter_F.ShoppingCartP_I;
import com.example.administrator.a1511amonthdemo01.view.activity.MyShoppingCart;
import com.example.administrator.a1511amonthdemo01.view.inter_F.ShoppingCartV_I;

/**
 * Created by Administrator on 2018/1/16.
 */

public class ShoppingCart_P implements ShoppingCartP_I{

    private ShoppingCartV_I shoppingCartV_i;
    private ShoppingCart_M shoppingCart_m;

    public ShoppingCart_P(ShoppingCartV_I shoppingCartV_i) {
        this.shoppingCartV_i = shoppingCartV_i;
        shoppingCart_m = new ShoppingCart_M(this);
    }

    public void getdata(String shoppingCart) {
        shoppingCart_m.getdata(shoppingCart);
    }

    @Override
    public void success(String s) {
        shoppingCartV_i.success(s);
    }
}
