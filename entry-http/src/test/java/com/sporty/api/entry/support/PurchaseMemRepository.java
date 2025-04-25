package com.sporty.api.entry.support;

import com.sporty.api.core.model.Purchase;
import com.sporty.api.core.ports.PurchasePort;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class PurchaseMemRepository implements PurchasePort {
  private static final Set<Purchase> buffer = new HashSet<>();

  @Override
  public Purchase save(Purchase entity) {
    buffer.add(entity);
    return entity;
  }

  @Override
  public Purchase update(Purchase entity) {
    buffer.add(entity);
    return entity;
  }

  @Override
  public Purchase delete(Purchase entity) {
    buffer.remove(entity);
    return entity;
  }

  @Override
  public Purchase findById(UUID id) {
    return buffer.stream()
        .filter(purchase -> purchase.getId().equals(id.toString()))
        .findFirst()
        .orElse(new Purchase());
  }

  @Override
  public List<Purchase> findAll() {
    return buffer.stream().toList();
  }
}
