package com.sporty.api.db.mapper;

import com.sporty.api.core.model.PurchaseItem;
import com.sporty.api.db.entity.PurchaseItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PurchaseItemMapper {
  PurchaseItemMapper INSTANCE = Mappers.getMapper(PurchaseItemMapper.class);

  PurchaseItem toModel(PurchaseItemEntity entity);

  PurchaseItemEntity toEntity(PurchaseItem model);
}
