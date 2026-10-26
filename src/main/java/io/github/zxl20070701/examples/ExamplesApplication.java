package io.github.zxl20070701.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用入口。
 * @SpringBootApplication 组合了 @Configuration + @EnableAutoConfiguration + @ComponentScan
 */
@SpringBootApplication
public class ExamplesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamplesApplication.class, args);
    }
}
