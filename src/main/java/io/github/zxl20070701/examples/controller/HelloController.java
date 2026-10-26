package io.github.zxl20070701.examples.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.zxl20070701.examples.dto.GreetingResponse;
import io.github.zxl20070701.examples.service.GreetingService;

/**
 * Web 层：负责接收 HTTP 请求，委托给 Service。
 */
@RestController
@RequestMapping("/api")
public class HelloController {

    private final GreetingService greetingService;

    public HelloController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/hello")
    public GreetingResponse hello(@RequestParam(required = false) String name) {
        return greetingService.greet(name);
    }
}
