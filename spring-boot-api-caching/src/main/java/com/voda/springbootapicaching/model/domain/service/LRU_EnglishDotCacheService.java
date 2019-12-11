package com.voda.springbootapicaching.model.domain.service;

import com.voda.springbootapicaching.model.domain.LRUCache.LRU_EnglishDotCache;
import com.voda.springbootapicaching.model.domain.LRUCache.LRU_KoreanDotCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LRU_EnglishDotCacheService {

    @Value("${LRU.cacheSize}")
    int cacheSize;

    LRU_EnglishDotCache lru_englishDotCache;
    LRU_EnglishDotCacheService(){
        lru_englishDotCache = LRU_EnglishDotCache.getInstance();
    }

    public String cacheHit(String key){
        if(lru_englishDotCache.find(key)){
            lru_englishDotCache.goBack(key);
            return lru_englishDotCache.get(key);
        }else{
            return null;
        }
    }

    public void add(String key, String val){
        final int size = lru_englishDotCache.size();
        if(size==cacheSize){
            lru_englishDotCache.deleteFirst();
        }
        lru_englishDotCache.add(key, val);
    }
}
