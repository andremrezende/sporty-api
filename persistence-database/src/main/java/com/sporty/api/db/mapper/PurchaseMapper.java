package com.sporty.api.db.mapper;

import com.sporty.api.core.model.Purchase;
import com.sporty.api.db.entity.PurchaseEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
    componentModel = "spring",
    uses = PurchaseItemMapper.class)
public interface PurchaseMapper {
  PurchaseMapper INSTANCE = Mappers.getMapper(PurchaseMapper.class);
  Purchase toModel(PurchaseEntity entity);

  PurchaseEntity toEntity(Purchase model);

  List<Purchase> toModels(List<PurchaseEntity> all);
}
