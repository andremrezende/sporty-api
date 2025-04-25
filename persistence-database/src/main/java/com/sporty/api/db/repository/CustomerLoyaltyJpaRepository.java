package com.sporty.api.db.repository;

import com.sporty.api.db.entity.CustomerLoyaltyEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerLoyaltyJpaRepository extends JpaRepository<CustomerLoyaltyEntity, UUID> {
  Optional<CustomerLoyaltyEntity> findByCustomerId(UUID customerId);
}
