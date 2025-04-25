package com.sporty.api.core.cases;

import com.sporty.api.core.exceptions.EntityNotFoundException;
import com.sporty.api.core.exceptions.NoQtdBookAvailableException;
import com.sporty.api.core.model.*;
import com.sporty.api.core.ports.BookPersistencePort;
import com.sporty.api.core.ports.CustomerLoyaltyPort;
import com.sporty.api.core.ports.CustomerPort;
import com.sporty.api.core.ports.PurchasePort;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.data.util.Pair;
import org.springframework.util.ObjectUtils;

public class CalculateBookPriceUseCase {

  private final BookPersistencePort bookRepository;
  private final PurchasePort purchasePort;
  private final CustomerPort customerPort;
  private final CustomerLoyaltyPort customerLoyaltyPort;

  public CalculateBookPriceUseCase(
      BookPersistencePort bookRepository,
      PurchasePort purchasePort,
      CustomerPort customerPort,
      CustomerLoyaltyPort customerLoyaltyPort) {
    this.bookRepository = bookRepository;
    this.purchasePort = purchasePort;
    this.customerPort = customerPort;
    this.customerLoyaltyPort = customerLoyaltyPort;
  }

  public Purchase calculateFinalPrice(Purchase purchase)
      throws EntityNotFoundException, NoQtdBookAvailableException {
    for (PurchaseItem item : purchase.getItems()) {
      Book book = bookRepository.findById(item.getBookId());
      if (book.getQuantity() < item.getQuantity()) {
        throw new NoQtdBookAvailableException(
            "Book %s has insufficient stock".formatted(book.getTitle()));
      }
    }
    List<Book> books =
        purchase.getItems().stream()
            .map(item -> bookRepository.findById(item.getBookId()))
            .sorted(Comparator.comparing(Book::getBasePrice))
            .collect(Collectors.toList());

    int loyaltyPoints = 0;
    boolean useLoyaltyDiscount = false;
    CustomerLoyalty customerLoyalty = null;
    try {
      customerLoyalty = customerLoyaltyPort.findByCustomerId(purchase.getCustomerId());
      loyaltyPoints = customerLoyalty.getLoyaltyPoints();
      useLoyaltyDiscount = loyaltyPoints >= 10;
    } catch (EntityNotFoundException e) {
      // no points
    }

    Pair<Book, BigDecimal> total = calculateFinalPrice(books, useLoyaltyDiscount);

    if (useLoyaltyDiscount && total.getFirst().getId() != null) {
      purchase.setLoyaltyPointsUsed(10);
      customerLoyalty.setLoyaltyPoints(0);
      customerLoyaltyPort.save(customerLoyalty);
    } else {
      purchase.setLoyaltyPointsUsed(0);
    }

    purchase.setTotalPrice(total.getSecond());
    purchase.setCreatedAt(LocalDateTime.now());
    purchasePort.save(purchase);
    return purchase;
  }

  public Pair<Book, BigDecimal> calculateFinalPrice(List<Book> books, boolean useLoyaltyPoints) {
    BigDecimal total = BigDecimal.ZERO;
    int totalBooks = books.size();
    Book eligibleFreeBook = new Book();
    for (Book book : books) {
      if (ObjectUtils.isEmpty(book)
          || ObjectUtils.isEmpty(book.getId())
          || ObjectUtils.isEmpty(book.getType())) {
        continue;
      }
      if (eligibleFreeBook.getId() == null
          && useLoyaltyPoints
          && (book.getType() == BookType.REGULAR || book.getType() == BookType.OLD_EDITIONS)) {
        eligibleFreeBook = book;
      } else {
        total = total.add(calculatePriceForBook(book, totalBooks));
      }
    }

    return Pair.of(eligibleFreeBook, total.setScale(2, RoundingMode.HALF_UP));
  }

  private BigDecimal calculatePriceForBook(Book book, int totalBooksInPurchase) {
    boolean bundle = totalBooksInPurchase >= 3;
    return ObjectUtils.isEmpty(book.getType())
        ? BigDecimal.ZERO
        : book.getType().applyDiscount(BigDecimal.valueOf(book.getBasePrice()), bundle);
  }
}
