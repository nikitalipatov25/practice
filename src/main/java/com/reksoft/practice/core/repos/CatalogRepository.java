package com.reksoft.practice.core.repos;

import com.reksoft.practice.core.models.CatalogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CatalogRepository extends JpaRepository<CatalogEntity, UUID> {
    Page<CatalogEntity> findByProductNameLike(String productName, Pageable pageable);
}
