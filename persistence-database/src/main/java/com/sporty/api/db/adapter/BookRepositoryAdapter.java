package com.sporty.api.db.adapter;

import com.sporty.api.core.model.Book;
import com.sporty.api.core.ports.BookPersistencePort;
import com.sporty.api.db.entity.BookEntity;
import com.sporty.api.db.mapper.BookMapper;
import com.sporty.api.db.repository.BookJpaRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BookRepositoryAdapter implements BookPersistencePort {
  private final BookJpaRepository bookJpaRepository;
  private final BookMapper mapper = BookMapper.INSTANCE;

  public BookRepositoryAdapter(BookJpaRepository jpaRepository) {
    this.bookJpaRepository = jpaRepository;
  }

  @Override
  public Book save(Book book) {
    BookEntity entity = mapper.toEntity(book);
    entity = bookJpaRepository.save(entity);
    return mapper.toModel(entity);
  }

  @Override
  public Book update(Book book) {
    BookEntity entity = mapper.toEntity(book);
    entity = bookJpaRepository.save(entity);
    return mapper.toModel(entity);
  }

  @Override
  public Book delete(Book book) {
    BookEntity entity = mapper.toEntity(book);
    bookJpaRepository.delete(entity);
    return book;
  }

  @Override
  public Book findById(UUID id) {
    BookEntity entity = bookJpaRepository.findById(id).orElse(new BookEntity());
    return mapper.toModel(entity);
  }

  @Override
  public List<Book> findAll() {
    return bookJpaRepository.findAll().stream().map(mapper::toModel).collect(Collectors.toList());
  }
}
