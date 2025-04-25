package com.sporty.api.db;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FlywayScriptTest {
  @Autowired private JdbcTemplate jdbcTemplate;

  @Test
  public void migrationScriptShouldRunSuccessfully() {
    int rowCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM book", Integer.class);

    assertEquals(4, rowCount, "The migration script did not execute correctly.");
  }
}
