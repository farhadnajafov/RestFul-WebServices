package com.example.restfull.webservices.helloWorld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

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

    @GetMapping("/hello-world-internationalization")
    public String HelloWorldInternationalization(){

        return messageSource.getMessage("good.morning.message",null,"Default Message", LocaleContextHolder.getLocale());
    }

}
