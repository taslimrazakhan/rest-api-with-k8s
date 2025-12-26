package com.example.restapik8s.interviewPrep;

import java.util.concurrent.ConcurrentHashMap;

public class TTLCache<K, V> {
    private final long ttlInMillis;
    private final ConcurrentHashMap<K, Entry<V>> map = new ConcurrentHashMap<>();

    public void put(K key, V value) {
        map.put(key, new Entry<V>(value, System.currentTimeMillis() + ttlInMillis));
    }

    public V get(K key){
        if(map.get(key)==null)
            return null;
        Entry<V> entry = map.get(key);
        if(System.currentTimeMillis() > entry.expiry){
            map.remove(key);
            return null;
        }
        return entry.value;
    }

    public TTLCache(long ttlInMillis) {
        this.ttlInMillis = ttlInMillis;
    }

    static class Entry<V> {
        V value;
        long expiry;

        Entry(V value, long expiry) {
            this.value = value;
            this.expiry = expiry;
        }
    }
}
