package com.manparvesh.lilcache.implementations;

import com.manparvesh.lilcache.LilCache;
import org.junit.Test;

import static org.junit.Assert.*;

public class FIFOLilCacheTest {

    @Test
    public void getOne() {
        LilCache lilCache = new FIFOLilCache();
        assertEquals(1, lilCache.getOne());
    }
}