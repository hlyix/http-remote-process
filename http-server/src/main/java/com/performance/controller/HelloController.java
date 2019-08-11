package com.performance.controller;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.ByteBufferInput;
import com.esotericsoftware.kryo.io.ByteBufferOutput;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.performance.service.HelloServiceImpl;
import iservice.HelloService;
import org.apache.coyote.OutputBuffer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.ByteBuffer;


@RestController
public class HelloController {

    static Kryo kryo = new Kryo();

    static {
        kryo.register(HelloServiceImpl.class);
        kryo.register(HelloService.class);
    }

    @RequestMapping(value = "/getService",produces = "application/octet-stream")
    @ResponseBody
    public byte[] helloService() {
        HelloServiceImpl helloService = new HelloServiceImpl();

        byte[] bytes = new byte[1024 * 1024];

        Output output = new Output(bytes);
        kryo.writeObject(output, helloService);
        output.close();

        Input input = new Input(bytes);
        HelloService object2 = kryo.readObject(input, HelloServiceImpl.class);
        input.close();
        System.out.println(object2.hello());
        ByteBuffer buffer = ByteBuffer.allocate(1024*1024);
        ByteBufferOutput bufferOutput = new ByteBufferOutput(buffer);
        kryo.writeObject(bufferOutput,helloService);
        bufferOutput.close();
//  利用buffer
//        ByteBufferInput bufferInput = new ByteBufferInput(buffer);
//        HelloService o1 = kryo.readObject(bufferInput,HelloServiceImpl.class);
//        System.out.print(o1.hello());
//
//        return null;


        return bytes;

    }
}
