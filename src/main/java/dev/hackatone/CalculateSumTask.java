package dev.hackatone;

import java.util.List;
import java.util.concurrent.Callable;

public class CalculateSumTask implements Callable<Integer> {

    private final List<Integer> nums;
    private String title;

    public CalculateSumTask(List<Integer> nums, String excTitle){
        this.nums=nums;
        this.title=excTitle;
    }


    @Override
    public Integer call() throws Exception {
        System.out.printf("Excercise title: %s\nThread title: %s\n",title,Thread.currentThread().getName());
        Thread.sleep(200);
        return nums.stream().mapToInt(Integer::intValue).sum();
    }
}
