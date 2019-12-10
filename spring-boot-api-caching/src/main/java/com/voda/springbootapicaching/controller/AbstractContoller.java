package com.voda.springbootapicaching.controller;

import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

public class AbstractContoller {
    @Value("${API.host}")
    private String host;

    @Value("${API.port}")
    private int port;

    @Value("${API.baseUrl}")
    private String baseUrl;
    final Logger logger = getLogger(getClass());

    private final RestTemplate restTemplate;

    public AbstractContoller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity sendRequestToApiServer(@RequestParam String letter, HttpMethod method, HttpServletRequest request){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(baseUrl).append(host).append(":").append(port).append(request.getRequestURI());
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(stringBuffer.toString())
                .queryParam("letter", letter);
        HttpHeaders httpHeaders = new HttpHeaders();
        MediaType mediaType = new MediaType("application", "json", StandardCharsets.UTF_8);
        httpHeaders.set(HttpHeaders.ACCEPT, String.valueOf(mediaType));
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, String.valueOf(mediaType));

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, httpEntity, String.class);
    }
}
