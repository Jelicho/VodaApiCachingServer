package com.voda.springbootapicaching.model.domain.cache;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class EnglishDotCache implements Cache{
    ConcurrentHashMap<String, String> englishDotCache;

    private EnglishDotCache() {
        englishDotCache = new ConcurrentHashMap<>();
    }

    private static class LazyHolder {
        public static final EnglishDotCache INSTANCE = new EnglishDotCache();
    }

    public static EnglishDotCache getInstance() {
        return LazyHolder.INSTANCE;
    }


    @Override
    public void add(String key, String value) {
        englishDotCache.put(key, value);
    }

    @Override
    public void deleteAll() {
        englishDotCache.clear();
    }

    @Override
    public String search(String key) {
        if(englishDotCache.containsKey(key)){
            return englishDotCache.get(key);
        }else{
            return null;
        }
    }
}
