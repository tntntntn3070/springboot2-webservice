package com.dev.springboot.dev.controllers;

import com.dev.springboot.dev.dto.ApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JacksonTestController {

    @RequestMapping(value = "/api/check", method = RequestMethod.GET)
    public ApiResponse check(){
        return ApiResponse.ok();
    }
}
