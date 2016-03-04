package com.test.java.thread.concurrency;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 模仿CopyOnWriteArrayList
 *
 * Created by mrf on 2016/3/4.
 */
public class CopyOnWriteMap<K,V>  implements Map<K,V>,Cloneable {

    private volatile Map<K,V> internalMap;
    public CopyOnWriteMap(){
        internalMap = new HashMap<>();
    }

    public CopyOnWriteMap(int i) {
        internalMap = new HashMap<>(i);
    }

    @Override
    public V put(K key, V value) {
        synchronized(this){
            Map<K,V> newMap = new HashMap<>(internalMap);
            V val = newMap.put(key,value);
            internalMap = newMap;
            return val;
        }
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        synchronized(this){
            Map<K,V> newMap = new HashMap<>(internalMap);
            newMap.putAll(m);
            internalMap = newMap;
        }
    }

    @Override
    public V get(Object key) {
        return internalMap.get(key);
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }


    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}

class BlackListServiceImpl{
    private static CopyOnWriteMap<String,Object> blackListMap = new CopyOnWriteMap<>(1000);

    public static boolean isBlackList(String id){
        return blackListMap.get(id) == null?false:true;
    }
    public static void addBlackList(String id){
        blackListMap.put(id,Boolean.TRUE);
    }
    /**
     * 批量添加黑名单
     */
    public static void addBlackList(Map<String,Object> ids){
        blackListMap.putAll(ids);
    }
}
