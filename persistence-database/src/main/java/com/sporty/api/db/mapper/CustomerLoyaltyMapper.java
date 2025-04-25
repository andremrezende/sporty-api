package com.sporty.api.db.mapper;

import com.sporty.api.core.model.CustomerLoyalty;
import com.sporty.api.db.entity.CustomerLoyaltyEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerLoyaltyMapper {
  CustomerLoyaltyMapper INSTANCE = Mappers.getMapper(CustomerLoyaltyMapper.class);

  CustomerLoyalty toModel(CustomerLoyaltyEntity entity);

  CustomerLoyaltyEntity toEntity(CustomerLoyalty model);

  List<CustomerLoyalty> toModels(List<CustomerLoyaltyEntity> all);
}
