package com.jia.wx.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CallBackController {

    @RequestMapping("/test")
    public String test(){
        return "hello word";
    }
}
