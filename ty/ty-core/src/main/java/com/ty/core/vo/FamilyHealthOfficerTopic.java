package com.ty.core.vo;

import java.util.Date;

public class FamilyHealthOfficerTopic {
    private Long id;

    private Date addtime;

    private Boolean deletestatus;

    private String medalnum;

    private String name;

    private Integer px;

    private Integer status;

    private Long accessoryId;

    private Integer joinnum;

    private String info;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Boolean getDeletestatus() {
        return deletestatus;
    }

    public void setDeletestatus(Boolean deletestatus) {
        this.deletestatus = deletestatus;
    }

    public String getMedalnum() {
        return medalnum;
    }

    public void setMedalnum(String medalnum) {
        this.medalnum = medalnum == null ? null : medalnum.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getPx() {
        return px;
    }

    public void setPx(Integer px) {
        this.px = px;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getAccessoryId() {
        return accessoryId;
    }

    public void setAccessoryId(Long accessoryId) {
        this.accessoryId = accessoryId;
    }

    public Integer getJoinnum() {
        return joinnum;
    }

    public void setJoinnum(Integer joinnum) {
        this.joinnum = joinnum;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }
}