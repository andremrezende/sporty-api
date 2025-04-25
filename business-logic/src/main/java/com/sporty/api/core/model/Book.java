package com.sporty.api.core.model;

import java.util.Objects;

public class Book extends BasicModel {
  private String title;
  private BookType type;
  private double basePrice;
  private int quantity;

  public Book() {
    super();
  }

  public Book(String id, String title, BookType type, double basePrice) {
    super(id);
    this.title = title;
    this.type = type;
    this.basePrice = basePrice;
  }

  public Book(String id, String title, BookType type, double basePrice, int quantity) {
    super(id);
    this.title = title;
    this.type = type;
    this.basePrice = basePrice;
    this.quantity = quantity;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public BookType getType() {
    return type;
  }

  public void setType(BookType type) {
    this.type = type;
  }

  public double getBasePrice() {
    return basePrice;
  }

  public void setBasePrice(double basePrice) {
    this.basePrice = basePrice;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Book book = (Book) o;
    return Double.compare(book.basePrice, basePrice) == 0
        && Objects.equals(title, book.title)
        && Objects.equals(type, book.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), title, type, basePrice);
  }

  @Override
  public String toString() {
    return "Book{"
        + "title='"
        + title
        + '\''
        + ", type="
        + type
        + ", basePrice="
        + basePrice
        + ", quantity="
        + quantity
        + '}';
  }
}
