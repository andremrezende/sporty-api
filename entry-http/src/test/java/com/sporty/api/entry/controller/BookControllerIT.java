package com.sporty.api.entry.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sporty.api.core.model.Book;
import com.sporty.api.core.model.BookType;
import com.sporty.api.core.ports.BookPersistencePort;
import com.sporty.api.db.mapper.BookMapper;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerIT {
  @Autowired BookPersistencePort bookRepository;

  @Autowired private MockMvc mockMvc;

  private Book book;

  @BeforeEach
  void setUp() {
    book = new Book();
    book.setId(UUID.randomUUID().toString());
    book.setTitle("Sample Book");
    book.setType(BookType.REGULAR);
    book.setBasePrice(29.99);
  }

  @Test
  void shouldReturnListOfBooks() throws Exception {
    bookRepository.save(book);

    mockMvc
        .perform(get("/api/v1/books").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].title").value("Sample Book"))
        .andExpect(jsonPath("$[0].type").value("REGULAR"))
        .andExpect(jsonPath("$[0].basePrice").value(29.99));
  }

  @Test
  void shouldReturnEmptyListWhenNoBooksAvailable() throws Exception {
    mockMvc
        .perform(get("/api/v1/books").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json("[]"));
  }

  @TestConfiguration
  static class TestConfig {
    @Bean
    public BookMapper bookMapper() {
      return BookMapper.INSTANCE;
    }

    //        @Bean
    //        public BookPersistencePort bookRepository() {
    //            return new BookMemRepository();
    //        }
  }
}
