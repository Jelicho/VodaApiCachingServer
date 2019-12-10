package com.voda.springbootapicaching.model.domain.LRUCache;

import com.voda.springbootapicaching.model.domain.cache.ConcurrentHashMap_EnglishDotCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class LRU_KoreanDotCache extends LRUCache{
    private LRU_KoreanDotCache() {
        super();
    }

    private static class LazyHolder {
        public static final LRU_KoreanDotCache INSTANCE = new LRU_KoreanDotCache();
    }

    public static LRU_KoreanDotCache getInstance() {
        return LRU_KoreanDotCache.LazyHolder.INSTANCE;
    }
}
