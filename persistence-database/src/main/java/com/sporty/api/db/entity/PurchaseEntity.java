package com.sporty.api.db.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "purchase")
public class PurchaseEntity extends BaseEntity {

  @Column(name = "customer_id", nullable = false)
  private UUID customerId;

  @Column(name = "total_price", nullable = false)
  private BigDecimal totalPrice;

  @Column(name = "discount_applied")
  private BigDecimal discountApplied = BigDecimal.ZERO;

  @Column(name = "loyalty_points_used")
  private Integer loyaltyPointsUsed = 0;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt = LocalDateTime.now();

  @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<PurchaseItemEntity> items;

  public UUID getCustomerId() {
    return customerId;
  }

  public void setCustomerId(UUID customerId) {
    this.customerId = customerId;
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }

  public BigDecimal getDiscountApplied() {
    return discountApplied;
  }

  public void setDiscountApplied(BigDecimal discountApplied) {
    this.discountApplied = discountApplied;
  }

  public Integer getLoyaltyPointsUsed() {
    return loyaltyPointsUsed;
  }

  public void setLoyaltyPointsUsed(Integer loyaltyPointsUsed) {
    this.loyaltyPointsUsed = loyaltyPointsUsed;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public List<PurchaseItemEntity> getItems() {
    return items;
  }

  public void setItems(List<PurchaseItemEntity> items) {
    this.items = items;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    PurchaseEntity that = (PurchaseEntity) o;
    return Objects.equals(customerId, that.customerId)
        && Objects.equals(totalPrice, that.totalPrice)
        && Objects.equals(discountApplied, that.discountApplied)
        && Objects.equals(loyaltyPointsUsed, that.loyaltyPointsUsed)
        && Objects.equals(createdAt, that.createdAt)
        && Objects.equals(items, that.items);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        super.hashCode(),
        customerId,
        totalPrice,
        discountApplied,
        loyaltyPointsUsed,
        createdAt,
        items);
  }

  @Override
  public String toString() {
    return "PurchaseEntity{"
        + "customerId="
        + customerId
        + ", totalPrice="
        + totalPrice
        + ", discountApplied="
        + discountApplied
        + ", loyaltyPointsUsed="
        + loyaltyPointsUsed
        + ", createdAt="
        + createdAt
        + ", items="
        + items
        + '}';
  }
}
