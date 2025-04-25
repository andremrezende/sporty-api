package com.sporty.api.db.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "purchase_item")
public class PurchaseItemEntity extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "purchase_id", nullable = false)
  private PurchaseEntity purchase;

  @Column(name = "book_id", nullable = false)
  private UUID bookId;

  @Column(nullable = false)
  private int quantity;

  @Column private BigDecimal discount = BigDecimal.ZERO;

  @Column(name = "final_price", nullable = false)
  private BigDecimal finalPrice;

  public PurchaseEntity getPurchase() {
    return purchase;
  }

  public void setPurchase(PurchaseEntity purchase) {
    this.purchase = purchase;
  }

  public UUID getBookId() {
    return bookId;
  }

  public void setBookId(UUID bookId) {
    this.bookId = bookId;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public BigDecimal getDiscount() {
    return discount;
  }

  public void setDiscount(BigDecimal discount) {
    this.discount = discount;
  }

  public BigDecimal getFinalPrice() {
    return finalPrice;
  }

  public void setFinalPrice(BigDecimal finalPrice) {
    this.finalPrice = finalPrice;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    PurchaseItemEntity that = (PurchaseItemEntity) o;
    return quantity == that.quantity && Objects.equals(purchase, that.purchase) && Objects.equals(bookId, that.bookId) && Objects.equals(discount, that.discount) && Objects.equals(finalPrice, that.finalPrice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), purchase, bookId, quantity, discount, finalPrice);
  }

  @Override
  public String toString() {
    return "PurchaseItemEntity{" +
            "purchase=" + purchase +
            ", bookId=" + bookId +
            ", quantity=" + quantity +
            ", discount=" + discount +
            ", finalPrice=" + finalPrice +
            '}';
  }
}
