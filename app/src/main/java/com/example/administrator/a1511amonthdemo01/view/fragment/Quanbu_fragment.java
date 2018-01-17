package com.example.administrator.a1511amonthdemo01.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.a1511amonthdemo01.R;
import com.example.administrator.a1511amonthdemo01.model.bean.Order_bean;
import com.example.administrator.a1511amonthdemo01.presenter.Quanbu_P;
import com.example.administrator.a1511amonthdemo01.util.My_api;
import com.example.administrator.a1511amonthdemo01.view.adapter.QuanbuAdapter;
import com.example.administrator.a1511amonthdemo01.view.inter_F.QuanbuV_I;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2018/1/16.
 * 刷新加载的时候,还没有获取到数据,就结束了,所以刷新和加载结束都放在了设置完适配器之后
 * 我犯的错误  因为每次都创建了一个Adapter  所以每次向下加载没事,向上就会直接显示刷新控件
 */

public class Quanbu_fragment extends Fragment implements QuanbuV_I {

    private RefreshLayout refreshLayout;
    private RecyclerView recycler;
    private int page = 1;
    List<Order_bean.DataBean> list = new ArrayList<>();
    private Quanbu_P quanbu_p;
    private QuanbuAdapter quanbuAdapter;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                String string = (String) msg.obj;
                Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();

                //清空集合,重新获取数据 这个时候就改变了订单的状态,又重新展示了
                list.clear();
                page = 1;
                quanbu_p.getdata(My_api.ShowOrder, page);

            }

        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.quanbu_layout, null);

        refreshLayout = view.findViewById(R.id.refreshLayout);
        recycler = view.findViewById(R.id.recycler);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        //一进入这个Fragment的时候就获取数据展示recyclerView
        quanbu_p = new Quanbu_P(this);
        quanbu_p.getdata(My_api.ShowOrder, page);

        //刷新的时候就加载第一页
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //清空集合
                list.clear();
                page = 1;
                quanbu_p.getdata(My_api.ShowOrder, page);

            }
        });
        //加载的时候
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

                page++;
                quanbu_p.getdata(My_api.ShowOrder, page);

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void success(final String s) {
        //获取到传回来的值
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //解析 展示
                Gson gson = new Gson();
                Order_bean order_bean = gson.fromJson(s, Order_bean.class);
                List<Order_bean.DataBean> data = order_bean.getData();
                list.addAll(data);
//                Collections.reverse(list);  反转  把数据反过来展示  不过没效果
                setAdapter();

                refreshLayout.finishRefresh();
                refreshLayout.finishLoadmore();
            }
        });
    }

    private void setAdapter() {
        if (quanbuAdapter == null) {
            recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
            quanbuAdapter = new QuanbuAdapter(getActivity(), list, handler);
            recycler.setAdapter(quanbuAdapter);
        } else {
            //刷新适配器
            quanbuAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //解除绑定
        if (quanbu_p != null) {
            quanbu_p.destory();
        }
    }
}
