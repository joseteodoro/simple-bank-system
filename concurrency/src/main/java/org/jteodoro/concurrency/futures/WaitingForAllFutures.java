package org.jteodoro.concurrency.futures;

import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WaitingForAllFutures {

    private static CompletableFuture<String> of(String message) {
        return CompletableFuture.supplyAsync(() -> message);
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("starting at " + LocalTime.now());
        CompletableFuture<String> future1 = of("Hello");
        CompletableFuture<String> future2 = of("Beautiful");
        CompletableFuture<String> future3 = of("World");
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);

        combinedFuture.get();

        System.out.println(String.join("; ", future1.get(), future2.get(), future3.get()));
        
        CompletableFuture<String> future10 = of("A");
        CompletableFuture<String> future20 = of("S");
        CompletableFuture<String> future30 = of("D");
        String combined = Stream.of(future10, future20, future30)
            .map(CompletableFuture::join)
            .collect(Collectors.joining(" "));
        
        System.out.println(combined);
        
        System.out.println("Finished at " + LocalTime.now());
    }
    
}
