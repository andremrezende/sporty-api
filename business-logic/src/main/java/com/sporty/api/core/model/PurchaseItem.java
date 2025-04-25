package com.sporty.api.core.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class PurchaseItem extends BasicModel {
  private UUID bookId;
  private int quantity;
  private BigDecimal unitPrice;
  private BigDecimal discount;
  private BigDecimal finalPrice;

  public PurchaseItem() {}

  public PurchaseItem(String id) {
    super(id);
  }

  public PurchaseItem(String bookId, int quantity) {
    this.bookId = UUID.fromString(bookId);
    this.quantity = quantity;
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

  public BigDecimal getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(BigDecimal unitPrice) {
    this.unitPrice = unitPrice;
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
    PurchaseItem that = (PurchaseItem) o;
    return quantity == that.quantity
        && Objects.equals(bookId, that.bookId)
        && Objects.equals(unitPrice, that.unitPrice)
        && Objects.equals(discount, that.discount)
        && Objects.equals(finalPrice, that.finalPrice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), bookId, quantity, unitPrice, discount, finalPrice);
  }

  @Override
  public String toString() {
    return "PurchaseItem{"
        + "bookId="
        + bookId
        + ", quantity="
        + quantity
        + ", unitPrice="
        + unitPrice
        + ", discount="
        + discount
        + ", finalPrice="
        + finalPrice
        + '}';
  }
}
