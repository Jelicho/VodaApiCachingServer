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
        MyLinkedList.Node value = dotCache.addLast(key, val);
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
        return (String) ((MyLinkedList.Node)searchingMap.get(key)).getValue();
    }

    @Synchronized
    public void goBack(String key){
        MyLinkedList.Node node = (MyLinkedList.Node) searchingMap.get(key);
        dotCache.delete(node);
        dotCache.addLast(node);
    }

    public int size(){return dotCache.size();}

    @Synchronized
    public void deleteFirst(){
        MyLinkedList.Node first = dotCache.getFirst();
        final String delete_key = (String) first.getKey();
        dotCache.delete(first);
        searchingMap.remove(delete_key);
    }
}
