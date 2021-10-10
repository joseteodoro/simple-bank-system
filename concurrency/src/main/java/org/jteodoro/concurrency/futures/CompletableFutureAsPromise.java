package org.jteodoro.concurrency.futures;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureAsPromise {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        
        CompletableFuture<Integer> failedFeature = CompletableFuture
            .failedFuture(new RuntimeException("something went wrong!"));
        
        CompletableFuture<Integer> afterFailed = failedFeature.handle((value, error) -> {
            if (Objects.nonNull(error)) {
                System.out.println("Error: " + error.getMessage());
                return 0;
            }
            return value;
        } );

        System.out.println("getting value: " + afterFailed.get());
    }
    
}
