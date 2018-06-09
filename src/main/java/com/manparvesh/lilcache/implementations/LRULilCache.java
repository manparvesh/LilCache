package com.manparvesh.lilcache.implementations;

import com.manparvesh.lilcache.LilCache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class LRULilCache implements LilCache {

    /**************************************************************
     *                      private variables
     * ************************************************************/
    private static final int DEFAULT_CACHE_SIZE = 20;

    private String eldestElementKey = null;
    private int cacheSize;

    private LilCache delegate;

    private Map<String, Object> keyMap = new LinkedHashMap<String, Object>(cacheSize, 0.75f, true) {
        @Override protected boolean removeEldestEntry(Map.Entry<String, Object> eldest) {
            boolean cacheOverflow = size() > cacheSize;
            if (cacheOverflow) {
                eldestElementKey = eldest.getKey();
            }
            return cacheOverflow;
        }
    };

    /**************************************************************
     *                      constructors
     * ************************************************************/

    LRULilCache() {
        cacheSize = DEFAULT_CACHE_SIZE;
        delegate = new PerpetualLilCache();
    }

    LRULilCache(LilCache delegate) {
        this.delegate = delegate;
        this.cacheSize = DEFAULT_CACHE_SIZE;
    }

    LRULilCache(int cacheSize) {
        this.delegate = new PerpetualLilCache();
        this.cacheSize = cacheSize;
    }

    LRULilCache(LilCache delegate, int cacheSize) {
        this.delegate = delegate;
        this.cacheSize = cacheSize;
    }

    /**************************************************************
     *                      implementations
     * ************************************************************/

    /**
     * Get size of cache
     *
     * @return size of cache
     */
    @Override public int getSize() {
        return delegate.getSize();
    }

    /**
     * Put a value into cache with a specified key
     *
     * @param key   key
     * @param value value to be cached
     */
    @Override public void put(String key, Object value) {
        delegate.put(key, value);
        updateKeyMap(key);
    }

    /**
     * Updates the position of key in keyMap
     * and removes eldestElement
     *
     * @param key key
     */
    private void updateKeyMap(String key) {
        keyMap.put(key, delegate.get(key));
        if (eldestElementKey != null) {
            delegate.remove(eldestElementKey);
        }
        eldestElementKey = null;
    }

    /**
     * Get value from cache
     *
     * @param key key of object to get
     * @return value from cache
     */
    @Override public Optional<Object> get(String key) {
        keyMap.get(key); // this is done so that this key is made more accessible by the LinkedHashMap
        return delegate.get(key);
    }

    /**
     * Remove a specific entry from cache
     *
     * @param key key
     */
    @Override public void remove(String key) {
        delegate.remove(key);
    }

    /**
     * Checks if a key exists in cache
     *
     * @param key key
     */
    @Override public boolean check(String key) {
        return delegate.check(key);
    }

    /**
     * Clear cache
     */
    @Override public void clear() {
        delegate.clear();
        keyMap.clear();
    }

    /**
     * Evict cache. Same as clear.
     */
    @Override public void evict() {
        clear();
    }
}
