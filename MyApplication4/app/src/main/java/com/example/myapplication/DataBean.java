package com.example.myapplication;

public class DataBean {

    private int id;
    private String name;
    private String time;
    private String count;
    public DataBean(int id,String name,String time,String count){
        this.id=id;
        this.name=name;
        this.count=count;
        this.time=time;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }



}
