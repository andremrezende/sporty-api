package com.sporty.api.db.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.sporty.api.core.model.Book;
import com.sporty.api.core.model.BookType;
import com.sporty.api.db.entity.BookEntity;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class BookMapperTest {

  private BookMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = Mappers.getMapper(BookMapper.class);
  }

  @Test
  void shouldMapEntityToModelCorrectly() {
    BookEntity entity = new BookEntity();
    UUID id = UUID.randomUUID();
    entity.setId(id);
    entity.setTitle("Test Book");
    entity.setType(BookType.REGULAR.toString());
    entity.setBasePrice(new BigDecimal("49.99"));

    Book model = mapper.toModel(entity);

    assertNotNull(model);
    assertEquals(id.toString(), model.getId());
    assertEquals("Test Book", model.getTitle());
    assertEquals(BookType.REGULAR, model.getType());
    assertEquals(49.99, model.getBasePrice(), 0.001);
  }

  @Test
  void shouldMapModelToEntityCorrectly() {
    Book model = new Book();
    UUID id = UUID.randomUUID();
    model.setId(id.toString());
    model.setTitle("Clean Code");
    model.setType(BookType.NEW_RELEASES);
    model.setBasePrice(59.90);

    BookEntity entity = mapper.toEntity(model);

    assertNotNull(entity);
    assertEquals(id, entity.getId());
    assertEquals("Clean Code", entity.getTitle());
    assertEquals(BookType.NEW_RELEASES.toString(), entity.getType());
    assertEquals(new BigDecimal("59.9"), entity.getBasePrice());
  }

  @Test
  void shouldHandleNullEntityToModel() {
    Book model = mapper.toModel(null);
    assertNull(model);
  }

  @Test
  void shouldHandleNullModelToEntity() {
    Book book = null;
    BookEntity entity = mapper.toEntity(book);
    assertNull(entity);
  }
}
