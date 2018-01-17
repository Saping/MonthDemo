package com.example.administrator.a1511amonthdemo01.view.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.a1511amonthdemo01.R;
import com.example.administrator.a1511amonthdemo01.model.bean.Order_bean;
import com.example.administrator.a1511amonthdemo01.util.My_api;
import com.example.administrator.a1511amonthdemo01.util.OkHttp3Util;
import com.example.administrator.a1511amonthdemo01.view.adapter.ViewHolder.DaizhifuVH;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/1/17.
 */

public class DaizhifuAdapter extends RecyclerView.Adapter<DaizhifuVH> {
    private Handler handler;
    Context context;
    List<Order_bean.DataBean> list;

    public DaizhifuAdapter(Context context, List<Order_bean.DataBean> list, Handler handler) {
        this.context = context;
        this.list = list;
        this.handler = handler;
    }

    @Override
    public DaizhifuVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.daizhifu_vh, null);
        DaizhifuVH daizhifuVH = new DaizhifuVH(view);
        return daizhifuVH;
    }

    @Override
    public void onBindViewHolder(DaizhifuVH holder, final int position) {

        int status = list.get(position).getStatus();

        //赋值
        holder.state.setText("待支付");
        holder.price.setText(list.get(position).getPrice() + "");
        holder.date.setText(list.get(position).getCreatetime() + "");
        holder.btn.setText("取消订单");


//        holder.btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setTitle("提示").setMessage("确定取消订单吗?").setNegativeButton("否", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                }).setPositiveButton("是", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                        Toast.makeText(context, "点击了是!", Toast.LENGTH_SHORT).show();
//                        //点击是的时候   取消 访问修改接口
//                        HashMap<String, String> map = new HashMap<>();
//                        map.put("uid", "4123");
//                        map.put("status", "2");
//                        map.put("orderId", list.get(position).getOrderid() + "");
//
//                        OkHttp3Util.doPost(My_api.UpdataOrder, map, new Callback() {
//                            @Override
//                            public void onFailure(Call call, IOException e) {
//
//                            }
//
//                            @Override
//                            public void onResponse(Call call, Response response) throws IOException {
//                                if (response.isSuccessful()) {
//                                    String string = response.body().string();
//
//                                    Message msg = new Message();
//                                    msg.obj = string;
//                                    msg.what = 0;
//                                    handler.sendMessage(msg);
//
//                                }
//                            }
//                        });
//                        ///////////////////////////////
//                    }
//                });
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
