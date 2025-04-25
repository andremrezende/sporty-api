package com.sporty.api.core.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class CustomerLoyalty extends BasicModel {
  private UUID customerId;
  private int loyaltyPoints;
  private LocalDateTime lastUpdated;

  public CustomerLoyalty() {}

  public CustomerLoyalty(UUID customerId, int loyaltyPoints, LocalDateTime lastUpdated) {
    this.customerId = customerId;
    this.loyaltyPoints = loyaltyPoints;
    this.lastUpdated = lastUpdated;
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
    CustomerLoyalty that = (CustomerLoyalty) o;
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
    return "CustomerLoyalty{"
        + "customerId="
        + customerId
        + ", loyaltyPoints="
        + loyaltyPoints
        + ", lastUpdated="
        + lastUpdated
        + '}';
  }
}
