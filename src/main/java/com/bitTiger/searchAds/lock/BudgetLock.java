package com.bitTiger.searchAds.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BudgetLock {
    public static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
}
