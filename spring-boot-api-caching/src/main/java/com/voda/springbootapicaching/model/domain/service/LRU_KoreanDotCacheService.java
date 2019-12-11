package com.voda.springbootapicaching.model.domain.service;

import com.voda.springbootapicaching.model.domain.LRUCache.LRU_KoreanDotCache;
import com.voda.springbootapicaching.model.domain.cache.ConcurrentHashMap_KoreanDotCache;
import com.voda.springbootapicaching.util.MyLinkedList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LRU_KoreanDotCacheService {

    @Value("${LRU.cacheSize}")
    int cacheSize;

    LRU_KoreanDotCache lru_koreanDotCache;
    LRU_KoreanDotCacheService(){
        lru_koreanDotCache = LRU_KoreanDotCache.getInstance();
    }

    public String cacheHit(String key){
        if(lru_koreanDotCache.find(key)){
            lru_koreanDotCache.goBack(key);
            return lru_koreanDotCache.get(key);
        }else{
            return null;
        }
    }

    public void add(String key, String val){
        final int size = lru_koreanDotCache.size();
        if(size==cacheSize){
            lru_koreanDotCache.deleteFirst();
        }
        lru_koreanDotCache.add(key, val);
    }
}
