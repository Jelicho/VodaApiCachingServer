package com.voda.springbootapicaching.model.domain.service;

import com.voda.springbootapicaching.model.domain.cache.KoreanDotCache;
import org.springframework.stereotype.Service;

@Service
public class KoreanDotCacheService {
    KoreanDotCache koreanDotCache;
    KoreanDotCacheService(){
        koreanDotCache = KoreanDotCache.getInstance();
    }
    public String cacheHit(String key){
        String value = koreanDotCache.search(key);
        if(value!=null){
            return value;
        }else{
            return null;
        }
    }
}
