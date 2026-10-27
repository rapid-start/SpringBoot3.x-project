package io.github.zxl20070701.examples.dto;

import java.math.BigDecimal;

public record ProductRequest(String name, BigDecimal price) {
}
