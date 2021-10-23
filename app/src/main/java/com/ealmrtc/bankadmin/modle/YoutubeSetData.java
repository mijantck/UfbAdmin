package com.ealmrtc.bankadmin.modle;

public class YoutubeSetData {

    String id;
    String youtubeChannelName;
    String youtubeChannellogoUrl;
    String youtubeVideoName;
    String youtubeVideUrl;
    String youtubeImageUrl;
    String youtubeVideId;
    String youtubeCatagory;
    String youtubeDetails;


    public YoutubeSetData() {
    }

    public YoutubeSetData(String youtubeVideUrl, String youtubeVideId) {
        this.youtubeVideUrl = youtubeVideUrl;
        this.youtubeVideId = youtubeVideId;
    }

    public YoutubeSetData(String youtubeVideoName, String youtubeVideUrl, String youtubeImageUrl) {
        this.youtubeVideoName = youtubeVideoName;
        this.youtubeVideUrl = youtubeVideUrl;
        this.youtubeImageUrl = youtubeImageUrl;
    }


    public YoutubeSetData(String id, String youtubeChannelName, String youtubeChannellogoUrl, String youtubeVideoName, String youtubeVideUrl, String youtubeImageUrl, String youtubeVideId, String youtubeCatagory, String youtubeDetails) {
        this.id = id;
        this.youtubeChannelName = youtubeChannelName;
        this.youtubeChannellogoUrl = youtubeChannellogoUrl;
        this.youtubeVideoName = youtubeVideoName;
        this.youtubeVideUrl = youtubeVideUrl;
        this.youtubeImageUrl = youtubeImageUrl;
        this.youtubeVideId = youtubeVideId;
        this.youtubeCatagory = youtubeCatagory;
        this.youtubeDetails = youtubeDetails;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYoutubeCatagory() {
        return youtubeCatagory;
    }

    public void setYoutubeCatagory(String youtubeCatagory) {
        this.youtubeCatagory = youtubeCatagory;
    }

    public String getYoutubeDetails() {
        return youtubeDetails;
    }

    public void setYoutubeDetails(String youtubeDetails) {
        this.youtubeDetails = youtubeDetails;
    }

    public String getYoutubeChannelName() {
        return youtubeChannelName;
    }

    public void setYoutubeChannelName(String youtubeChannelName) {
        this.youtubeChannelName = youtubeChannelName;
    }

    public String getYoutubeChannellogoUrl() {
        return youtubeChannellogoUrl;
    }

    public void setYoutubeChannellogoUrl(String youtubeChannellogoUrl) {
        this.youtubeChannellogoUrl = youtubeChannellogoUrl;
    }

    public String getYoutubeVideoName() {
        return youtubeVideoName;
    }

    public void setYoutubeVideoName(String youtubeVideoName) {
        this.youtubeVideoName = youtubeVideoName;
    }

    public String getYoutubeVideUrl() {
        return youtubeVideUrl;
    }

    public void setYoutubeVideUrl(String youtubeVideUrl) {
        this.youtubeVideUrl = youtubeVideUrl;
    }

    public String getYoutubeImageUrl() {
        return youtubeImageUrl;
    }

    public void setYoutubeImageUrl(String youtubeImageUrl) {
        this.youtubeImageUrl = youtubeImageUrl;
    }

    public String getYoutubeVideId() {
        return youtubeVideId;
    }

    public void setYoutubeVideId(String youtubeVideId) {
        this.youtubeVideId = youtubeVideId;
    }
}


