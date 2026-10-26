package io.github.zxl20070701.examples.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import io.github.zxl20070701.examples.dto.GreetingResponse;

/**
 * 业务层：封装核心逻辑，方便复用与单元测试（不依赖 Web 层）。
 */
@Service
public class GreetingService {

    public GreetingResponse greet(String name) {
        String safeName = (name == null || name.isBlank()) ? "World" : name.trim();
        String message = "Hello, " + safeName + "!";
        return new GreetingResponse(message, LocalDateTime.now());
    }
}
