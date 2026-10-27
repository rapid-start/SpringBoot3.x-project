package io.github.zxl20070701.examples.repository;

import java.math.BigDecimal;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import io.github.zxl20070701.examples.dto.Product;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Product create(String name, BigDecimal price) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            var statement = connection.prepareStatement(
                    "INSERT INTO product (name, price) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.setBigDecimal(2, price);
            return statement;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key == null) {
            throw new IllegalStateException("Insert did not return a generated product ID");
        }
        return new Product(key.longValue(), name, price);
    }

    public List<Product> findAll() {
        return jdbcTemplate.query(
                "SELECT id, name, price FROM product ORDER BY id",
                (resultSet, rowNumber) -> new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getBigDecimal("price")));
    }

    public Optional<Product> findById(long id) {
        return jdbcTemplate.query(
                "SELECT id, name, price FROM product WHERE id = ?",
                (resultSet, rowNumber) -> new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getBigDecimal("price")),
                id).stream().findFirst();
    }

    public boolean update(long id, String name, BigDecimal price) {
        return jdbcTemplate.update(
                "UPDATE product SET name = ?, price = ? WHERE id = ?",
                name, price, id) > 0;
    }

    public boolean delete(long id) {
        return jdbcTemplate.update("DELETE FROM product WHERE id = ?", id) > 0;
    }
}
