package com.son.temp3.model;

public class Links {
    private String support;
    private String teleCode;
    private String zaloCode;
    private String playNow;

    public Links()  {

    }

    public Links(String support, String teleCode, String zaloCode, String playNow) {
        this.support = support;
        this.teleCode = teleCode;
        this.zaloCode = zaloCode;
        this.playNow = playNow;
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }

    public String getTeleCode() {
        return teleCode;
    }

    public void setTeleCode(String teleCode) {
        this.teleCode = teleCode;
    }

    public String getZaloCode() {
        return zaloCode;
    }

    public void setZaloCode(String zaloCode) {
        this.zaloCode = zaloCode;
    }

    public String getPlayNow() {
        return playNow;
    }

    public void setPlayNow(String playNow) {
        this.playNow = playNow;
    }
}
