package io.github.zxl20070701.examples.dto;

import java.time.LocalDateTime;

/**
 * 响应 DTO：对外暴露的数据结构，避免直接把实体/内部对象暴露给客户端。
 */
// record（Java 16+ 特性）：一行代码自动生成构造器、getter、equals、hashCode、toString
public record GreetingResponse(String message, LocalDateTime serverTime) {
}
