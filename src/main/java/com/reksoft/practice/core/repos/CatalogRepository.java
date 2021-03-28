package com.reksoft.practice.core.repos;

import com.reksoft.practice.core.models.CatalogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CatalogRepository extends JpaRepository<CatalogEntity, UUID> { }
