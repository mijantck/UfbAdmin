package com.ealmrtc.bankadmin.modle;

public class CategoryModle {

    String catName;
    String catID;

    public CategoryModle() {
    }

    public CategoryModle(String catName, String catID) {
        this.catName = catName;
        this.catID = catID;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatID() {
        return catID;
    }

    public void setCatID(String catID) {
        this.catID = catID;
    }
}
