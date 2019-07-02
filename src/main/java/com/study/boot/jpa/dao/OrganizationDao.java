package com.study.boot.jpa.dao;

import com.study.boot.jpa.entity.Organization;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Xingyu Sun
 * @date 2019/4/8 15:00
 */
@Transactional(rollbackFor = Exception.class)
public interface OrganizationDao extends CommonDao<Organization,Long>{

}
