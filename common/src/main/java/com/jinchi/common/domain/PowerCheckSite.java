package com.jinchi.common.domain;

public class PowerCheckSite {
    private Long code;

    private String siteName;

    public PowerCheckSite(Long code, String siteName) {
        this.code = code;
        this.siteName = siteName;
    }

    public PowerCheckSite() {
        super();
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName == null ? null : siteName.trim();
    }
}