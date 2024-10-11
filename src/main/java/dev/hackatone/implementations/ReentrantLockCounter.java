package dev.hackatone.implementations;

import dev.hackatone.SiteVisitCounter;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter implements SiteVisitCounter {

    ReentrantLock reentrantLock=new ReentrantLock();

    int count;

    public ReentrantLockCounter(){
        count=0;
    }

    @Override
    public synchronized void incrementVisitCount()  {
        reentrantLock.lock();
        try {
            Thread.sleep(100);
            count++;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        reentrantLock.unlock();
    }

    @Override
    public int getVisitCount() {
        return count;
    }
}