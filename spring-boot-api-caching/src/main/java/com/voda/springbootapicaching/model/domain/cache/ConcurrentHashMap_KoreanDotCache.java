package com.voda.springbootapicaching.model.domain.cache;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class ConcurrentHashMap_KoreanDotCache implements Cache{
    ConcurrentHashMap<String, String> koreanHashMap;

    private ConcurrentHashMap_KoreanDotCache() {
        koreanHashMap = new ConcurrentHashMap<>();
    }

    private static class LazyHolder {
        public static final ConcurrentHashMap_KoreanDotCache INSTANCE = new ConcurrentHashMap_KoreanDotCache();
    }

    public static ConcurrentHashMap_KoreanDotCache getInstance() {
        return LazyHolder.INSTANCE;
    }


    @Override
    public void add(String key, String value) {
        koreanHashMap.put(key, value);
    }

    @Override
    public void deleteAll() {
        koreanHashMap.clear();
    }

    @Override
    public String search(String key) {
        if(koreanHashMap.containsKey(key)){
            return koreanHashMap.get(key);
        }else{
            return null;
        }
    }
}
