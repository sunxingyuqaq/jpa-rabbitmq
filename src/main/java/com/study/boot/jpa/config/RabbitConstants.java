package com.study.boot.jpa.config;

/**
 * @author Xingyu Sun
 * @date 2019/1/14 13:47
 */
public class RabbitConstants {

    public static class Queue{
        public static final String TEST_QUEUE = "queue_test";
        public static final String QUEUE = "queue_yu";
    }

    public static class Exchange{
        public static final String TEST_EXCHANGE = "exchange_test";
        public static final String EXCHANGE = "exchange_yu";
    }

    public static class RoutingKey{
        public static final String KEY_1 = "rabbit.#";
        public static final String KEY_2 = "rabbit.*";
    }
}
