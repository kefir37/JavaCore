package dev.hackatone;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class DataProcessor {

    private final Map<String,Integer> resultMap;
    private final ExecutorService executorService;
    private final AtomicInteger counter;
    private final AtomicInteger activeThreads;

    public DataProcessor(int numOfThreads) {
        executorService = Executors.newFixedThreadPool(numOfThreads);
        resultMap=new HashMap<>();
        counter=new AtomicInteger(0);
        activeThreads= new AtomicInteger(0);
    }

    String ToCalculateSumTask(List<Integer> nums) throws Exception {
        String taskTitle = "task"+counter.incrementAndGet();
        CalculateSumTask calculateSumTask=new CalculateSumTask(nums,taskTitle);

        CompletableFuture<Integer> future=CompletableFuture.supplyAsync(()->{
            try {
                return calculateSumTask.call();
            } catch (Exception e){
                throw new RuntimeException(e);
            }
        }, executorService);

        activeThreads.incrementAndGet();
        future.thenAccept(result ->{
            synchronized (calculateSumTask){
                resultMap.put(taskTitle,result);
            }
            activeThreads.decrementAndGet();
        });
        return taskTitle;
    }

    Integer countActiveThreads(){
        return activeThreads.get();
    }

    Optional<Integer> getTask(String title){
        synchronized (resultMap){
            if(!resultMap.containsKey(title)){
                return Optional.empty();
            }
            return Optional.of(resultMap.get(title));
        }
    }

    public void shutdown() {
        executorService.shutdown();
        try {
            boolean isTerminated = executorService.awaitTermination(10, TimeUnit.MILLISECONDS);
            if (!isTerminated) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            throw new RuntimeException(e);
        }
    }
}
