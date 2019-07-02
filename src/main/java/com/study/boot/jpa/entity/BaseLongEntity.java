package com.study.boot.jpa.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @author Xingyu Sun
 * @date 2019/4/8 17:27
 */
@MappedSuperclass
public class BaseLongEntity<K> extends BaseEntity<K> {

    @Id
    @Column(unique = true, length = 50, updatable = false, name = "id")
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "com.study.boot.jpa.id.LongGenerator")
    protected K id;
    protected Date createTime;
    protected Date updateTime;

    public K getId() {
        return id;
    }

    public void setId(K id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
