package com.manparvesh.lilcache;

import com.manparvesh.lilcache.implementations.PerpetualLilCache;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class LilCacheTest {
    private static final String THREE = "three";
    private static final String ONE = "one";
    private static final String TWO = "two";
    private LilCache perpetualCache;

    @Before
    public void setUp() throws Exception {
        perpetualCache = new PerpetualLilCache();
    }

    private void testCache(LilCache cache) {
        cache.clear();
        assertEquals(0, cache.getSize());
        cache.put(ONE, 1);
        cache.put(THREE, 3);
        assertEquals(2, cache.getSize());
        assertFalse(cache.get(TWO).isPresent());
        assertTrue(cache.get(ONE).isPresent());
        cache.remove(THREE);
        assertEquals(1, cache.getSize());
        assertFalse(cache.get(THREE).isPresent());
        assertFalse(cache.check(THREE));
        cache.evict();
        assertEquals(0, cache.getSize());
    }

    @Test
    public void testPerpetualCache() {
        testCache(perpetualCache);
    }
}