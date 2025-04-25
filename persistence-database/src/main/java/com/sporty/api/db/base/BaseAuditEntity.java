package com.sporty.api.db.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.ZonedDateTime;
import java.util.Objects;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public abstract class BaseAuditEntity extends BaseEntity {

  @Column(name = "created_at", nullable = false, updatable = false)
  @CreationTimestamp
  private ZonedDateTime createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private ZonedDateTime updatedAt;

  @Column(name = "creation_user_id", nullable = false)
  private String creationUserId;

  @Column(name = "mod_user_id")
  private String modUserId;

  public ZonedDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(ZonedDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public ZonedDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(ZonedDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getCreationUserId() {
    return creationUserId;
  }

  public void setCreationUserId(String creationUserId) {
    this.creationUserId = creationUserId;
  }

  public String getModUserId() {
    return modUserId;
  }

  public void setModUserId(String modUserId) {
    this.modUserId = modUserId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    BaseAuditEntity that = (BaseAuditEntity) o;
    return Objects.equals(createdAt, that.createdAt)
        && Objects.equals(updatedAt, that.updatedAt)
        && Objects.equals(creationUserId, that.creationUserId)
        && Objects.equals(modUserId, that.modUserId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), createdAt, updatedAt, creationUserId, modUserId);
  }

  @Override
  public String toString() {
    return String.format(
        "%s{id=%s"
            + ", createdAt=%s"
            + ", updatedAt=%s"
            + ", creationUserId='%s'"
            + ", modUserId='%s'"
            + "}",
        getClass().getSimpleName(), getId(), createdAt, updatedAt, creationUserId, modUserId);
  }
}
