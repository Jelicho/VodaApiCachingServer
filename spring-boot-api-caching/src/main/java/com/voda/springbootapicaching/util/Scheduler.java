package com.voda.springbootapicaching.util;

import com.voda.springbootapicaching.model.domain.LRUCache.LRU_EnglishDotCache;
import com.voda.springbootapicaching.model.domain.LRUCache.LRU_KoreanDotCache;
import com.voda.springbootapicaching.model.domain.cache.ConcurrentHashMap_EnglishDotCache;
import com.voda.springbootapicaching.model.domain.cache.ConcurrentHashMap_KoreanDotCache;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {


    private final LRU_KoreanDotCache lru_koreanDotCache;
    private final LRU_EnglishDotCache lru_englishDotCache;
    public Scheduler(LRU_KoreanDotCache lru_koreanDotCache, LRU_EnglishDotCache lru_englishDotCache) {
        this.lru_koreanDotCache = lru_koreanDotCache;
        this.lru_englishDotCache = lru_englishDotCache;
    }
    @Scheduled(cron = "0 0 * * * *")
    public void LRU_cacheFresh() {
        System.out.println("LRU_cacheFresh Scheduler 동작 사작");
        try {
            lru_koreanDotCache.deleteAll();
            lru_englishDotCache.deleteAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("LRU_cacheFresh Scheduler 동작 종료");
        }
    }
//    private final ConcurrentHashMap_EnglishDotCache concurrentHashMapEnglishDotCache;
//    private final ConcurrentHashMap_KoreanDotCache concurrentHashMapKoreanDotCache;
//
//    public Scheduler(ConcurrentHashMap_EnglishDotCache concurrentHashMapEnglishDotCache, ConcurrentHashMap_KoreanDotCache concurrentHashMapKoreanDotCache) {
//        this.concurrentHashMapEnglishDotCache = concurrentHashMapEnglishDotCache;
//        this.concurrentHashMapKoreanDotCache = concurrentHashMapKoreanDotCache;
//    }
//    @Scheduled(cron = "0 0 * * * *")
//    public void cacheFresh() {
//        System.out.println("cacheFresh Scheduler 동작 사작");
//        try {
//            concurrentHashMapEnglishDotCache.deleteAll();
//            concurrentHashMapKoreanDotCache.deleteAll();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            System.out.println("cacheFresh Scheduler 동작 종료");
//        }
//    }
}