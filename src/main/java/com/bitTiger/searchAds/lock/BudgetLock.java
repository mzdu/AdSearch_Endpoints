package com.bitTiger.searchAds.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BudgetLock {
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static void acquireReadLock() {
        lock.readLock().lock();
    }

    public static void releaseReadLock() {
        lock.readLock().unlock();
    }

    public static void acquireWriteLock() {
        lock.writeLock().lock();
    }

    public static void releaseWriteLock() {
        lock.writeLock().unlock();
    }
}
