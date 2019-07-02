package com.study.boot.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Xingyu Sun
 * @date 2019/4/8 16:39
 */
@Entity
@Table(name = "organization")
public class Organization extends BaseLongEntity<Long>{

    private String orgName;
    private String address;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
