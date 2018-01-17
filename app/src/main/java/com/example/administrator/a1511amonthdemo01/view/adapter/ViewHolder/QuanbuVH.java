package com.example.administrator.a1511amonthdemo01.view.adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.a1511amonthdemo01.R;

/**
 * Created by Administrator on 2018/1/17.
 */

public class QuanbuVH extends RecyclerView.ViewHolder {

    public TextView state;
    public TextView price;
    public TextView date;
    public Button btn;

    public QuanbuVH(View itemView) {
        super(itemView);

        //找到控件
        state = itemView.findViewById(R.id.state);
        price = itemView.findViewById(R.id.price);
        date = itemView.findViewById(R.id.date);
        btn = itemView.findViewById(R.id.btn);

    }
}
