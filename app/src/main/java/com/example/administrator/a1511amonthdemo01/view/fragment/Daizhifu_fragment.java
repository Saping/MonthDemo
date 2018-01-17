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
import com.example.administrator.a1511amonthdemo01.presenter.Daizhifu_P;
import com.example.administrator.a1511amonthdemo01.util.My_api;
import com.example.administrator.a1511amonthdemo01.view.adapter.DaizhifuAdapter;
import com.example.administrator.a1511amonthdemo01.view.inter_F.DaizhifuV_I;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/16.
 */

public class Daizhifu_fragment extends Fragment implements DaizhifuV_I {

    private RefreshLayout refreshLayout;
    private RecyclerView recycler;
    private int page = 1;
    List<Order_bean.DataBean> list = new ArrayList<>();
    private Daizhifu_P daizhifu_p;
    private DaizhifuAdapter daizhifuAdapter;
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
                daizhifu_p.getdata(My_api.ShowOrder, page);

            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.daizhifu_layout, null);

        refreshLayout = view.findViewById(R.id.refreshLayout);
        recycler = view.findViewById(R.id.recycler);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        //一进入这个页面的时候就获取数据
        daizhifu_p = new Daizhifu_P(this);
        daizhifu_p.getdata(My_api.ShowOrder, page);

        //刷新的时候就加载第一页
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //清空集合
                list.clear();
                page = 1;
                daizhifu_p.getdata(My_api.ShowOrder, page);

            }
        });
        //加载的时候
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

                page++;
                daizhifu_p.getdata(My_api.ShowOrder, page);

            }
        });

    }


    @Override
    public void success(final String s) {
        //获取到数据
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //获取到数据之后解析,设置适配器,设置布局
                Gson gson = new Gson();
                Order_bean order_bean = gson.fromJson(s, Order_bean.class);
                List<Order_bean.DataBean> data = order_bean.getData();
                list.addAll(data);
                setAdapter();

                //结束刷新
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadmore();
            }
        });

    }

    private void setAdapter() {
        if (daizhifuAdapter == null) {
            recycler.setLayoutManager(new LinearLayoutManager(getActivity(), OrientationHelper.VERTICAL, false));
            daizhifuAdapter = new DaizhifuAdapter(getActivity(), list, handler);
            recycler.setAdapter(daizhifuAdapter);
        } else {
            daizhifuAdapter.notifyDataSetChanged();
        }
    }

}
