package com.ealmrtc.bankadmin.modle;

public class PackegModel {
    String id;
    String usdtAmount;
    String usdtType;
    String walletAddress;
    String imageUrl;
    String dailyTask;
    String dailyIncome;
    String VipName;
    String taskIcome;

    public PackegModel() {
    }

    public PackegModel(String id, String usdtAmount, String usdtType, String walletAddress, String imageUrl, String dailyTask, String dailyIncome, String vipName, String taskIcome) {
        this.id = id;
        this.usdtAmount = usdtAmount;
        this.usdtType = usdtType;
        this.walletAddress = walletAddress;
        this.imageUrl = imageUrl;
        this.dailyTask = dailyTask;
        this.dailyIncome = dailyIncome;
        VipName = vipName;
        this.taskIcome = taskIcome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsdtAmount() {
        return usdtAmount;
    }

    public void setUsdtAmount(String usdtAmount) {
        this.usdtAmount = usdtAmount;
    }

    public String getUsdtType() {
        return usdtType;
    }

    public void setUsdtType(String usdtType) {
        this.usdtType = usdtType;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDailyTask() {
        return dailyTask;
    }

    public void setDailyTask(String dailyTask) {
        this.dailyTask = dailyTask;
    }

    public String getDailyIncome() {
        return dailyIncome;
    }

    public void setDailyIncome(String dailyIncome) {
        this.dailyIncome = dailyIncome;
    }

    public String getVipName() {
        return VipName;
    }

    public void setVipName(String vipName) {
        VipName = vipName;
    }

    public String getTaskIcome() {
        return taskIcome;
    }

    public void setTaskIcome(String taskIcome) {
        this.taskIcome = taskIcome;
    }
}
