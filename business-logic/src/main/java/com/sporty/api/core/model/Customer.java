package com.sporty.api.core.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Customer extends BasicModel {
  private String name;
  private String email;
  private String phone;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public Customer() {}

  public Customer(String id, String name) {
    super(id);
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Customer customer = (Customer) o;
    return Objects.equals(name, customer.name)
        && Objects.equals(email, customer.email)
        && Objects.equals(phone, customer.phone)
        && Objects.equals(createdAt, customer.createdAt)
        && Objects.equals(updatedAt, customer.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), name, email, phone, createdAt, updatedAt);
  }

  @Override
  public String toString() {
    return "Customer{"
        + "name='"
        + name
        + '\''
        + ", email='"
        + email
        + '\''
        + ", phone='"
        + phone
        + '\''
        + ", createdAt="
        + createdAt
        + ", updatedAt="
        + updatedAt
        + '}';
  }
}
