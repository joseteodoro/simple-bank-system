package org.jteodoro.concurrency.futures;

import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BasicUsageMain {
    

    public static Future<String> calculateAsync() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
    
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        newCachedThreadPool.submit(() -> {
            Thread.sleep(2500);
            completableFuture.complete("Hello");
            return null;
        });
    
        return completableFuture.thenApply(str -> {
            newCachedThreadPool.shutdown();
            return str;
        });
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("starter at "+ LocalTime.now());
        Future<String> str = calculateAsync();
        System.out.println("output: " + str.get());
        System.out.println("Finished at "+ LocalTime.now());
    }
}
