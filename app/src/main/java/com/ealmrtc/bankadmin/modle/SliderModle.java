package com.ealmrtc.bankadmin.modle;

public class SliderModle {
    String id;
    String url;
    String name;
    String imageUrl;

    public SliderModle() {
    }

    public SliderModle(String id, String url, String name, String imageUrl) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
