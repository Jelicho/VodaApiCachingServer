package com.voda.springbootapicaching.controller;

import com.voda.springbootapicaching.model.domain.service.KoreanDotCacheService;
import com.voda.springbootapicaching.enumeration.ResponseMessage;
import com.voda.springbootapicaching.enumeration.StatusCode;
import com.voda.springbootapicaching.model.common.DefaultRes;
import com.voda.springbootapicaching.model.dto.DotDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

@RequestMapping("/korean")
@CrossOrigin(origins = "*")
@Controller
public class KoreanDotController extends  AbstractContoller {
    private final KoreanDotCacheService koreanDotCacheService;
    public KoreanDotController(RestTemplate restTemplate, KoreanDotCacheService koreanDotCacheService) {
        super(restTemplate);
        this.koreanDotCacheService = koreanDotCacheService;
    }

    @GetMapping
    public ResponseEntity mirrorRest(@RequestParam String letter, HttpMethod method, HttpServletRequest request) {
        String key = request.getQueryString();
        String cacheValue = koreanDotCacheService.cacheHit(key);
        if (cacheValue != null) {
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CACHE_HIT, new DotDto(cacheValue)), HttpStatus.OK);
        }
        System.out.println("not cache hit 수신 성공");
        ResponseEntity responseEntity = sendRequestToApiServer(letter, method, request);
//        System.out.println(responseEntity.getBody());
        return responseEntity;
    }
}