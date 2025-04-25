package com.sporty.api.config;

import com.sporty.api.core.cases.CalculateBookPriceUseCase;
import com.sporty.api.core.ports.BookPersistencePort;
import com.sporty.api.core.ports.CustomerLoyaltyPort;
import com.sporty.api.core.ports.CustomerPort;
import com.sporty.api.core.ports.PurchasePort;
import com.sporty.api.db.adapter.BookRepositoryAdapter;
import com.sporty.api.db.adapter.CustomerLoyaltyRepositoryAdapter;
import com.sporty.api.db.adapter.CustomerRepositoryAdapter;
import com.sporty.api.db.adapter.PurchaseRepositoryAdapter;
import com.sporty.api.db.mapper.PurchaseMapper;
import com.sporty.api.db.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!test")
public class SportyConfig {

  @Bean
  public BookPersistencePort bookPersistence(BookJpaRepository bookJpaRepository) {
    return new BookRepositoryAdapter(bookJpaRepository);
  }

  @Bean
  public CalculateBookPriceUseCase calculateBookPriceUseCase(
      BookPersistencePort bookRepository,
      PurchasePort purchasePort,
      CustomerPort customerPort,
      CustomerLoyaltyPort customerLoyaltyPort) {
    return new CalculateBookPriceUseCase(
        bookRepository, purchasePort, customerPort, customerLoyaltyPort);
  }

  @Bean
  public CustomerLoyaltyPort customerLoyaltyPort(
      CustomerLoyaltyJpaRepository customerLoaltyJpaRepository) {
    return new CustomerLoyaltyRepositoryAdapter(customerLoaltyJpaRepository);
  }

  @Bean
  public PurchasePort purchasePort(
      PurchaseJpaRepository purchaseJpaRepository,
      BookJpaRepository bookJpaRepository,
      PurchaseMapper purchaseMapper) {
    return new PurchaseRepositoryAdapter(
        purchaseJpaRepository, bookJpaRepository, purchaseMapper);
  }

  @Bean
  @Primary
  public CustomerPort customerRepository(CustomerJpaRepository jpaRepository) {
    return new CustomerRepositoryAdapter(jpaRepository);
  }
}
