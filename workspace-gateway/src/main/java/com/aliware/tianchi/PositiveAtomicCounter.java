package com.aliware.tianchi;

import java.util.concurrent.atomic.AtomicInteger;

public class PositiveAtomicCounter {
    private final AtomicInteger atom = new AtomicInteger(0);
    private static final int mask = 2147483647;

    public final int incrementAndGet() {
        int rt = this.atom.incrementAndGet();
        return rt & mask;
    }
}

