package com.min.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Chris
 * @create: 2023-09-02 18:35
 **/
@RestController
public class SampleController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot!";
    }
}

