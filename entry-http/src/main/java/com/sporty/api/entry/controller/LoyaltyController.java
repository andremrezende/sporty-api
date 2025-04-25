package com.sporty.api.entry.controller;

import com.sporty.api.core.exceptions.EntityNotFoundException;
import com.sporty.api.core.model.CustomerLoyalty;
import com.sporty.api.core.ports.CustomerLoyaltyPort;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loyalty")
public class LoyaltyController {
  private static final Logger LOG = LoggerFactory.getLogger(LoyaltyController.class);

  private final CustomerLoyaltyPort loyaltyPort;

  public LoyaltyController(CustomerLoyaltyPort loyaltyPort) {
    this.loyaltyPort = loyaltyPort;
  }

  @GetMapping("/{customerId}")
  public ResponseEntity<CustomerLoyalty> getLoyalty(@PathVariable UUID customerId) {
    CustomerLoyalty loyalty = null;
    try {
      loyalty = loyaltyPort.findByCustomerId(customerId);
    } catch (EntityNotFoundException e) {
      LOG.error(e.getMessage());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomerLoyalty());
    }
    return ResponseEntity.ok(loyalty);
  }
}
