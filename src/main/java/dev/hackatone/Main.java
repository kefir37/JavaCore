package dev.hackatone;


import dev.hackatone.implementations.*;

import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<SiteVisitCounter> siteVisitCounters=List.of(
                new AtomicIntegerCounter(),
                new ReentrantLockCounter(),
                new SynchronizedBlockCounter(),
                new UnsynchronizedCounter(),
                new VolatileCounter()
        );
        MultithreadingSiteVisitor siteVisitor=new MultithreadingSiteVisitor();
        for(SiteVisitCounter counter: siteVisitCounters){
            siteVisitor.setSiteVisitCounter(counter);
            siteVisitor.visitMultithread(100);
            siteVisitor.waitUntilAllVisited();
            long time= siteVisitor.getTotalTimeOfHandling()/1000;
            int visitCount= siteVisitor.getVisitCounter();
            System.out.printf("Class: %s\nTotal time: %d\nTotal visits: %d\n",
                    counter.getClass(), time, visitCount);
        }
    }
}