package com.study.boot.jpa.service;

import com.study.boot.jpa.dao.CommonDao;
import com.study.boot.jpa.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * @author Xingyu Sun
 * @date 2019/4/8 17:04
 */
public class CommonService<E extends BaseEntity,K extends Serializable,D extends CommonDao<E,K>>{

    @Autowired(required = false)
    private D dao;

    public D getDao() {
        return dao;
    }

    public void setDao(D dao) {
        this.dao = dao;
    }
}
