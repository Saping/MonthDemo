package com.example.administrator.a1511amonthdemo01.view.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.a1511amonthdemo01.R;
import com.example.administrator.a1511amonthdemo01.model.bean.CountPriceBean;
import com.example.administrator.a1511amonthdemo01.model.bean.ShoppingCart_bean;
import com.example.administrator.a1511amonthdemo01.presenter.ShoppingCart_P;
import com.example.administrator.a1511amonthdemo01.util.My_api;
import com.example.administrator.a1511amonthdemo01.util.OkHttp3Util;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/1/16.
 */

public class Myadapter extends BaseExpandableListAdapter{


    private  ShoppingCart_P shoppingCart_p;
    private Handler handler;
    private Context context;
    private  List<ShoppingCart_bean.DataBean> list;
    private int allIndex;
    private int childIndex;


    public Myadapter(Context context, List<ShoppingCart_bean.DataBean> list, Handler handler, ShoppingCart_P shoppingCart_p) {
        this.context = context;
        this.list = list;
        this.handler = handler;
        this.shoppingCart_p = shoppingCart_p;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return list.get(i).getList().size();
    }

    @Override
    public Object getGroup(int i) {
        return list.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return list.get(i).getList().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        final groupVH groupVH;
        if(view==null){
            groupVH = new groupVH();
            view = View.inflate(context, R.layout.group_layout,null);
            groupVH.group_check = view.findViewById(R.id.group_check);
            groupVH.group_name = view.findViewById(R.id.group_name);

            view.setTag(groupVH);
        }else{
            groupVH = (groupVH) view.getTag();
        }

        final ShoppingCart_bean.DataBean dataBean = list.get(i);

        groupVH.group_check.setChecked(list.get(i).isGroup_check());
        groupVH.group_name.setText(list.get(i).getSellerName());
        //点击一届列表单选框
        groupVH.group_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                childIndex = 0;
                //更新      参数当前一级列表的选择状态    一级列表对象
                updateChildCheckedInGroup(groupVH.group_check.isChecked(),dataBean);
            }
        });
        return view;
    }

    //一级列表
    private void updateChildCheckedInGroup(final boolean checked, final ShoppingCart_bean.DataBean dataBean) {
        ShoppingCart_bean.DataBean.ListBean listBean = dataBean.getList().get(childIndex);

        HashMap<String, String> map = new HashMap<>();
        map.put("uid","4123");
        map.put("sellerid",listBean.getSellerid()+"");
        map.put("pid",listBean.getPid()+"");
        map.put("num",listBean.getNum()+"");
        map.put("selected", String.valueOf(checked?1:0));

        OkHttp3Util.doPost(My_api.UpdataShoppingCart, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                childIndex++;
               if(response.isSuccessful()){
                   if(childIndex<dataBean.getList().size()){
                       updateChildCheckedInGroup(checked,dataBean);
                   }else{
                       shoppingCart_p.getdata(My_api.ShoppingCart);
                   }
               }
            }
        });

    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        childVH childVH;
        if(view==null){
           view = View.inflate(context,R.layout.child_layout,null);
           childVH = new childVH();
           childVH.child_check = view.findViewById(R.id.child_check);
           childVH.image = view.findViewById(R.id.image);
           childVH.content = view .findViewById(R.id.content);
           childVH.beginPrice = view.findViewById(R.id.beginPrice);
           childVH.num = view.findViewById(R.id.num);
           childVH.jia = view.findViewById(R.id.jia);
           childVH.jian=view.findViewById(R.id.jian);
           childVH.delete=view.findViewById(R.id.delete);

           view.setTag(childVH);
      }else{
            childVH = (childVH) view.getTag();
        }
        //赋值
        final ShoppingCart_bean.DataBean.ListBean listBean = list.get(i).getList().get(i1);

        childVH.child_check.setChecked(listBean.getSelected()==0?false:true);
        if(listBean.getImages()!=null){
            String[] split = listBean.getImages().split("\\|");
            Glide.with(context).load(split[0]).into(childVH.image);
            childVH.content.setText(listBean.getTitle()+"");
            childVH.beginPrice.setText("￥:"+listBean.getBargainPrice());
            childVH.num.setText(listBean.getNum()+"");
            //加
            childVH.jia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //更新接口
                    HashMap<String, String> map = new HashMap<>();
                    map.put("uid","4123");
                    map.put("sellerid",listBean.getSellerid()+"");
                    map.put("pid",listBean.getPid()+"");
                    map.put("num",listBean.getNum()+1+"");
                    map.put("selected", String.valueOf(listBean.getSelected()));

                    OkHttp3Util.doPost(My_api.UpdataShoppingCart, map, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            //更新成功之后,再次请求查询购物车的接口,进行数据的展示
                            if (response.isSuccessful()){
                                shoppingCart_p.getdata(My_api.ShoppingCart);
                            }
                        }
                    });

                }
            });
            //减号
            childVH.jian.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int num = listBean.getNum();
                    if (num == 1){
                        return;
                    }

                    Map<String, String> params = new HashMap<>();
                    params.put("uid","4123");
                    params.put("sellerid", String.valueOf(listBean.getSellerid()));
                    params.put("pid", String.valueOf(listBean.getPid()));
                    params.put("selected", String.valueOf(listBean.getSelected()));//listBean.getSelected()...0--->1,,,1--->0
                    params.put("num", String.valueOf(num - 1));

                    OkHttp3Util.doPost(My_api.UpdataShoppingCart, params, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            //更新成功之后,再次请求查询购物车的接口,进行数据的展示
                            if (response.isSuccessful()){
                                shoppingCart_p.getdata(My_api.ShoppingCart);
                            }
                        }
                    });

                }
            });
            //删除
            childVH.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("uid","4123");
                    map.put("pid",listBean.getPid()+"");

                    OkHttp3Util.doPost(My_api.DeleteShoppingCart, map, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            //更新成功之后,再次请求查询购物车的接口,进行数据的展示
                            if (response.isSuccessful()){
                                shoppingCart_p.getdata(My_api.ShoppingCart);
                            }
                        }
                    });
                }
            });

            //点击二级列表选择框
            childVH.child_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //刷新接口,重新展示
                    HashMap<String, String> map = new HashMap<>();
                    map.put("uid","4123");
                    map.put("sellerid",listBean.getSellerid()+"");
                    map.put("pid",listBean.getPid()+"");
                    map.put("num",listBean.getNum()+"");
                    map.put("selected", String.valueOf(listBean.getSelected()==0?1:0));

                    OkHttp3Util.doPost(My_api.UpdataShoppingCart, map, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                           if(response.isSuccessful()){
                               shoppingCart_p.getdata(My_api.ShoppingCart);
                           }
                        }
                    });
                }
            });

        }

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    //当点击全选按钮的时候
    public void setAllChildChecked(boolean checked) {
        //通过遍历所有孩子添加到一个集合
        ArrayList<ShoppingCart_bean.DataBean.ListBean> allList = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            for(int j=0;j<list.get(i).getList().size();j++){
                allList.add(list.get(i).getList().get(j));
            }
        }
        //更新每一个子孩子的状态...递归
        allIndex = 0;
        //修改所有二级列表的状态,  参数  当前全选的状态,所有的二级列表
        updateAllChild(checked,allList);
    }

    //修改所有二级列表的状态
    private void updateAllChild(final boolean checked, final ArrayList<ShoppingCart_bean.DataBean.ListBean> allList) {
        //集合中当前二级列表对象
        final ShoppingCart_bean.DataBean.ListBean listBean = allList.get(allIndex);
        //请求更新的接口
        //刷新接口,重新展示
        HashMap<String, String> map = new HashMap<>();
        map.put("uid","4123");
        map.put("sellerid",listBean.getSellerid()+"");
        map.put("pid",listBean.getPid()+"");
        map.put("num",listBean.getNum()+"");
        map.put("selected", String.valueOf(checked?1:0));

       OkHttp3Util.doPost(My_api.UpdataShoppingCart, map, new Callback() {
           @Override
           public void onFailure(Call call, IOException e) {

           }

           @Override
           public void onResponse(Call call, Response response) throws IOException {

            if(response.isSuccessful()){
                allIndex++;
                if(allIndex<allList.size()){
                    //继续更新
                    updateAllChild(checked,allList);
                }else{
                    //展示
                    shoppingCart_p.getdata(My_api.ShoppingCart);
                }
            }
           }
       });
    }

    //计算价格和数目的,算好后封装传回Activity进行展示
    public void sendPriceAndCount() {
        double price = 0;
        int count = 0;

        //循环
        for(int i=0;i<list.size();i++){
            List<ShoppingCart_bean.DataBean.ListBean> list = this.list.get(i).getList();
            for(int j=0;j<list.size();j++){
                ShoppingCart_bean.DataBean.ListBean listBean = list.get(j);
                //当选中状态下进行计算
                if(listBean.getSelected()==1){
                    price+=listBean.getBargainPrice()*listBean.getNum();
                    count+=listBean.getNum();
                }
            }
            //让价钱更精准一点
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            String priceString = decimalFormat.format(price);
            //封装成对象,发挥主页面

            CountPriceBean countPriceBean = new CountPriceBean(priceString, count);

            //发送给activity/fragment进行显示

            Message msg = Message.obtain();

            msg.what = 0;
            msg.obj = countPriceBean;
            handler.sendMessage(msg);

        }

    }

    class groupVH{
        CheckBox group_check;
        TextView group_name;
    }
    class childVH{
        CheckBox child_check;
        ImageView image;
        TextView content;
        TextView beginPrice;
        TextView num;
        TextView jia;
        TextView jian;
        TextView delete;

    }
}
