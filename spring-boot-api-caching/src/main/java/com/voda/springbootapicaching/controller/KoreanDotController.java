package com.voda.springbootapicaching.controller;

import com.voda.springbootapicaching.model.common.APIRes;
import com.voda.springbootapicaching.model.domain.service.ConcurrentHashMap_KoreanDotCacheService;
import com.voda.springbootapicaching.enumeration.ResponseMessage;
import com.voda.springbootapicaching.enumeration.StatusCode;
import com.voda.springbootapicaching.model.common.DefaultRes;
import com.voda.springbootapicaching.model.domain.service.LRU_KoreanDotCacheService;
import com.voda.springbootapicaching.model.dto.DotDto;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.StringTokenizer;

@RequestMapping("/korean")
@CrossOrigin(origins = "*")
@Controller
public class KoreanDotController extends  AbstractContoller {
    private final LRU_KoreanDotCacheService lru_koreanDotCacheService;
    public KoreanDotController(RestTemplate restTemplate, LRU_KoreanDotCacheService lru_koreanDotCacheService) {
        super(restTemplate);
        this.lru_koreanDotCacheService = lru_koreanDotCacheService;
    }
    @GetMapping
    public ResponseEntity mirrorRest(@RequestParam String letter, HttpMethod method, HttpServletRequest request) {
        String key = request.getQueryString();
        String cacheValue = lru_koreanDotCacheService.cacheHit(key);
        if (cacheValue != null) {
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CACHE_HIT, new DotDto(cacheValue)), HttpStatus.OK);
        }
        System.out.println("not cache hit 수신 성공");
        ResponseEntity<APIRes> responseEntity = sendRequestToApiServer(letter, method, request);
        if(responseEntity.getStatusCodeValue()==200){
//            System.out.println(responseEntity.getBody().getData().getDotValue());
            String value = responseEntity.getBody().getData().getDotValue();
            lru_koreanDotCacheService.add(key, value);
        }
        return responseEntity;
    }
//    private final ConcurrentHashMap_KoreanDotCacheService concurrentHashMapKoreanDotCacheService;
//    public KoreanDotController(RestTemplate restTemplate, ConcurrentHashMap_KoreanDotCacheService concurrentHashMapKoreanDotCacheService) {
//        super(restTemplate);
//        this.concurrentHashMapKoreanDotCacheService = concurrentHashMapKoreanDotCacheService;
//    }
//    @GetMapping
//    public ResponseEntity mirrorRest(@RequestParam String letter, HttpMethod method, HttpServletRequest request) {
//        String key = request.getQueryString();
//        String cacheValue = concurrentHashMapKoreanDotCacheService.cacheHit(key);
//        if (cacheValue != null) {
//            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CACHE_HIT, new DotDto(cacheValue)), HttpStatus.OK);
//        }
//        System.out.println("not cache hit 수신 성공");
//        ResponseEntity<APIRes> responseEntity = sendRequestToApiServer(letter, method, request);
//        if(responseEntity.getStatusCodeValue()==200){
//            System.out.println(responseEntity.getBody().getData().getDotValue());
//        }
//        return responseEntity;
//    }
}