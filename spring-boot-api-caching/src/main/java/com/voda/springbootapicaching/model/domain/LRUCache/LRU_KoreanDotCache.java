package com.voda.springbootapicaching.model.domain.LRUCache;

import org.springframework.stereotype.Component;

@Component
public class LRU_KoreanDotCache extends LRUCache{
    private LRU_KoreanDotCache() { super(); }

    private static class LazyHolder {
        public static final LRU_KoreanDotCache INSTANCE = new LRU_KoreanDotCache();
    }

    public static LRU_KoreanDotCache getInstance() {
        return LRU_KoreanDotCache.LazyHolder.INSTANCE;
    }
}
