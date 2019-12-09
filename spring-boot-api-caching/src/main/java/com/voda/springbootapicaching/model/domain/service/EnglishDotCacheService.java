package com.voda.springbootapicaching.model.domain.service;

import com.voda.springbootapicaching.model.domain.cache.EnglishDotCache;
import org.springframework.stereotype.Service;

@Service
public class EnglishDotCacheService {
    EnglishDotCache englishDotCache;
    EnglishDotCacheService(){
        englishDotCache = englishDotCache.getInstance();
    }
    public String cacheHit(String key){
        String value = englishDotCache.search(key);
        if(value!=null){
            return value;
        }else{
            return null;
        }
    }
}
