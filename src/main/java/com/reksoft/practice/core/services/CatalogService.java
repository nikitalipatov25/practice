package com.reksoft.practice.core.services;

import com.reksoft.practice.core.repos.CatalogRepository;
import com.reksoft.practice.core.models.CatalogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CatalogService {

    private final CatalogRepository catalogRepository;

    @Autowired
    public CatalogService(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    public Page<CatalogEntity> catalogFindAll(String searching, Pageable pageable) {
        if (searching != null && !searching.isBlank()) {
            var result = catalogRepository.findByProductNameLike("%" + searching + "%", pageable);
            return result;
        } else {
            return catalogRepository.findAll(pageable);
        }
    }

    public Optional<CatalogEntity> catalogFindById(UUID id) {
        return catalogRepository.findById(id);
    }

    public CatalogEntity catalogSaveEntity(CatalogEntity catalogEntity) {
        CatalogEntity newEntity = new CatalogEntity();
        newEntity.setId(UUID.randomUUID());
        newEntity.setProductDescription(catalogEntity.getProductDescription());
        newEntity.setProductKol(catalogEntity.getProductKol());
        newEntity.setProductName(catalogEntity.getProductName());
        newEntity.setProductPhoto(catalogEntity.getProductPhoto());
        newEntity.setProductPrice(catalogEntity.getProductPrice());
        newEntity.setCategoryType(catalogEntity.getCategoryType());
        newEntity.setCategoryName(catalogEntity.getCategoryName());
        return catalogRepository.save(newEntity);
    }

    public Optional<CatalogEntity> catalogEditEntity(UUID uuid, CatalogEntity catalogEntity) {
        Optional<CatalogEntity> result = catalogRepository.findById(uuid);
        return result
                .map(entity -> {
                    entity.setProductDescription(catalogEntity.getProductDescription());
                    entity.setProductKol(catalogEntity.getProductKol());
                    entity.setProductPhoto(catalogEntity.getProductPhoto());
                    entity.setProductPrice(catalogEntity.getProductPrice());
                    entity.setProductName(catalogEntity.getProductName());
                    entity.setProductDescription(catalogEntity.getProductDescription());
                    entity.setCategoryType(catalogEntity.getCategoryType());
                    entity.setProductDescription(catalogEntity.getProductDescription());
                    return catalogRepository.save(entity);
                });

    }

    public Optional<Boolean> catalogDeleteEntity(UUID id) {
        Optional<CatalogEntity> result = catalogRepository.findById(id);
        return result
                .map(catalogEntity -> {
                    catalogRepository.deleteById(id);
                    return true;
                });
    }

    public boolean catalogValidateEntity(CatalogEntity catalogEntity) {
        boolean result = true;
        return result;
    }
}