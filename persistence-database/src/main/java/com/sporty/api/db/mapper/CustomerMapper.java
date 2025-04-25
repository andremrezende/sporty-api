package com.sporty.api.db.mapper;

import com.sporty.api.core.model.Customer;
import com.sporty.api.db.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
  CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

  Customer toModel(CustomerEntity entity);

  CustomerEntity toEntity(Customer model);
}
