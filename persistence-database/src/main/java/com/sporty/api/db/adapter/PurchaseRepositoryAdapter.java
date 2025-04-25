package com.sporty.api.db.adapter;

import com.sporty.api.core.model.Purchase;
import com.sporty.api.core.ports.PurchasePort;
import com.sporty.api.db.entity.PurchaseEntity;
import com.sporty.api.db.mapper.PurchaseMapper;
import com.sporty.api.db.repository.BookJpaRepository;
import com.sporty.api.db.repository.PurchaseItemRepository;
import com.sporty.api.db.repository.PurchaseJpaRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public class PurchaseRepositoryAdapter implements PurchasePort {
  private final PurchaseJpaRepository purchaseJpaRepository;
  private final BookJpaRepository bookJpaRepository;
  private final PurchaseMapper mapper;

  public PurchaseRepositoryAdapter(
          PurchaseJpaRepository purchaseJpaRepository,
          BookJpaRepository bookJpaRepository, PurchaseMapper mapper) {
    this.purchaseJpaRepository = purchaseJpaRepository;
    this.bookJpaRepository = bookJpaRepository;
    this.mapper = mapper;
  }

  @Transactional
  @Override
  public Purchase save(Purchase purchase) {
    PurchaseEntity purchaseEntity = mapper.toEntity(purchase);
    PurchaseEntity finalPurchaseEntity = purchaseEntity;
    purchaseEntity.getItems().forEach(item -> {
      item.setFinalPrice(bookJpaRepository.findById(item.getBookId()).get().getBasePrice());
      item.setPurchase(finalPurchaseEntity);
    });
    purchaseEntity = purchaseJpaRepository.save(purchaseEntity);
    return mapper.toModel(purchaseEntity);
  }

  @Override
  public Purchase update(Purchase purchase) {
    return mapper.toModel(purchaseJpaRepository.save(mapper.toEntity(purchase)));
  }

  @Override
  public Purchase delete(Purchase purchase) {
    purchaseJpaRepository.delete(mapper.toEntity(purchase));
    return purchase;
  }

  @Override
  public Purchase findById(UUID id) {
    return mapper.toModel(purchaseJpaRepository.findById(id).orElse(new PurchaseEntity()));
  }

  @Override
  public List findAll() {
    return mapper.toModels(purchaseJpaRepository.findAll());
  }
}
