package com.manparvesh.lilcache.implementations;

import com.manparvesh.lilcache.LilCache;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TransitoryLilCacheTest {

    private static final String THREE = "three";
    private static final String ONE = "one";
    private static final String TWO = "two";
    private final long TIMEOUT = TimeUnit.SECONDS.toMillis(5);
    private LilCache transitoryLilCache;

    @Before
    public void setUp() {
        transitoryLilCache = TransitoryLilCache
                .builder()
                .delegate(new PerpetualLilCache())
                .flushInterval(TIMEOUT)
                .lastFlushTime(System.nanoTime())
                .build();
    }

    @Test
    public void testExpirableCache() {
        transitoryLilCache.evict();
        assertEquals(0, transitoryLilCache.getSize());
        transitoryLilCache.put(ONE, 1);
        transitoryLilCache.put(THREE, 3);
        assertEquals(2, transitoryLilCache.getSize());
        assertFalse(transitoryLilCache.get(TWO).isPresent());
        assertTrue(transitoryLilCache.get(ONE).isPresent());
        transitoryLilCache.remove(THREE);
        assertEquals(1, transitoryLilCache.getSize());
        assertFalse(transitoryLilCache.get(THREE).isPresent());
        assertFalse(transitoryLilCache.check(THREE));

        // instead of clear, we will just wait for 5 seconds for the cache to clear
        try {
            Thread.sleep(TIMEOUT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(0, transitoryLilCache.getSize());
    }
}