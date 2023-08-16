package com.example.springdemo.mybatis.entity;

//定义一个Food实体类
//实体类通常用于映射关系型数据库中的表结构，每个属性对应表中的列。这样可以方便地进行数据的存取和操作。
public class TFood {
    private String id;
    private String name;
    private String price;
    private String msg;
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
