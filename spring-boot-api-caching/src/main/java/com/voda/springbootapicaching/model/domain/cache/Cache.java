package com.voda.springbootapicaching.model.domain.cache;

public interface Cache {
    void add(String key, String value);
    void deleteAll();
    String search(String key);
}
