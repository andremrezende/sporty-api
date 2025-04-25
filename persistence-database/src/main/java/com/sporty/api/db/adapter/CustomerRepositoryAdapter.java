package com.sporty.api.db.adapter;

import com.sporty.api.core.model.Customer;
import com.sporty.api.core.ports.CustomerPort;
import com.sporty.api.db.entity.CustomerEntity;
import com.sporty.api.db.mapper.CustomerMapper;
import com.sporty.api.db.repository.CustomerJpaRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CustomerRepositoryAdapter implements CustomerPort {
  private final CustomerJpaRepository customerJpaRepository;
  private final CustomerMapper mapper = CustomerMapper.INSTANCE;

  public CustomerRepositoryAdapter(CustomerJpaRepository jpaRepository) {
    this.customerJpaRepository = jpaRepository;
  }

  @Override
  public Customer save(Customer customer) {
    CustomerEntity entity = mapper.toEntity(customer);
    entity = customerJpaRepository.save(entity);
    return mapper.toModel(entity);
  }

  @Override
  public Customer update(Customer customer) {
    CustomerEntity entity = mapper.toEntity(customer);
    entity = customerJpaRepository.save(entity);
    return mapper.toModel(entity);
  }

  @Override
  public Customer delete(Customer customer) {
    CustomerEntity entity = mapper.toEntity(customer);
    customerJpaRepository.delete(entity);
    return customer;
  }

  @Override
  public Customer findById(UUID id) {
    CustomerEntity entity = customerJpaRepository.findById(id).orElse(new CustomerEntity());
    return mapper.toModel(entity);
  }

  @Override
  public List<Customer> findAll() {
    return customerJpaRepository.findAll().stream()
        .map(mapper::toModel)
        .collect(Collectors.toList());
  }
}
