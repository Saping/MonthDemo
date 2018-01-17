package com.example.administrator.a1511amonthdemo01.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.administrator.a1511amonthdemo01.R;
import com.example.administrator.a1511amonthdemo01.model.bean.CountPriceBean;
import com.example.administrator.a1511amonthdemo01.model.bean.ShoppingCart_bean;
import com.example.administrator.a1511amonthdemo01.presenter.ShoppingCart_P;
import com.example.administrator.a1511amonthdemo01.util.My_api;
import com.example.administrator.a1511amonthdemo01.view.adapter.Myadapter;
import com.example.administrator.a1511amonthdemo01.view.inter_F.ShoppingCartV_I;
import com.google.gson.Gson;

import java.util.List;

public class MyShoppingCart extends AppCompatActivity implements ShoppingCartV_I {

    private ExpandableListView my_expandableListview;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);


            if (msg.what == 0) {
                CountPriceBean countPriceBean = (CountPriceBean) msg.obj;
                priceString = countPriceBean.getPriceString();
                count = countPriceBean.getCount();

                tol.setText("合计:¥" + priceString);
                gobuy.setText("去结算(" + count + ")");
            }
        }
    };
    private ShoppingCart_P shoppingCart_p;
    private CheckBox allcheck;
    private Myadapter myadapter;
    private TextView tol;
    private TextView gobuy;
    private String priceString;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shopping_cart);

        //找到控件
        my_expandableListview = findViewById(R.id.My_expandableListview);
        allcheck = findViewById(R.id.allcheck);
        //合计
        tol = findViewById(R.id.tol);
        //去结算
        gobuy = findViewById(R.id.gobuy);


        //首先通过MVP获取网络上的数据
        shoppingCart_p = new ShoppingCart_P(MyShoppingCart.this);
        shoppingCart_p.getdata(My_api.ShoppingCart);

    }

    @Override
    public void success(final String s) {

        if (s != null) {
            //获取数据解析展示
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Gson gson = new Gson();
                    ShoppingCart_bean shoppingCart_bean = gson.fromJson(s, ShoppingCart_bean.class);
                    List<ShoppingCart_bean.DataBean> data = shoppingCart_bean.getData();
                    if (data != null) {
                        //设置二级列表的适配器
                        myadapter = new Myadapter(MyShoppingCart.this, data, handler, shoppingCart_p);
                        my_expandableListview.setAdapter(myadapter);
                        //去掉小箭头
                        my_expandableListview.setGroupIndicator(null);
                        //展开全部
                        for (int i = 0; i < data.size(); i++) {
                            my_expandableListview.expandGroup(i);
                        }
                        //l.根据某一个组中的二级所有的子条目是否选中,确定当前一级列表是否选中
                        for (int i = 0; i < data.size(); i++) {
                            //如果在当前一级列表中
                            if (isChildInGroupChecked(i, data)) {
                                //选中一级列表
                                data.get(i).setGroup_check(true);
                            }
                        }
                        //2.设置是否全选选中...根据所有的一级列表是否选中,确定全选是否选中
                        allcheck.setChecked(isAllGroupChecked(data));

                    }
                    //点击全选
                    allcheck.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //判断适配器是否为空
                            if (myadapter != null) {
                                myadapter.setAllChildChecked(allcheck.isChecked());
                            }
                        }
                    });
                    //3.计算  价格  和数目
                    myadapter.sendPriceAndCount();
                    //点击结算
                    gobuy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(MyShoppingCart.this, Generating_Order.class);
                            intent.putExtra("price", priceString);
                            intent.putExtra("count", count);
                            startActivity(intent);
                        }
                    });

                }
            });
        }


    }

    //判断所有的一级列表是否选中
    private boolean isAllGroupChecked(List<ShoppingCart_bean.DataBean> data) {
        for (int i = 0; i < data.size(); i++) {
            //如果有没选中的
            if (!data.get(i).isGroup_check()) {
                return false;
            }
        }
        return true;
    }

    //如果当前的以及列表中有一个二级列表没有选中,那么返回false
    private boolean isChildInGroupChecked(int i, List<ShoppingCart_bean.DataBean> data) {
        for (int j = 0; j < data.get(i).getList().size(); j++) {
            if (data.get(i).getList().get(j).getSelected() == 0) {
                return false;
            }
        }
        return true;
    }
}
