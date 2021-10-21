package com.mrsoftit.ufbadmin.modle;

public class VipPurchechModel {
    String id;
    String userName;
    String userId;
    String userPhone;
    String userWallet;
    String orderId;
    String date;
    String orderType;
    String userAccount;
    String oderStatus;
    String imageUrlr;
    String myRefer;
    String UsdtAmount;

    public VipPurchechModel() {
    }

    public VipPurchechModel(String id, String userName, String userId, String userPhone, String userWallet, String orderId, String date, String orderType, String userAccount, String oderStatus, String imageUrlr, String myRefer, String usdtAmount) {
        this.id = id;
        this.userName = userName;
        this.userId = userId;
        this.userPhone = userPhone;
        this.userWallet = userWallet;
        this.orderId = orderId;
        this.date = date;
        this.orderType = orderType;
        this.userAccount = userAccount;
        this.oderStatus = oderStatus;
        this.imageUrlr = imageUrlr;
        this.myRefer = myRefer;
        UsdtAmount = usdtAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserWallet() {
        return userWallet;
    }

    public void setUserWallet(String userWallet) {
        this.userWallet = userWallet;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getOderStatus() {
        return oderStatus;
    }

    public void setOderStatus(String oderStatus) {
        this.oderStatus = oderStatus;
    }

    public String getImageUrlr() {
        return imageUrlr;
    }

    public void setImageUrlr(String imageUrlr) {
        this.imageUrlr = imageUrlr;
    }

    public String getMyRefer() {
        return myRefer;
    }

    public void setMyRefer(String myRefer) {
        this.myRefer = myRefer;
    }

    public String getUsdtAmount() {
        return UsdtAmount;
    }

    public void setUsdtAmount(String usdtAmount) {
        UsdtAmount = usdtAmount;
    }
}
