package com.study.boot.jpa.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

/**
 * @author Xingyu Sun
 * @date 2019/4/10 14:39
 */
public class RealLifeCompletableFutureExample {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        cars().thenCompose(cars -> {
            List<CompletionStage<Car>> updatedCars = cars.stream()
                    .map(car -> rating(car.getManufacturerId()).thenApply(r -> {
                        car.setRating(r);
                        return car;
                    })).collect(Collectors.toList());

            CompletableFuture<Void> done = CompletableFuture
                    .allOf(updatedCars.toArray(new CompletableFuture[0]));
            return done.thenApply(v -> updatedCars.stream().map(CompletionStage::toCompletableFuture)
                    .map(CompletableFuture::join).collect(Collectors.toList()));
        }).whenComplete((cars, th) -> {
            if (th == null) {
                cars.forEach(System.out::println);
            } else {
                throw new RuntimeException(th);
            }
        }).toCompletableFuture().join();

        long end = System.currentTimeMillis();

        System.out.println("Took " + (end - start) + " ms.");
    }

    private static CompletionStage<Float> rating(int manufacturer) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                simulateDelay();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
            switch (manufacturer) {
                case 2:
                    return 4f;
                case 3:
                    return 4.1f;
                case 7:
                    return 4.2f;
                default:
                    return 5f;
            }
        }).exceptionally(th -> -1f);
    }

    private static CompletionStage<List<Car>> cars() {
        List<Car> carList = new ArrayList<>();
        carList.add(new Car(1, 3, "Fiesta", 2017));
        carList.add(new Car(2, 7, "Camry", 2014));
        carList.add(new Car(3, 2, "M2", 2008));
        return CompletableFuture.supplyAsync(() -> carList);
    }

    private static void simulateDelay() throws InterruptedException {
        Thread.sleep(5000);
    }
}
