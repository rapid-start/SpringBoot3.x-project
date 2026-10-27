package io.github.zxl20070701.examples;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

/**
 * 集成测试：加载完整 Spring 上下文，验证应用能正常启动。
 * 使用 @SpringBootTest 启动真实上下文（不真正起 Web 端口，可用 webEnvironment 配置）。
 */
@SpringBootTest
@ActiveProfiles("test")
class ExamplesApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Test
    void contextLoads() {
        assertThat(context).isNotNull();
        assertThat(context.containsBean("greetingService")).isTrue();
    }
}
