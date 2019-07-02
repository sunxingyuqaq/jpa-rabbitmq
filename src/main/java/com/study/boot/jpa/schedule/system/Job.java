package com.study.boot.jpa.schedule.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Xingyu Sun
 * @date 2019/4/12 13:45
 */
@Component
public class Job {

    private List<Integer> index = Arrays.asList(8000, 3000, 6000, 2000, 2000);
    private final Logger logger = LoggerFactory.getLogger(Job.class);
    private AtomicInteger integer = new AtomicInteger(0);

    @Scheduled(fixedDelay = 3 * 1000)
    public void fixedDelay() {
        if (integer.get() < 5) {
            Integer integer = index.get(this.integer.get());
            try {
                logger.info("第{}个任务开始执行,执行时间为{}ms", this.integer, integer);
                Thread.sleep(integer);
                this.integer.getAndIncrement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
