package com.mrsoftit.ufbadmin.modle;

public class UserModle {
    String userName;
    String userEmail;
    String userImage;
    String referCode;
    String uId;
    int coin;
    int rCoin;
    int lifeCoin;
    String active;

    public UserModle() {

    }

    public UserModle(String userName, String userEmail, String userImage, String referCode, String uId, int coin, int rCoin, int lifeCoin, String active) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userImage = userImage;
        this.referCode = referCode;
        this.uId = uId;
        this.coin = coin;
        this.rCoin = rCoin;
        this.lifeCoin = lifeCoin;
        this.active = active;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getReferCode() {
        return referCode;
    }

    public void setReferCode(String referCode) {
        this.referCode = referCode;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getrCoin() {
        return rCoin;
    }

    public void setrCoin(int rCoin) {
        this.rCoin = rCoin;
    }

    public int getLifeCoin() {
        return lifeCoin;
    }

    public void setLifeCoin(int lifeCoin) {
        this.lifeCoin = lifeCoin;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
