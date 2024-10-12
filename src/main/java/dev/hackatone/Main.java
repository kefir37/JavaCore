package dev.hackatone;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws Exception {
        DataProcessor dataProcessor=new DataProcessor(10);

        List<String> taskTitles =new ArrayList<>();
        for(int i=0;i<100;++i){
            List<Integer> nums=generateRandomNums(100);
            String title= dataProcessor.ToCalculateSumTask(nums);
            taskTitles.add(title);
        }

        while (dataProcessor.countActiveThreads()>0){
            System.out.println("Temp number of tasks: "+dataProcessor.countActiveThreads());
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        taskTitles.forEach(
                title-> {
                    Optional<Integer> result =dataProcessor.getTask(title);
                    result.ifPresent(value-> System.out.println("Task result "+title+": "+value));
                }
        );

        dataProcessor.shutdown();
    }

    private static List<Integer> generateRandomNums(int i) {
        List<Integer> nums=new ArrayList<>();
        for(int j=0;j<i;++j){
            nums.add((int) (Math.random() * 100));
        }
        return nums;
    }
}