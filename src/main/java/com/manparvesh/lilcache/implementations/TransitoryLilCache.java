package com.manparvesh.lilcache.implementations;

import com.manparvesh.lilcache.LilCache;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Builder
@AllArgsConstructor
public class TransitoryLilCache implements LilCache {
    /**************************************************************
     *                      private variables
     * ************************************************************/

    long flushInterval = TimeUnit.MINUTES.toMillis(1);
    LilCache delegate = new PerpetualLilCache();

    private long lastFlushTime = System.nanoTime();

    /**************************************************************
     *                      implementations
     * ************************************************************/

    /**
     * Get size of cache
     *
     * @return size of cache
     */
    @Override public int getSize() {
        updateCacheAccordingToTimePassed();
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
    }

    /**
     * Get value from cache
     *
     * @param key key of object to get
     * @return value from cache
     */
    @Override public Optional<Object> get(String key) {
        updateCacheAccordingToTimePassed();
        return delegate.get(key);
    }

    private void updateCacheAccordingToTimePassed() {
        boolean shouldUpdate = (System.nanoTime() - lastFlushTime) >= TimeUnit.MILLISECONDS.toNanos(flushInterval);
        if (shouldUpdate) {
            delegate.clear();
        }
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
        updateCacheAccordingToTimePassed();
        return delegate.check(key);
    }

    /**
     * Clear cache
     */
    @Override public void clear() {
        delegate.clear();
    }

    /**
     * Evict cache. Same as clear.
     */
    @Override public void evict() {
        clear();
    }
}
