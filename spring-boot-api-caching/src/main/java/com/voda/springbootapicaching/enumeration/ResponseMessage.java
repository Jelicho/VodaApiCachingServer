package com.voda.springbootapicaching.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {
    //Common
    INTERNAL_SERVER_ERROR("서버 내부 에러"),
    BAD_REQUEST("올바르지 않은 요청"),
    //Cache
    CACHE_HIT("캐시에서 값 반환"),
    CACHE_NO_HIT("캐시에 값이 없음");

    private String message;


}
