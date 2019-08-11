package com.performance.service;

import iservice.HelloService;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class HelloServiceImpl implements HelloService,Serializable {


    @Override
    public String hello() {
        return "hello service";
    }

    public HelloServiceImpl(){};
}
