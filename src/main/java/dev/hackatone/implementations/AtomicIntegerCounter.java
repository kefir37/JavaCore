package dev.hackatone.implementations;

import dev.hackatone.SiteVisitCounter;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerCounter implements SiteVisitCounter {

    AtomicInteger count;

    public AtomicIntegerCounter(){
        count= new AtomicInteger(0);
    }

    @Override
    public void incrementVisitCount() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        count.getAndIncrement();
    }

    @Override
    public int getVisitCount() {
        return count.get();
    }
}