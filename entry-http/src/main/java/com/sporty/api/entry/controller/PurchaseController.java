package com.sporty.api.entry.controller;

import com.sporty.api.core.cases.CalculateBookPriceUseCase;
import com.sporty.api.core.exceptions.EntityNotFoundException;
import com.sporty.api.core.exceptions.NoQtdBookAvailableException;
import com.sporty.api.core.model.Purchase;
import com.sporty.api.core.ports.PurchasePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/purchase")
public class PurchaseController {
  private static final Logger LOG = LoggerFactory.getLogger(PurchaseController.class);
  private final CalculateBookPriceUseCase priceUseCase;
  private final PurchasePort purchasePort;

  public PurchaseController(CalculateBookPriceUseCase priceUseCase, PurchasePort purchasePort) {
    this.priceUseCase = priceUseCase;
    this.purchasePort = purchasePort;
  }

  @PostMapping
  public ResponseEntity<Purchase> purchase(@RequestBody Purchase purchase) {
    Purchase finalPrice = null;
    try {
      finalPrice = this.priceUseCase.calculateFinalPrice(purchase);
    } catch (EntityNotFoundException e) {
      LOG.error(e.getMessage());
      return ResponseEntity.notFound().build();
    } catch (NoQtdBookAvailableException e) {
      return ResponseEntity.unprocessableEntity().build();
    }
    return ResponseEntity.ok(finalPrice);
  }
}
