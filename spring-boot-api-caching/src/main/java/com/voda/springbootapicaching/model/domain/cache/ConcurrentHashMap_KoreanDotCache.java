package com.voda.springbootapicaching.model.domain.cache;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class KoreanDotCache implements Cache{
    ConcurrentHashMap<String, String> koreanHashMap;

    private KoreanDotCache() {
        koreanHashMap = new ConcurrentHashMap<>();
    }

    private static class LazyHolder {
        public static final KoreanDotCache INSTANCE = new KoreanDotCache();
    }

    public static KoreanDotCache getInstance() {
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
