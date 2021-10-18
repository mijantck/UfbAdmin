package com.mrsoftit.ufbadmin.modle;

public class WelcomeLink {
    String channleLink;
    String name;
    String image;
    String cId;
    String someTextUser;
     int coin;

    public WelcomeLink() {
    }


    public WelcomeLink(String channleLink, String name, String image, String cId, String someTextUser, int coin) {
        this.channleLink = channleLink;
        this.name = name;
        this.image = image;
        this.cId = cId;
        this.someTextUser = someTextUser;
        this.coin = coin;
    }


    public String getChannleLink() {
        return channleLink;
    }

    public void setChannleLink(String channleLink) {
        this.channleLink = channleLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getSomeTextUser() {
        return someTextUser;
    }

    public void setSomeTextUser(String someTextUser) {
        this.someTextUser = someTextUser;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }
}
