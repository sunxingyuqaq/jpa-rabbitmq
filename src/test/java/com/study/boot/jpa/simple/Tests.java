package com.study.boot.jpa.simple;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.study.boot.jpa.model.Shop;
import com.study.boot.jpa.utils.Java8Utils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author Xingyu Sun
 * @date 2019/4/10 10:11
 */
public class Tests {

    private final Logger log = LoggerFactory.getLogger(Tests.class);

    @Test
    public void test() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Double> submit = executorService.submit(this::doSomeThing);
        doSomeThingElse();
        try {
            Double result = submit.get(2, TimeUnit.SECONDS);
            System.out.println("result = " + result);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    private void doSomeThingElse() {
        System.out.println("doSomeThingElse");
    }

    private Double doSomeThing() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Random().nextDouble();
    }

    @Test
    public void sync() {
        Shop shop = new Shop("shop");
        long l = System.nanoTime();
        double price = shop.getPrice();
        long time = (System.nanoTime() - l) / 1000000;
        System.out.println("time" + time);
        System.out.println("do something else");
        System.out.printf("price is %.2f%n", price);
        long real = (System.nanoTime() - l) / 1000000;
        System.out.println("real" + real);
    }

    @Test
    public void asynchronous() {
        Shop shop = new Shop("shop");
        long l = System.nanoTime();
        Future<Double> okShop = shop.getPriceAsync("ok shop");
        long time = (System.nanoTime() - l) / 1000000;
        System.out.println("time" + time);
        System.out.println("do something else");
        try {
            Double price = okShop.get();
            System.out.printf("price is %.2f%n", price);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        long real = (System.nanoTime() - l) / 1000000;
        System.out.println("real" + real);
    }

    @Test
    public void tests() {
        long l = System.currentTimeMillis();
        findPriceByCom();
        long time = System.currentTimeMillis() - l;
        System.out.println("time" + time);
    }

    public List<String> findPrice() {
        return Java8Utils.getShops().parallelStream().map(x -> String.format("%s price is %.2f", x.getName(), x.getPrice())).collect(Collectors.toList());
    }

    public List<String> findPriceByCom() {
        List<CompletableFuture<String>> collect = Java8Utils.getShops().stream().map(x -> CompletableFuture.supplyAsync(() -> x.getName() + " price is " + x.getPrice())).collect(Collectors.toList());
        return collect.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    @Test
    public void supply() {
        CompletableFuture.supplyAsync(() -> "111")
                .thenAccept(x -> System.out.println(x + "1112222"));
    }

    @Test
    public void test1() {
        long start = System.currentTimeMillis();
        // 结果集
        List<String> list = new ArrayList<>();

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        List<Integer> taskList = Arrays.asList(2, 1, 3, 4, 5, 6, 7, 8, 9, 10);
        // 全流式处理转换成CompletableFuture[]+组装成一个无返回值CompletableFuture，join等待执行完毕。返回结果whenComplete获取
        CompletableFuture[] cfs = taskList.stream()
                .map(integer -> CompletableFuture.supplyAsync(() -> calc(integer), executorService)
                        .thenApply(String::valueOf)
                        .whenComplete((s, e) -> {
                            log.info("任务" + s + "完成!result=" + s + "，异常 e=" + e + "," + new Date());
                            list.add(s);
                        })
                ).toArray(CompletableFuture[]::new);
        // 封装后无返回值，必须自己whenComplete()获取
        CompletableFuture.allOf(cfs).join();
        executorService.shutdownNow();
        log.info("list=" + list + ",耗时=" + (System.currentTimeMillis() - start));
    }

    private int calc(Integer i) {
        try {
            if (i == 1) {
                Thread.sleep(3000);//任务1耗时3秒
            } else if (i == 5) {
                Thread.sleep(5000);//任务5耗时5秒
            } else {
                Thread.sleep(1000);//其它任务耗时1秒
            }
            System.out.println("task线程：" + Thread.currentThread().getName()
                    + "任务i=" + i + ",完成！+" + new Date());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i;
    }

    @Test
    public void list(){
        ArrayListMultimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("ok","1");
        multimap.put("ok","2");
        System.out.println(multimap.asMap());
        StringBuilder stringBuilder = Joiner.on(";").skipNulls().appendTo(new StringBuilder("112"), Lists.newArrayList("11", "22", "33", "44"));
        System.out.println(stringBuilder);
        Iterable<String> sad = Splitter.onPattern("sad").omitEmptyStrings().split("asdasfasfsadfdsfdsasd");
        sad.forEach(System.out::println);
    }
}
