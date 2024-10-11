package dev.hackatone.implementations;

import dev.hackatone.SiteVisitCounter;

public class SynchronizedBlockCounter implements SiteVisitCounter {

    Integer count;

    public SynchronizedBlockCounter(){
        count=0;
    }

    @Override
    public synchronized void incrementVisitCount() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        count++;
    }

    @Override
    public int getVisitCount() {
        return count;
    }
}
