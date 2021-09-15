package com.mrsoftit.ufbadmin.modle;

public class SocialLinkModle {
    String id;
    String teamSupport;
    String joinTheCommunity;
    String  helpCenter;

    public SocialLinkModle() {
    }

    public SocialLinkModle(String id, String teamSupport, String joinTheCommunity, String helpCenter) {
        this.id = id;
        this.teamSupport = teamSupport;
        this.joinTheCommunity = joinTheCommunity;
        this.helpCenter = helpCenter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeamSupport() {
        return teamSupport;
    }

    public void setTeamSupport(String teamSupport) {
        this.teamSupport = teamSupport;
    }

    public String getJoinTheCommunity() {
        return joinTheCommunity;
    }

    public void setJoinTheCommunity(String joinTheCommunity) {
        this.joinTheCommunity = joinTheCommunity;
    }

    public String getHelpCenter() {
        return helpCenter;
    }

    public void setHelpCenter(String helpCenter) {
        this.helpCenter = helpCenter;
    }
}
