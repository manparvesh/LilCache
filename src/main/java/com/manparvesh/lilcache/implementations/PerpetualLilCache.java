package com.manparvesh.lilcache.implementations;

import com.manparvesh.lilcache.LilCache;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * This cache assumes that the values will be stored in the cache forever,
 * and will never be deleted.
 * <p>
 * This is a naive implementation of a cache using java's HashMap.
 */
public class PerpetualLilCache implements LilCache {

    private Map<String, Object> cache;

    public PerpetualLilCache() {
        cache = new HashMap<>();
    }

    /**
     * Get size of cache
     *
     * @return size of cache
     */
    @Override public int getSize() {
        return cache.size();
    }

    /**
     * Put a value into cache with a specified key
     *
     * @param key   key
     * @param value value to be cached
     */
    @Override public void put(String key, Object value) {
        cache.put(key, value);
    }

    /**
     * Get value from cache
     *
     * @param key key of object to get
     * @return value from cache
     */
    @Override public Optional<Object> get(String key) {
        return Optional.ofNullable(cache.get(key));
    }

    /**
     * Remove a specific entry from cache
     *
     * @param key key
     */
    @Override public void remove(String key) {
        cache.remove(key);
    }

    /**
     * Checks if a key exists in cache
     *
     * @param key key
     */
    @Override public boolean check(String key) {
        return cache.containsKey(key);
    }

    /**
     * Clear cache
     */
    @Override public void clear() {
        cache.clear();
    }

    /**
     * Evict cache. Same as clear.
     */
    @Override public void evict() {
        clear();
    }
}
