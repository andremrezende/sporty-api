package com.sporty.api.entry.controller;

import com.sporty.api.core.model.Book;
import com.sporty.api.core.ports.BookPersistencePort;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

  private final BookPersistencePort bookRepository;

  public BookController(BookPersistencePort bookRepository) {
    this.bookRepository = bookRepository;
  }

  /** GET /api/v1/books Returns a list of all books available for purchase. */
  @GetMapping
  public ResponseEntity<List<Book>> getAvailableBooks() {
    List<Book> books = bookRepository.findAll();
    return ResponseEntity.ok(books);
  }
}
