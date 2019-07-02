package com.study.boot.jpa;

import com.study.boot.jpa.app.BaseApplicationInit;
import com.study.boot.jpa.dao.OrganizationDao;
import com.study.boot.jpa.dao.UserDao;
import com.study.boot.jpa.entity.Organization;
import com.study.boot.jpa.entity.User;
import com.study.boot.jpa.id.SnowFlakeId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author sxy
 */
@EnableScheduling
@SpringBootApplication
public class JpaApplication extends BaseApplicationInit {

    private final static Logger log = LoggerFactory.getLogger(JpaApplication.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private OrganizationDao organizationDao;

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            try {
                log.info("hook thread running {}", LocalDateTime.now());
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("hook thread will exit. {}", LocalDateTime.now());
        }));
    }

    @Bean
    @ConditionalOnMissingBean
    public SnowFlakeId snowFlakeId() {
        return new SnowFlakeId(0, 0);
    }

    @Override
    protected void appRun(String... args) {
        final int count = 20;
        for (int i = 0; i < count; i++) {
            List<User> all = userDao.findAll();
            if (all.size() < 20) {
                User user = new User();
                user.setUsername("test" + i);
                user.setPassword("test" + i);
                user.setAge(i);
                user.setCreateTime(new Date());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                user.setUpdateTime(new Date());
                user.setDeleted(false);
                user.setEnable(true);
                user.setOrgId("123321" + i);
                userDao.save(user);
            }
            List<Organization> organizationList = organizationDao.findAll();
            if (organizationList.size() < 10) {
                List<Organization> list = new ArrayList<>();
                final int count2 = 10;
                for (int j = 0; j < count2; j++) {
                    Organization organization = new Organization();
                    organization.setOrgName("org_" + j);
                    organization.setAddress("address_" + j);
                    organization.setUpdateTime(new Date());
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    organization.setCreateTime(new Date());
                    list.add(organization);
                }
                organizationDao.saveAll(list);
            }
        }
    }
}
