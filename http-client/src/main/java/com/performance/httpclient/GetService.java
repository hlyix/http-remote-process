package com.performance.httpclient;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.performance.service.HelloServiceImpl;
import iservice.HelloService;


import java.io.IOException;

public class GetService {



    public static void main(String[] args) throws IOException {
        Kryo kryo = new Kryo();
        kryo.register(HelloServiceImpl.class);
        long start = System.currentTimeMillis();
        for(int i = 0 ;i<100;i++){
        byte[] bytes = HttpClientUtil.post("http://localhost:8080/getService","application/octet-stream");

        Input input = new Input(bytes);
        HelloService helloService = kryo.readObject(input,HelloServiceImpl.class);

        System.out.println("helloService is "+helloService.hello());
        }
        long totalTime = System.currentTimeMillis()-start;
        System.out.print("=================\n"+"totaltime is "+totalTime);

    }









}
