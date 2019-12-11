package com.voda.springbootapicaching.model.domain.service;

import com.voda.springbootapicaching.model.domain.cache.ConcurrentHashMap_KoreanDotCache;
import org.springframework.stereotype.Service;

@Service
public class ConcurrentHashMap_KoreanDotCacheService {
    ConcurrentHashMap_KoreanDotCache concurrentHashMapKoreanDotCache;
    ConcurrentHashMap_KoreanDotCacheService(){
        concurrentHashMapKoreanDotCache = ConcurrentHashMap_KoreanDotCache.getInstance();
    }
    public String cacheHit(String key){
        String value = concurrentHashMapKoreanDotCache.search(key);
        if(value!=null){
            return value;
        }else{
            return null;
        }
    }
}
