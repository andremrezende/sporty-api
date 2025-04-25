package com.sporty.api.db.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "book")
public class BookEntity extends BaseEntity implements Serializable {
  @Column(nullable = false, unique = true)
  private String title;

  @Column(nullable = false)
  private String type;

  @Column(name = "base_price", nullable = false)
  private BigDecimal basePrice;

  @Column(nullable = false)
  private int quantity;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt = LocalDateTime.now();

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public BigDecimal getBasePrice() {
    return basePrice;
  }

  public void setBasePrice(BigDecimal basePrice) {
    this.basePrice = basePrice;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    BookEntity that = (BookEntity) o;
    return quantity == that.quantity
        && Objects.equals(title, that.title)
        && Objects.equals(type, that.type)
        && Objects.equals(basePrice, that.basePrice)
        && Objects.equals(createdAt, that.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), title, type, basePrice, quantity, createdAt);
  }

  @Override
  public String toString() {
    return "BookEntity{"
        + "title='"
        + title
        + '\''
        + ", type='"
        + type
        + '\''
        + ", basePrice="
        + basePrice
        + ", quantity="
        + quantity
        + ", createdAt="
        + createdAt
        + '}';
  }
}
