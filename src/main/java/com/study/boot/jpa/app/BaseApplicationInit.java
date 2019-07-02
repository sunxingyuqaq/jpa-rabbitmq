package com.study.boot.jpa.app;

import org.springframework.boot.CommandLineRunner;

/**
 * @author Xingyu Sun
 * @date 2019/4/9 10:12
 */
public abstract class BaseApplicationInit implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        appRun(args);
    }

    /**
     * 测试
     *
     * @param args a
     * @apiNote appRun a
     */
    protected abstract void appRun(String... args);
}
