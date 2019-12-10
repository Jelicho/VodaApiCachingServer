package com.voda.springbootapicaching.model.domain.LRUCache;

import org.springframework.stereotype.Component;

@Component
public class LRU_EnglishDotCache extends LRUCache{
    private LRU_EnglishDotCache() {
        super();
    }

    private static class LazyHolder {
        public static final LRU_EnglishDotCache INSTANCE = new LRU_EnglishDotCache();
    }

    public static LRU_EnglishDotCache getInstance() {
        return LRU_EnglishDotCache.LazyHolder.INSTANCE;
    }
}
