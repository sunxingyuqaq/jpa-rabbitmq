package com.study.boot.jpa.service;

import com.study.boot.jpa.dao.UserDao;
import com.study.boot.jpa.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Path;
import java.util.Date;
import java.util.List;

/**
 * @author Xingyu Sun
 * @date 2019/4/8 17:15
 */
@Service
public class UserService extends CommonService<User, String, UserDao> {

    public List<User> findAll() {
        return getDao().findAll();
    }

    public Page<User> getPage(Integer pageNumber, Integer pageSize){
        return getDao().findAll(PageRequest.of(pageNumber, pageSize,new Sort(Sort.Direction.ASC,"createTime")));
    }

    public List<User> getPage(int page, int size, String name, Date date) {
        Page<User> users = getDao().findAll((Specification<User>) (root, criteriaQuery, criteriaBuilder) -> {
            Path<String> username = root.get("username");
            Path<Date> createTime = root.get("createTime");
            return criteriaBuilder.and(criteriaBuilder.like(username, "%" + name + "%"), criteriaBuilder.lessThan(createTime, date));
        }, PageRequest.of(page, size));
        return users.getContent();
    }

}
