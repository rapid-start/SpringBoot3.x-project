package io.github.zxl20070701.examples.dto;

import java.math.BigDecimal;

public record Product(Long id, String name, BigDecimal price) {
}
