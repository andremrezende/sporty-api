package com.sporty.api.db.repository;

import com.sporty.api.db.entity.BookEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookJpaRepository extends JpaRepository<BookEntity, UUID> {}
