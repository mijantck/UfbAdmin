package com.mrsoftit.ufbadmin.modle;


public class WithdralModel {
    String userid;
    String id;
    String userName;
    String userEmail;
    String userImageUrl;
    String userRecevNumber;
    String userRecevceMethod;
    String dateTime;
    String stuts;
    double userPreviceBalance;
    double userCurrentBalance;
    double userWithdroalCoin;
    String userUsdtInfo;

    public WithdralModel() {
    }


    public WithdralModel(String userid, String id, String userName, String userEmail, String userImageUrl, String userRecevNumber, String userRecevceMethod, String dateTime, String stuts, double userPreviceBalance, double userCurrentBalance, double userWithdroalCoin, String userUsdtInfo) {
        this.userid = userid;
        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userImageUrl = userImageUrl;
        this.userRecevNumber = userRecevNumber;
        this.userRecevceMethod = userRecevceMethod;
        this.dateTime = dateTime;
        this.stuts = stuts;
        this.userPreviceBalance = userPreviceBalance;
        this.userCurrentBalance = userCurrentBalance;
        this.userWithdroalCoin = userWithdroalCoin;
        this.userUsdtInfo = userUsdtInfo;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public String getUserRecevNumber() {
        return userRecevNumber;
    }

    public void setUserRecevNumber(String userRecevNumber) {
        this.userRecevNumber = userRecevNumber;
    }

    public String getUserRecevceMethod() {
        return userRecevceMethod;
    }

    public void setUserRecevceMethod(String userRecevceMethod) {
        this.userRecevceMethod = userRecevceMethod;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getStuts() {
        return stuts;
    }

    public void setStuts(String stuts) {
        this.stuts = stuts;
    }

    public double getUserPreviceBalance() {
        return userPreviceBalance;
    }

    public void setUserPreviceBalance(double userPreviceBalance) {
        this.userPreviceBalance = userPreviceBalance;
    }

    public double getUserCurrentBalance() {
        return userCurrentBalance;
    }

    public void setUserCurrentBalance(double userCurrentBalance) {
        this.userCurrentBalance = userCurrentBalance;
    }

    public double getUserWithdroalCoin() {
        return userWithdroalCoin;
    }

    public void setUserWithdroalCoin(double userWithdroalCoin) {
        this.userWithdroalCoin = userWithdroalCoin;
    }

    public String getUserUsdtInfo() {
        return userUsdtInfo;
    }

    public void setUserUsdtInfo(String userUsdtInfo) {
        this.userUsdtInfo = userUsdtInfo;
    }
}
