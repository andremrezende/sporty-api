package com.sporty.api.entry.support;

import com.sporty.api.core.exceptions.EntityNotFoundException;
import com.sporty.api.core.model.CustomerLoyalty;
import com.sporty.api.core.ports.CustomerLoyaltyPort;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerLoyaltyMemRepository implements CustomerLoyaltyPort {
  private static final Set<CustomerLoyalty> buffer = new HashSet<>();

  @Override
  public CustomerLoyalty save(CustomerLoyalty entity) {
    buffer.add(entity);
    return entity;
  }

  @Override
  public CustomerLoyalty update(CustomerLoyalty entity) {
    buffer.add(entity);
    return entity;
  }

  @Override
  public CustomerLoyalty delete(CustomerLoyalty entity) {
    buffer.remove(entity);
    return entity;
  }

  @Override
  public CustomerLoyalty findById(UUID id) {
    return buffer.stream()
        .filter(tmpCustomerLoyalty -> tmpCustomerLoyalty.getId().equals(id.toString()))
        .findFirst()
        .orElse(new CustomerLoyalty());
  }

  @Override
  public List<CustomerLoyalty> findAll() {
    return buffer.stream().toList();
  }

  @Override
  public CustomerLoyalty findByCustomerId(UUID customerID) throws EntityNotFoundException {
    return buffer.stream()
        .filter(tmpCustomerLoyalty -> tmpCustomerLoyalty.getCustomerId().equals(customerID))
        .findFirst()
        .orElse(new CustomerLoyalty());
  }
}
