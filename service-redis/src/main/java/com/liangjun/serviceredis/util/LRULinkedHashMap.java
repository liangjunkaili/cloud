package com.liangjun.serviceredis.util;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 用LinkedHashMap实现LRU缓存算法
 * @param <K>
 * @param <V>
 */
public class LRULinkedHashMap<K,V> extends LinkedHashMap<K,V> {
    private int capacity;
    LRULinkedHashMap(int capacity){
        super(16,0.75f,true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        System.out.println(eldest.getKey()+"=="+eldest.getValue());
        return size()>capacity;
    }

    public static void main(String[] args) {
        Map<Integer,Integer> map = new LRULinkedHashMap<>(4);
        map.put(9,3);
        map.put(7,4);
        map.put(5,9);
        map.get(9);
        map.put(3,4);
        map.put(6,6);
        for(Iterator<Map.Entry<Integer,Integer>> it = map.entrySet().iterator(); it.hasNext();){
            System.out.println(it.next().getKey());
        }

    }
}
