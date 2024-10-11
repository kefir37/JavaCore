package dev.hackatone.implementations;

import dev.hackatone.SiteVisitCounter;

public class UnsynchronizedCounter implements SiteVisitCounter {

    int count;

    public UnsynchronizedCounter(){
        count=0;
    }

    @Override
    public void incrementVisitCount() {
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
