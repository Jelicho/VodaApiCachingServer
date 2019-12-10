package com.voda.springbootapicaching.model.domain.service;

import com.voda.springbootapicaching.model.domain.cache.ConcurrentHashMap_EnglishDotCache;
import org.springframework.stereotype.Service;

@Service
public class EnglishDotCacheService {
    ConcurrentHashMap_EnglishDotCache concurrentHashMapEnglishDotCache;
    EnglishDotCacheService(){
        concurrentHashMapEnglishDotCache = concurrentHashMapEnglishDotCache.getInstance();
    }
    public String cacheHit(String key){
        String value = concurrentHashMapEnglishDotCache.search(key);
        if(value!=null){
            return value;
        }else{
            return null;
        }
    }
}
