package com.study.boot.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Xingyu Sun
 * @date 2019/4/8 14:53
 */
@Entity
@Table(name = "user")
public class User extends BaseStringEntity<String>{
    private static final long serialVersionUID = 826505866540373514L;
    private String username;
    private String password;
    private Integer age;
    private Boolean enable;
    private Boolean deleted;
    private String orgId;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", enable=" + enable +
                ", deleted=" + deleted +
                ", orgId='" + orgId + '\'' +
                ", id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
