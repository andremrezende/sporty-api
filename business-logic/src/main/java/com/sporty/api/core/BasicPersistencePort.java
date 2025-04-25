package com.sporty.api.core;

import com.sporty.api.core.model.BasicModel;
import java.util.List;
import java.util.UUID;

public interface BasicPersistencePort<T extends BasicModel> {

  T save(T entity);

  T update(T entity);

  T delete(T entity);

  T findById(UUID id);

  List<T> findAll();
}
