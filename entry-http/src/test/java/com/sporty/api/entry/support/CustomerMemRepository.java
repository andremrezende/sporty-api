package com.sporty.api.entry.support;

import com.sporty.api.core.model.Customer;
import com.sporty.api.core.ports.CustomerPort;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerMemRepository implements CustomerPort {
  private static final Set<Customer> buffer = new HashSet<>();

  @Override
  public Customer save(Customer entity) {
    buffer.add(entity);
    return entity;
  }

  @Override
  public Customer update(Customer entity) {
    buffer.add(entity);
    return entity;
  }

  @Override
  public Customer delete(Customer entity) {
    buffer.remove(entity);
    return entity;
  }

  @Override
  public Customer findById(UUID id) {
    return buffer.stream()
        .filter(customer -> customer.getId().equals(id.toString()))
        .findFirst()
        .orElse(new Customer());
  }

  @Override
  public List<Customer> findAll() {
    return buffer.stream().toList();
  }
}
