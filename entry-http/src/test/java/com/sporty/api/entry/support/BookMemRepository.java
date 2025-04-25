package com.sporty.api.entry.support;

import com.sporty.api.core.model.Book;
import com.sporty.api.core.ports.BookPersistencePort;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class BookMemRepository implements BookPersistencePort {
  private static final Set<Book> buffer = new HashSet<>();

  @Override
  public Book save(Book entity) {
    buffer.add(entity);
    return entity;
  }

  @Override
  public Book update(Book entity) {
    buffer.add(entity);
    return entity;
  }

  @Override
  public Book delete(Book entity) {
    buffer.remove(entity);
    return entity;
  }

  @Override
  public Book findById(UUID id) {
    return buffer.stream()
        .filter(tmpBook -> tmpBook.getId().equals(id.toString()))
        .findFirst()
        .orElse(new Book());
  }

  @Override
  public List<Book> findAll() {
    return buffer.stream().toList();
  }
}
