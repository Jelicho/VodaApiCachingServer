package com.voda.springbootapicaching.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.voda.springbootapicaching.model.dto.DotDto;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class APIRes {
    @JsonProperty("status")
    String status;
    @JsonProperty("message")
    String message;
    @JsonProperty("data")
    Dot data;

    @Data
    public static class Dot{
        @JsonProperty("dotValue")
        String dotValue;
    }
}
