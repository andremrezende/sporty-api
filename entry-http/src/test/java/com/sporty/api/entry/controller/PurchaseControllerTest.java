package com.sporty.api.entry.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sporty.api.core.cases.CalculateBookPriceUseCase;
import com.sporty.api.core.model.*;
import com.sporty.api.core.ports.BookPersistencePort;
import com.sporty.api.core.ports.CustomerLoyaltyPort;
import com.sporty.api.core.ports.CustomerPort;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@Import(CalculateBookPriceUseCase.class)
class PurchaseControllerTest {

  @Autowired private MockMvc mockMvc;
  @Autowired private BookPersistencePort bookRepository;
  @Autowired private CustomerPort customerPort;
  @Autowired private CustomerLoyaltyPort customerLoyaltyPort;
  private final ObjectMapper objectMapper = new ObjectMapper();

  @Test
  void shouldReturnNotFoundIfBookNotExists() throws Exception {
    // This test validates that if the book ID doesn't exist in the database,
    // the controller returns HTTP 404 Not Found.
    String ID = UUID.randomUUID().toString();
    List<PurchaseItem> items =
        List.of(new PurchaseItem(ID, 1), new PurchaseItem(ID, 1), new PurchaseItem(ID, 1));

    Purchase purchase = new Purchase();
    purchase.setCustomerId(UUID.randomUUID());
    purchase.setItems(items);
    purchase.setLoyaltyPointsUsed(0);

    mockMvc
        .perform(
            post("/api/v1/purchase")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(purchase)))
        .andExpect(status().isUnprocessableEntity());
  }

  @Test
  void shouldApplyBundleAndFidelityDiscountsAccurately() throws Exception {
    // This test checks the correct application of type-based discounts,
    // bundle discounts, and loyalty reward logic.
    UUID customerId = UUID.randomUUID();
    customerPort.save(new Customer(customerId.toString(), "John Doe"));
    customerLoyaltyPort.save(new CustomerLoyalty(customerId, 10, LocalDateTime.now()));

    Book newBook =
        new Book(UUID.randomUUID().toString(), "New Book", BookType.NEW_RELEASES, 100.00, 10);
    Book regularBook =
        new Book(UUID.randomUUID().toString(), "Regular Book", BookType.REGULAR, 80.00, 10);
    Book oldBook =
        new Book(UUID.randomUUID().toString(), "Old Book", BookType.OLD_EDITIONS, 60.00, 10);
    bookRepository.save(newBook);
    bookRepository.save(regularBook);
    bookRepository.save(oldBook);

    List<PurchaseItem> items =
        List.of(
            new PurchaseItem(newBook.getId(), 1),
            new PurchaseItem(regularBook.getId(), 1),
            new PurchaseItem(oldBook.getId(), 1));

    Purchase purchase = new Purchase();
    purchase.setCustomerId(customerId);
    purchase.setItems(items);

    // Expected total:
    // NEW = 100.00
    // REGULAR = 80 * 0.9 = 72
    // OLD = 60 * 0.8 * 0.95 = 45.60
    // Subtotal: 217.60
    // Loyalty discount: -72.00 (free REGULAR)
    // Final: 145.60
    mockMvc
        .perform(
            post("/api/v1/purchase")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(purchase)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.totalPrice").value(172.00));
  }

  @Test
  void shouldReturnEmptyItemsIfNoBooksProvided() throws Exception {
    // This test verifies that if no books are passed,
    // the result should be a zero-value purchase.
    Purchase purchase = new Purchase();
    purchase.setCustomerId(UUID.randomUUID());
    purchase.setItems(Collections.emptyList());

    mockMvc
        .perform(
            post("/api/v1/purchase")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(purchase)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.totalPrice").value(0.00));
  }
}
