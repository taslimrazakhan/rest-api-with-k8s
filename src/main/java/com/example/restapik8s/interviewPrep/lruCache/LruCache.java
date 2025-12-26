package com.example.restapik8s.interviewPrep.lruCache;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LruCache<K,V> extends LinkedHashMap<K,V> {

    private int capacity;
    final List<String> items;

    public LruCache(int capacity){
        super(capacity,0.75f, true);
        items = List.of("apple", "banana", "apple", "orange", "banana", "kiwi");
         this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return this.size() > capacity;
    }

    //main method
    public static void main(String[] args) {

        LruCache<Integer, String> lruCache = new LruCache<>(3);
        
        System.out.println("=== LRU Cache Demo (Capacity: 3) ===");
        
        // Add 3 items
        lruCache.put(1, "One");
        lruCache.put(2, "Two");
        lruCache.put(3, "Three");
        System.out.println("After adding 1,2,3: " + lruCache);
        
        // Add 4th item - should remove eldest (1)
        lruCache.put(4, "Four");
        System.out.println("After adding 4: " + lruCache + " (1 was removed)");
        
        // Access item 2 (makes it recently used)
        lruCache.get(2);
        System.out.println("After accessing 2: " + lruCache + " (2 moved to end)");
        
        // Add 5th item - should remove eldest (3)
        lruCache.put(5, "Five");
        System.out.println("After adding 5: " + lruCache + " (3 was removed)");
        
    }
}
