package com.sporty.api.core.model;

import java.util.Objects;

public abstract class BasicModel {
  private String id;

  public BasicModel(String id) {
    this.id = id;
  }

  public BasicModel() {}

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BasicModel that = (BasicModel) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "BasicModel{" + "id='" + id + '\'' + '}';
  }
}
