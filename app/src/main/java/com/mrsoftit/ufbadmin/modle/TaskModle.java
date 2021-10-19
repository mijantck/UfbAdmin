package com.mrsoftit.ufbadmin.modle;

public class TaskModle {
    String id;
    String name;
    String url;
    long time;
    double coin;
    String taskType;
    long date;
    String vip;
    String category;



    public TaskModle() {
    }

    public TaskModle(String id, String name, String url, long time, double coin, String taskType, long date, String vip, String category) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.time = time;
        this.coin = coin;
        this.taskType = taskType;
        this.date = date;
        this.vip = vip;
        this.category = category;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getCoin() {
        return coin;
    }

    public void setCoin(double coin) {
        this.coin = coin;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
