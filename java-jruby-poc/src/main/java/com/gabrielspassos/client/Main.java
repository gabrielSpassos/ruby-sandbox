package com.gabrielspassos.client;

import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Client client = new Client();
        var executor = Executors.newFixedThreadPool(100);
        Callable<Long> wrapper = client::run;
        var callableTasks = Collections.nCopies(100, wrapper);
        var concurrentResponses = executor.invokeAll(callableTasks);

        executor.shutdownNow();
        executor.close();

        var min = concurrentResponses.stream()
                .map(Main::getFutureValue)
                .mapToLong(v -> v)
                .min();

        var max = concurrentResponses.stream()
                .map(Main::getFutureValue)
                .mapToLong(v -> v)
                .max();

        System.out.println("Min: " + min.getAsLong());
        System.out.println("Max: " + max.getAsLong());
    }

    private static Long getFutureValue(Future<Long> longFuture) {
        try {
            return longFuture.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
