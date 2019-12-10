package com.voda.springbootapicaching.model.domain.LRUCache;



import com.voda.springbootapicaching.util.MyLinkedList;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class LRUCache {
    @Value("${LRU.cacheSize}")
    int cacheSize;
    ConcurrentHashMap searchingMap;
    MyLinkedList dotCache;

    public LRUCache(){
        searchingMap = new ConcurrentHashMap<String, MyLinkedList.Node>();
        dotCache = new MyLinkedList();
    }

    public LRUCache(int cacheSize) {
        this.cacheSize = cacheSize;
        searchingMap = new ConcurrentHashMap<String, MyLinkedList.Node>();
        dotCache = new MyLinkedList();
    }

    @Synchronized
    public void deleteAll(){
        searchingMap.clear();
        dotCache.clear();
    }

    @Synchronized
    public void add(String key, String val){
        MyLinkedList.Node value = dotCache.addLast(val);
        searchingMap.put(key, value);
    }

    @Synchronized
    public void delete(String key){
        MyLinkedList.Node value = (MyLinkedList.Node) searchingMap.get(key);
        searchingMap.remove(key);
        dotCache.delete(value);
    }

    public boolean find(String key){
        return searchingMap.containsKey(key);
    }

    @Synchronized
    public String get(String key){
        return (String) ((MyLinkedList.Node)searchingMap.get(key)).getItem();
    }

    @Synchronized
    public void goBack(String key){
        MyLinkedList.Node node = (MyLinkedList.Node) searchingMap.get(key);
        dotCache.delete(node);
        dotCache.addLast(node);
    }
}
