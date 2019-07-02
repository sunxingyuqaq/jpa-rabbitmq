package com.study.boot.jpa;

import com.study.boot.jpa.dao.OrganizationDao;
import com.study.boot.jpa.dao.UserDao;
import com.study.boot.jpa.entity.Organization;
import com.study.boot.jpa.entity.User;
import com.study.boot.jpa.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class JpaApplicationTests {

    @Autowired
    private UserDao dao;
    @Autowired
    private UserService service;
    @Autowired
    private OrganizationDao organizationDao;

    @Test
    @Repeat(5)
    public void contextLoads() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        user.setAge(11);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setDeleted(false);
        user.setEnable(true);
        user.setOrgId("123321");
        dao.save(user);
        assert user.getId() != null;
        System.out.println(user.getId());
        Organization organization = new Organization();
        organization.setCreateTime(new Date());
        organization.setUpdateTime(new Date());
        organization.setAddress("test");
        organization.setOrgName("sz");
        organizationDao.save(organization);
        assert organization.getId() != null;
        System.out.println(organization.getId());
        List<User> all = service.findAll();
        all.forEach(System.out::println);
    }

    @Test
    public void user(){
        List<User> test1 = service.getPage(0, 5, "test1", new Date());
        test1.forEach(System.out::println);
    }

}
