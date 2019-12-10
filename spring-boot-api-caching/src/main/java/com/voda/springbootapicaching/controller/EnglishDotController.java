package com.voda.springbootapicaching.controller;

import com.voda.springbootapicaching.enumeration.ResponseMessage;
import com.voda.springbootapicaching.enumeration.StatusCode;
import com.voda.springbootapicaching.model.common.DefaultRes;
import com.voda.springbootapicaching.model.domain.service.ConcurrentHashMap_EnglishDotCacheService;
import com.voda.springbootapicaching.model.dto.DotDto;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/english")
@CrossOrigin(origins = "*")
@Controller
public class EnglishDotController extends  AbstractContoller {
    private final ConcurrentHashMap_EnglishDotCacheService concurrentHashMapEnglishDotCacheService;
    public EnglishDotController(RestTemplate restTemplate, ConcurrentHashMap_EnglishDotCacheService concurrentHashMapEnglishDotCacheService) {
        super(restTemplate);
        this.concurrentHashMapEnglishDotCacheService = concurrentHashMapEnglishDotCacheService;
    }

    @GetMapping
    public ResponseEntity mirrorRest(@RequestParam String letter, HttpMethod method, HttpServletRequest request) {
        String key = request.getQueryString();
        String cacheValue = concurrentHashMapEnglishDotCacheService.cacheHit(key);
        if (cacheValue != null) {
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CACHE_HIT, new DotDto(cacheValue)), HttpStatus.OK);
        }
        System.out.println("not cache hit 수신 성공");
        ResponseEntity responseEntity = sendRequestToApiServer(letter, method, request);
//        System.out.println(responseEntity.getBody());
        return responseEntity;
    }
}