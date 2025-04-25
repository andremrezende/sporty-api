package com.sporty.api.db.repository;

import com.sporty.api.db.entity.PurchaseItemEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseItemRepository extends JpaRepository<PurchaseItemEntity, UUID> {}
