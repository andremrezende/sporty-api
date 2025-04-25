package com.sporty.api.db.mapper;

import com.sporty.api.core.model.Book;
import com.sporty.api.db.entity.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookMapper {
  BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

  Book toModel(BookEntity entity);

  BookEntity toEntity(Book model);
}
