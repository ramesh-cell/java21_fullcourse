package test.java21_fullcourse;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.locks.LockSupport;
import java.util.stream.IntStream;

public class VirtualThreadsAndConcurrencyDemo {

    // Simulate a task (e.g., calling a microservice)
    private static String fetchData(String name, int delaySec) {
        LockSupport.parkNanos(delaySec);
        return name + "_result";
    }

    // Demonstrates Virtual Threads
    public static void runVirtualThreadExample() {

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 1000_000).forEach(i ->

                    executor.submit(() -> {
                        LockSupport.parkNanos(1000_00);
                        System.out.println("Task " + i + " done on: " + Thread.currentThread());
                    })

            );
        }
    }

    // Demonstrates Structured Concurrency
    public static void runStructuredConcurrencyExample() {

        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {

            var userTask = scope.fork(() -> fetchData("user", 2));
            var ordersTask = scope.fork(() -> fetchData("orders", 3));
            var statsTask = scope.fork(() -> fetchData("stats", 1));

            scope.join();              // Wait for all
            scope.throwIfFailed();    // Or throw first exception

            System.out.println("User Result: " + userTask.get());
            System.out.println("Orders Result: " + ordersTask.get());
            System.out.println("Stats Result: " + statsTask.get());

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            System.err.println("One or more tasks failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Running Virtual Threads ---");

        runVirtualThreadExample();

        System.out.println("--- Running Structured Concurrency ---");

        runStructuredConcurrencyExample();
    }
} 
