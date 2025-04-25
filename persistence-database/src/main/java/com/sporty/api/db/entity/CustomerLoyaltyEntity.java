package com.sporty.api.db.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "customer_loyalty")
public class CustomerLoyaltyEntity extends BaseEntity implements Serializable {

  @Column(name = "customer_id", nullable = false, unique = true)
  private UUID customerId;

  @Column(name = "loyalty_points", nullable = false)
  private int loyaltyPoints = 0;

  @Column(name = "last_updated", nullable = false)
  private LocalDateTime lastUpdated = LocalDateTime.now();

  @PreUpdate
  public void updateTimestamp() {
    this.lastUpdated = LocalDateTime.now();
  }

  public UUID getCustomerId() {
    return customerId;
  }

  public void setCustomerId(UUID customerId) {
    this.customerId = customerId;
  }

  public int getLoyaltyPoints() {
    return loyaltyPoints;
  }

  public void setLoyaltyPoints(int loyaltyPoints) {
    this.loyaltyPoints = loyaltyPoints;
  }

  public LocalDateTime getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(LocalDateTime lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    CustomerLoyaltyEntity that = (CustomerLoyaltyEntity) o;
    return loyaltyPoints == that.loyaltyPoints
        && Objects.equals(customerId, that.customerId)
        && Objects.equals(lastUpdated, that.lastUpdated);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), customerId, loyaltyPoints, lastUpdated);
  }

  @Override
  public String toString() {
    return "CustomerLoyaltyEntity{"
        + "customerId="
        + customerId
        + ", loyaltyPoints="
        + loyaltyPoints
        + ", lastUpdated="
        + lastUpdated
        + '}';
  }
}
