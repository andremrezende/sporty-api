package com.sporty.api.db.adapter;

import com.sporty.api.core.exceptions.EntityNotFoundException;
import com.sporty.api.core.model.CustomerLoyalty;
import com.sporty.api.core.ports.CustomerLoyaltyPort;
import com.sporty.api.db.entity.CustomerLoyaltyEntity;
import com.sporty.api.db.mapper.CustomerLoyaltyMapper;
import com.sporty.api.db.repository.CustomerLoyaltyJpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CustomerLoyaltyRepositoryAdapter implements CustomerLoyaltyPort {
  private final CustomerLoyaltyJpaRepository customerLoyaltyJpaRepository;
  private final CustomerLoyaltyMapper mapper = CustomerLoyaltyMapper.INSTANCE;

  public CustomerLoyaltyRepositoryAdapter(CustomerLoyaltyJpaRepository jpaRepository) {
    this.customerLoyaltyJpaRepository = jpaRepository;
  }

  @Override
  public CustomerLoyalty findByCustomerId(UUID customerID) throws EntityNotFoundException {
    Optional<CustomerLoyaltyEntity> byCustomerId =
        customerLoyaltyJpaRepository.findByCustomerId(customerID);
    if (!byCustomerId.isPresent()) {
      throw new EntityNotFoundException(String.format("Customer %s not found", customerID));
    }
    return mapper.toModel(byCustomerId.stream().findFirst().get());
  }

  @Override
  public CustomerLoyalty save(CustomerLoyalty customerLoyalty) {
    return mapper.toModel(customerLoyaltyJpaRepository.save(mapper.toEntity(customerLoyalty)));
  }

  @Override
  public CustomerLoyalty update(CustomerLoyalty customerLoyalty) {
    return mapper.toModel(customerLoyaltyJpaRepository.save(mapper.toEntity(customerLoyalty)));
  }

  @Override
  public CustomerLoyalty delete(CustomerLoyalty customerLoyalty) {
    customerLoyaltyJpaRepository.delete(mapper.toEntity(customerLoyalty));
    return customerLoyalty;
  }

  @Override
  public CustomerLoyalty findById(UUID id) {
    return mapper.toModel(
        customerLoyaltyJpaRepository.findById(id).orElse(new CustomerLoyaltyEntity()));
  }

  @Override
  public List<CustomerLoyalty> findAll() {
    return mapper.toModels(customerLoyaltyJpaRepository.findAll());
  }
}
