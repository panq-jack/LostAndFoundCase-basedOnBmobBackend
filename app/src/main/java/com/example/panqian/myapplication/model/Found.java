package com.example.panqian.myapplication.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by panqian on 2016/6/8.
 * 招领
 */
public class Found extends BmobObject {
    private String title;
    private String desc;
    private String phone;
    public Found(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
