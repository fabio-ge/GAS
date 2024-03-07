package com.fabio.gas.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/test/")
public class TestController {

    @GetMapping("hello")
    public String hello(){
        return "hello";
    }
}
