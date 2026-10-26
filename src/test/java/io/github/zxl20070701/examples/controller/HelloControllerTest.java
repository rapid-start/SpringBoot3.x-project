package io.github.zxl20070701.examples.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import io.github.zxl20070701.examples.dto.GreetingResponse;
import io.github.zxl20070701.examples.service.GreetingService;

/**
 * 切片测试：只加载 Web 层（Controller + MVC 相关），不启动整个上下文，速度更快。
 * 注意：@WebMvcTest 不会扫描 @Service，因此需要 @MockitoBean 模拟 GreetingService。
 */
@WebMvcTest(HelloController.class)
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GreetingService greetingService;

    @Test
    void helloWithNameReturnsGreeting() throws Exception {
        when(greetingService.greet("zxl20070701"))
                .thenReturn(new GreetingResponse("Hello, zxl20070701!", LocalDateTime.now()));

        mockMvc.perform(get("/api/hello").param("name", "zxl20070701"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello, zxl20070701!"))
                .andExpect(jsonPath("$.serverTime").exists());
    }

    @Test
    void helloWithoutNameUsesWorld() throws Exception {
        when(greetingService.greet(null))
                .thenReturn(new GreetingResponse("Hello, World!", LocalDateTime.now()));

        mockMvc.perform(get("/api/hello"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello, World!"));
    }
}
