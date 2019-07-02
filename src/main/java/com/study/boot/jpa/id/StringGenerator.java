package com.study.boot.jpa.id;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerator;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Xingyu Sun
 * @date 2019/4/8 15:10
 */
public class StringGenerator extends UUIDGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor s, Object obj) {
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
