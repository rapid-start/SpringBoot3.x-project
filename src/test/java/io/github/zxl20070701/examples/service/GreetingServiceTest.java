package io.github.zxl20070701.examples.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import io.github.zxl20070701.examples.dto.GreetingResponse;

/**
 * 纯单元测试：不加载 Spring 上下文，直接实例化被测类，最快且最独立。
 */
class GreetingServiceTest {

    private final GreetingService service = new GreetingService();

    @Test
    void greetWithNameTrimsAndPrefixes() {
        GreetingResponse response = service.greet("  zxl20070701  ");
        assertThat(response.message()).isEqualTo("Hello, zxl20070701!");
        assertThat(response.serverTime()).isNotNull();
    }

    @Test
    void greetWithBlankFallsBackToWorld() {
        assertThat(service.greet("   ").message()).isEqualTo("Hello, World!");
        assertThat(service.greet(null).message()).isEqualTo("Hello, World!");
    }
}
