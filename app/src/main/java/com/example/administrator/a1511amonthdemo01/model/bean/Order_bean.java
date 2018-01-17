package com.example.administrator.a1511amonthdemo01.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/17.
 */

public class Order_bean {
    /**
     * msg : 请求成功
     * code : 0
     * data : [{"createtime":"2017-12-20T14:13:28","orderid":3769,"price":269.78,"status":1,"title":"订单测试标题4123","uid":4123},{"createtime":"2017-12-20T14:53:23","orderid":3870,"price":269.78,"status":1,"title":"订单测试标题4123","uid":4123},{"createtime":"2017-12-20T15:09:56","orderid":3921,"price":1053.71,"status":1,"title":"订单测试标题4123","uid":4123},{"createtime":"2017-12-20T15:11:06","orderid":3925,"price":1053.71,"status":1,"title":"订单测试标题4123","uid":4123},{"createtime":"2017-12-20T19:44:31","orderid":4251,"price":335.97,"status":0,"title":"订单测试标题4123","uid":4123},{"createtime":"2017-12-20T20:24:49","orderid":4276,"price":335.97,"status":0,"title":"订单测试标题4123","uid":4123},{"createtime":"2017-12-20T20:30:03","orderid":4290,"price":783.93,"status":0,"title":"订单测试标题4123","uid":4123},{"createtime":"2017-12-20T20:32:37","orderid":4304,"price":783.93,"status":0,"title":"订单测试标题4123","uid":4123},{"createtime":"2017-12-20T20:35:03","orderid":4311,"price":783.93,"status":0,"title":"订单测试标题4123","uid":4123},{"createtime":"2017-12-20T20:36:33","orderid":4313,"price":783.93,"status":0,"title":"订单测试标题4123","uid":4123}]
     * page : 0
     */

    private String msg;
    private String code;
    private String page;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * createtime : 2017-12-20T14:13:28
         * orderid : 3769
         * price : 269.78
         * status : 1
         * title : 订单测试标题4123
         * uid : 4123
         */

        private String createtime;
        private int orderid;
        private double price;
        private int status;
        private String title;
        private int uid;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
