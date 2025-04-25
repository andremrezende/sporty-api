package com.sporty.api.core.cases;

import static org.junit.jupiter.api.Assertions.*;

import com.sporty.api.core.model.Book;
import com.sporty.api.core.model.BookType;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculateBookPriceUseCaseTest {

  private CalculateBookPriceUseCase useCase;

  @BeforeEach
  void setUp() {
    useCase = new CalculateBookPriceUseCase(null, null, null, null);
  }

  @Test
  void shouldCalculatePriceForSingleNewRelease() {
    Book book = bookOf("Title", BookType.NEW_RELEASES, 100.00);

    BigDecimal total = useCase.calculateFinalPrice(List.of(book), false).getSecond();
    assertEquals(new BigDecimal("100.00"), total);
  }

  @Test
  void shouldCalculatePriceForSingleRegular() {
    Book book = bookOf("Regular", BookType.REGULAR, 80.00);

    BigDecimal total = useCase.calculateFinalPrice(List.of(book), false).getSecond();
    assertEquals(new BigDecimal("80.00"), total);
  }

  @Test
  void shouldCalculateDiscountForRegularBundle() {
    List<Book> books =
        List.of(
            bookOf("Book1", BookType.REGULAR, 50.00),
            bookOf("Book2", BookType.REGULAR, 50.00),
            bookOf("Book3", BookType.REGULAR, 50.00));

    BigDecimal total = useCase.calculateFinalPrice(books, false).getSecond();
    assertEquals(new BigDecimal("135.00"), total); // 3 x (50 * 0.9)
  }

  @Test
  void shouldCalculateDiscountForOldEditionBundle() {
    List<Book> books =
        List.of(
            bookOf("Old1", BookType.OLD_EDITIONS, 100.00),
            bookOf("Old2", BookType.OLD_EDITIONS, 100.00),
            bookOf("Old3", BookType.OLD_EDITIONS, 100.00));

    BigDecimal total = useCase.calculateFinalPrice(books, false).getSecond();
    assertEquals(new BigDecimal("228.00"), total); // 100 * 0.8 * 0.95 * 3
  }

  @Test
  void shouldCalculateOldEditionWithoutBundle() {
    Book book = bookOf("Old", BookType.OLD_EDITIONS, 100.00);

    BigDecimal total = useCase.calculateFinalPrice(List.of(book), false).getSecond();
    assertEquals(new BigDecimal("80.00"), total); // 100 * 0.8
  }

  @Test
  void shouldApplyLoyaltyDiscountRegular() {
    Book book = bookOf("Regular", BookType.REGULAR, 100.00);

    BigDecimal total = useCase.calculateFinalPrice(List.of(book), true).getSecond();
    assertEquals(new BigDecimal("0.00"), total); // 100 - 100
  }

  @Test
  void shouldApplyLoyaltyDiscountWithBundleOnOldEdition() {
    List<Book> books =
        List.of(
            bookOf("Old1", BookType.OLD_EDITIONS, 100.00),
            bookOf("Old2", BookType.OLD_EDITIONS, 100.00),
            bookOf("Old3", BookType.OLD_EDITIONS, 100.00));

    BigDecimal total = useCase.calculateFinalPrice(books, true).getSecond();
    BigDecimal expectedOneFree = new BigDecimal("76.00"); // 100 * 0.8 * 0.95
    BigDecimal totalWithLoyalty = new BigDecimal("228.00").subtract(expectedOneFree);

    assertEquals(totalWithLoyalty.setScale(2), total);
  }

  @Test
  void shouldNotGoBelowZeroWithLoyaltyDiscount() {
    Book book = bookOf("Cheap", BookType.OLD_EDITIONS, 10.00);

    BigDecimal total = useCase.calculateFinalPrice(List.of(book), true).getSecond();
    assertEquals(new BigDecimal("0.00"), total);
  }

  @Test
  void shouldReturnZeroForEmptyBookList() {
    BigDecimal total = useCase.calculateFinalPrice(List.of(), false).getSecond();
    assertEquals(new BigDecimal("0.00"), total);
  }

  @Test
  void shouldHandleMixedBookTypesWithBundleAndLoyalty() {
    List<Book> books =
        List.of(
            bookOf("New", BookType.NEW_RELEASES, 100.00),
            bookOf("Regular", BookType.REGULAR, 80.00),
            bookOf("Old", BookType.OLD_EDITIONS, 60.00));

    BigDecimal total = useCase.calculateFinalPrice(books, true).getSecond();

    // NEW = 100.00
    // REGULAR (bundle) = 80 * 0.9 = 72
    // OLD (bundle) = 60 * 0.8 * 0.95 = 45.60
    // Total = 145.60, then one book free
    BigDecimal expected = new BigDecimal("145.60");
    assertEquals(expected.setScale(2), total);
  }

  private Book bookOf(String title, BookType type, double price) {
    Book book = new Book();
    book.setId(UUID.randomUUID().toString());
    book.setTitle(title);
    book.setType(type);
    book.setBasePrice(price);
    return book;
  }
}
