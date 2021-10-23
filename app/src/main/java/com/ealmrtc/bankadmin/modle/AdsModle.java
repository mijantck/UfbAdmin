package com.ealmrtc.bankadmin.modle;

public class AdsModle {
    String adsNo;
    String sdsType;
    String bannarAdsUnit;
    String intrastialAdsUnit;
    String rewordAdsUnit;
    String nativeAds;

    public AdsModle() {
    }

    public AdsModle(String adsNo, String sdsType, String bannarAdsUnit, String intrastialAdsUnit, String rewordAdsUnit, String nativeAds) {
        this.adsNo = adsNo;
        this.sdsType = sdsType;
        this.bannarAdsUnit = bannarAdsUnit;
        this.intrastialAdsUnit = intrastialAdsUnit;
        this.rewordAdsUnit = rewordAdsUnit;
        this.nativeAds = nativeAds;
    }

    public String getAdsNo() {
        return adsNo;
    }

    public void setAdsNo(String adsNo) {
        this.adsNo = adsNo;
    }

    public String getSdsType() {
        return sdsType;
    }

    public void setSdsType(String sdsType) {
        this.sdsType = sdsType;
    }

    public String getBannarAdsUnit() {
        return bannarAdsUnit;
    }

    public void setBannarAdsUnit(String bannarAdsUnit) {
        this.bannarAdsUnit = bannarAdsUnit;
    }

    public String getIntrastialAdsUnit() {
        return intrastialAdsUnit;
    }

    public void setIntrastialAdsUnit(String intrastialAdsUnit) {
        this.intrastialAdsUnit = intrastialAdsUnit;
    }

    public String getRewordAdsUnit() {
        return rewordAdsUnit;
    }

    public void setRewordAdsUnit(String rewordAdsUnit) {
        this.rewordAdsUnit = rewordAdsUnit;
    }

    public String getNativeAds() {
        return nativeAds;
    }

    public void setNativeAds(String nativeAds) {
        this.nativeAds = nativeAds;
    }
}
