package com.voda.springbootapicaching.util;

import com.voda.springbootapicaching.model.domain.cache.EnglishDotCache;
import com.voda.springbootapicaching.model.domain.cache.KoreanDotCache;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    private final EnglishDotCache englishDotCache;
    private final KoreanDotCache koreanDotCache;

    public Scheduler(EnglishDotCache englishDotCache, KoreanDotCache koreanDotCache) {
        this.englishDotCache = englishDotCache;
        this.koreanDotCache = koreanDotCache;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void cacheFresh() {
        System.out.println("cacheFresh Scheduler 동작 사작");
        try {
            englishDotCache.deleteAll();
            koreanDotCache.deleteAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("cacheFresh Scheduler 동작 종료");
        }
    }
}