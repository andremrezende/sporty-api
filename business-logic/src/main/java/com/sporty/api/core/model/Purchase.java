package com.sporty.api.core.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Purchase extends BasicModel {
  private UUID customerId;
  private BigDecimal totalPrice;
  private BigDecimal discountApplied;
  private Integer loyaltyPointsUsed;
  private LocalDateTime createdAt;
  private List<PurchaseItem> items;

  public Purchase() {}

  public Purchase(String id) {
    super(id);
  }

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

  public List<PurchaseItem> getItems() {
    return items;
  }

  public void setItems(List<PurchaseItem> items) {
    this.items = items;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Purchase purchase = (Purchase) o;
    return Objects.equals(customerId, purchase.customerId)
        && Objects.equals(totalPrice, purchase.totalPrice)
        && Objects.equals(discountApplied, purchase.discountApplied)
        && Objects.equals(loyaltyPointsUsed, purchase.loyaltyPointsUsed)
        && Objects.equals(createdAt, purchase.createdAt)
        && Objects.equals(items, purchase.items);
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
    return "Purchase{"
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
