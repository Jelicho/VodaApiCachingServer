package com.voda.springbootapicaching.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class CachePool
{
    private ConcurrentHashMap<String, String> koreanCache;
    private ConcurrentHashMap<String, String> englishCache;
}
