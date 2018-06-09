package com.manparvesh.lilcache;

import java.util.Optional;

/**
 * The interface LilCache, which is implemented in different ways.
 */
public interface LilCache {
    /**
     * Get size of cache
     *
     * @return size of cache
     */
    int getSize();

    /**
     * Put a value into cache with a specified key
     *
     * @param key   key
     * @param value value to be cached
     */
    void put(String key, Object value);

    /**
     * Get value from cache
     *
     * @param key key of object to get
     * @return value from cache
     */
    Optional<Object> get(String key);

    /**
     * Remove a specific entry from cache
     *
     * @param key key
     */
    void remove(String key);

    /**
     * Checks if a key exists in cache
     *
     * @param key key
     */
    boolean check(String key);

    /**
     * Clear cache
     */
    void clear();

    /**
     * Evict cache. Same as clear.
     */
    void evict();
}
