package com.study.boot.jpa.dao;

import com.study.boot.jpa.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author Xingyu Sun
 * @date 2019/4/8 17:11
 */
@NoRepositoryBean
public interface CommonDao<E extends BaseEntity,K extends Serializable> extends JpaRepository<E,K>, JpaSpecificationExecutor<E> {
}
