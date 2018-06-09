package com.manparvesh.lilcache.implementations;

import com.manparvesh.lilcache.LilCache;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class LRULilCacheTest {
    private LilCache lruLilCache1;
    private LilCache lruLilCache2;
    private LilCache lruLilCache3;
    private LilCache lruLilCache4;

    @Before
    public void setUp() {
        lruLilCache1 = new LRULilCache();
        lruLilCache2 = new LRULilCache(new PerpetualLilCache());
        lruLilCache3 = new LRULilCache(20);
        lruLilCache4 = new LRULilCache(new PerpetualLilCache(), 20);
    }

    @Test
    public void testLRULilCache() {
        testUtil(lruLilCache1);
        testUtil(lruLilCache2);
        testUtil(lruLilCache3);
        testUtil(lruLilCache4);
    }

    private void testUtil(LilCache cache){
        for (int i = 0; i < 100; i++) {
            cache.put(Integer.toString(i), i);
        }
        assertEquals(20, cache.getSize());

        cache.remove(Integer.toString(90));
        assertEquals(19, cache.getSize());
        assertFalse(cache.check(Integer.toString(90)));

        for (int i = 0; i < 20; i++) {
            cache.put(Integer.toString(i), i);
        }

        Random random = new Random();
        int indices[] = new int[20];
        for (int i = 0; i < 20; i++) {
            indices[i] = random.nextInt(20);
        }

        for (int index : indices) {
            cache.get(Integer.toString(index));
        }

        for (int index : indices) {
            assertEquals(index, cache.get(Integer.toString(index)).get());
        }

        cache.evict();
        assertEquals(0, cache.getSize());
    }
}