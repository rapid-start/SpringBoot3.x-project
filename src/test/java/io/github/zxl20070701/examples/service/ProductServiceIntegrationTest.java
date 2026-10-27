package io.github.zxl20070701.examples.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import io.github.zxl20070701.examples.dto.ProductRequest;

@SpringBootTest
@ActiveProfiles("test")
class ProductServiceIntegrationTest {

    @Autowired
    private ProductService productService;

    @Test
    void performsProductCrudOperations() {
        var product = productService.create(new ProductRequest("键盘", new BigDecimal("299.00")));

        assertThat(product.id()).isNotNull();
        assertThat(productService.findById(product.id())).contains(product);
        assertThat(productService.findAll()).contains(product);

        assertThat(productService.update(
                product.id(), new ProductRequest("机械键盘", new BigDecimal("399.00"))))
                .isTrue();
        assertThat(productService.findById(product.id()))
                .hasValueSatisfying(updated -> {
                    assertThat(updated.name()).isEqualTo("机械键盘");
                    assertThat(updated.price()).isEqualByComparingTo("399.00");
                });

        assertThat(productService.delete(product.id())).isTrue();
        assertThat(productService.findById(product.id())).isEmpty();
        assertThat(productService.delete(product.id())).isFalse();
    }
}
