package com.hzcf.flagship.model;

import java.util.Date;

public class FileMapping {
    private Long id;

    private String code;

    private String info;

    private String type;

    private String dfsAddress;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getDfsAddress() {
        return dfsAddress;
    }

    public void setDfsAddress(String dfsAddress) {
        this.dfsAddress = dfsAddress == null ? null : dfsAddress.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}