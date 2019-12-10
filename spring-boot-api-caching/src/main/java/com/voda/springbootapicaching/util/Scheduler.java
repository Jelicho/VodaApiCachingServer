package com.voda.springbootapicaching.util;

import com.voda.springbootapicaching.model.domain.cache.ConcurrentHashMap_EnglishDotCache;
import com.voda.springbootapicaching.model.domain.cache.ConcurrentHashMap_KoreanDotCache;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    private final ConcurrentHashMap_EnglishDotCache concurrentHashMapEnglishDotCache;
    private final ConcurrentHashMap_KoreanDotCache concurrentHashMapKoreanDotCache;

    public Scheduler(ConcurrentHashMap_EnglishDotCache concurrentHashMapEnglishDotCache, ConcurrentHashMap_KoreanDotCache concurrentHashMapKoreanDotCache) {
        this.concurrentHashMapEnglishDotCache = concurrentHashMapEnglishDotCache;
        this.concurrentHashMapKoreanDotCache = concurrentHashMapKoreanDotCache;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void cacheFresh() {
        System.out.println("cacheFresh Scheduler 동작 사작");
        try {
            concurrentHashMapEnglishDotCache.deleteAll();
            concurrentHashMapKoreanDotCache.deleteAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("cacheFresh Scheduler 동작 종료");
        }
    }
}