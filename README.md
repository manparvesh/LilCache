<div align="center">
  <h1>LilCache</h1>

  <p>Lightweight cache framework written in Java for education purposes</p>
  <a href="https://travis-ci.org/manparvesh/LilCache/builds" target="_blank"><img src="https://travis-ci.org/manparvesh/LilCache.svg?branch=master" alt="Build Status"></a> 
  <a href="https://manparvesh.mit-license.org/" target="_blank"><img src="https://img.shields.io/badge/license-MIT-blue.svg" alt="License"></a> 
  <a href="https://codecov.io/gh/manparvesh/LilCache" target="_blank"><img src="https://img.shields.io/codecov/c/github/manparvesh/LilCache/master.svg" alt="Coverage"></a>
</div>

## The API
The following methods are provided in this framework:

```java
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
```

## Implementations

- PerpetualLilCache:
  - This simply uses Java's HashMap to store values.
- LRULilCache
  - This implementation is based on the Least Recently Used principle
  - It uses the `PerpetualLilCache` implementation through the decorator design pattern.
- TransitoryLilCache
  - This implementation is based for the cases when you need to set an expiry time for the entries in the cache.

## Note
This is a simple implementation of different techniques used in caching frameworks, mainly to understand how it all works.

## LICENSE

```
MIT License

Copyright (c) 2018 Man Parvesh Singh Randhawa

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
