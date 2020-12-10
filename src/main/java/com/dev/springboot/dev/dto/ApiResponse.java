package com.dev.springboot.dev.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@JsonAutoDetect
public class ApiResponse {

    private final int status;
    private final boolean success;
    private final String message;

    public static ApiResponse ok(){
        return new ApiResponse(200, true, "ok");
    }
}
