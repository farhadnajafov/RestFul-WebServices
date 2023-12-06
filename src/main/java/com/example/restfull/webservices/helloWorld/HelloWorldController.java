package com.example.restfull.webservices.helloWorld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping("/hello-world")
    public String HelloWorld(){
        return "HelloWorld";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean HelloWorldBean(){

        return new HelloWorldBean("HelloWorld 2");
    }

    @GetMapping("/hello-world-path-variable/{name}")
    public HelloWorldBean HelloWorldPathVariable(@PathVariable String name){

        return new HelloWorldBean(String.format("HelloWorld 2 %s",name));
    }
}
