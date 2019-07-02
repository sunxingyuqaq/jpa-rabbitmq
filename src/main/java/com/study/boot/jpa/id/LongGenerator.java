package com.study.boot.jpa.id;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author Xingyu Sun
 * @date 2019/4/8 15:10
 */
@Component
public class LongGenerator extends IdentityGenerator implements ApplicationContextAware {

    @Autowired
    private static ApplicationContext context;

    @Override
    public Serializable generate(SharedSessionContractImplementor s, Object obj) {
        SnowFlakeId bean = context.getBean(SnowFlakeId.class);
        Serializable id = bean.nextId();
        if (id != null) {
            return id;
        }
        return super.generate(s, obj);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
