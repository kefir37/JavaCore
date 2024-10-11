package dev.hackatone;

import java.util.ArrayList;
import java.util.List;

public class MultithreadingSiteVisitor {

    private SiteVisitCounter siteVisitCounter;
    private final List<Thread> threads;
    private long startTime;
    private long endTime;

    public MultithreadingSiteVisitor(){
        threads=new ArrayList<>();
    }

    void visitMultithread(int numOfThreads){
        startTime=System.currentTimeMillis();
        for(int i=0;i<numOfThreads;++i){
            Thread thread=new Thread(siteVisitCounter::incrementVisitCount);
            threads.add(thread);
            thread.start();
        }
    }

    void waitUntilAllVisited() throws InterruptedException {
        for(Thread thread:threads){
            thread.join();
        }
        endTime=System.currentTimeMillis();
    }

    long getTotalTimeOfHandling(){
        return endTime-startTime;
    }

    void setSiteVisitCounter(SiteVisitCounter siteVisitCounter){
        this.siteVisitCounter=siteVisitCounter;
    }

    int getVisitCounter(){
        return siteVisitCounter.getVisitCount();
    }
}
