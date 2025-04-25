package com.sporty.api.entry.support;

import com.sporty.api.core.ports.BookPersistencePort;
import com.sporty.api.core.ports.CustomerLoyaltyPort;
import com.sporty.api.core.ports.CustomerPort;
import com.sporty.api.core.ports.PurchasePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

  @Bean
  @Primary
  public BookPersistencePort bookRepository() {
    return new BookMemRepository();
  }

  @Bean
  @Primary
  public CustomerLoyaltyPort customerLoyaltyRepository() {
    return new CustomerLoyaltyMemRepository();
  }

  @Bean
  @Primary
  public PurchasePort purchasePort() {
    return new PurchaseMemRepository();
  }

  @Bean
  @Primary
  public CustomerPort customerRepository() {
    return new CustomerMemRepository();
  }
}
