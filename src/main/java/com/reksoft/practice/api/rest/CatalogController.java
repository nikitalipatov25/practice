package com.reksoft.practice.api.rest;

import com.reksoft.practice.core.models.CatalogEntity;
import com.reksoft.practice.core.services.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(allowedHeaders = {
        "Access-Control-Allow-Origin",
        "Access-Control-Allow-Headers",
        "Access-Control-Allow-Methods",
        "Content-Type"
},
        methods = { RequestMethod.POST,RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT })
@Transactional
@RestController
@RequestMapping(value = "/catalog")
public class CatalogController {

    private final CatalogService catalogService;

    @Autowired
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    // this method is shit
    @GetMapping()
    public ResponseEntity<Page<CatalogEntity>> getCatalog(@RequestParam(name = "searching", required = false)String searching, Pageable pageable) {
        Page<CatalogEntity> catalogEntityList = catalogService.catalogFindAll(searching, pageable);
        return ResponseEntity.ok(catalogEntityList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatalogEntity> getCatalogItem(@PathVariable(name = "id") UUID id) {
        Optional<CatalogEntity> result = catalogService.catalogFindById(id);
        return result
                .map(entity -> ResponseEntity.ok(entity))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<CatalogEntity> createCatalogItem(@RequestBody CatalogEntity catalogEntity) {
        boolean result = catalogService.catalogValidateEntity(catalogEntity);
        if (result) {
           CatalogEntity resSave = catalogService.catalogSaveEntity(catalogEntity);
           return ResponseEntity.ok(resSave);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CatalogEntity> editCatalogItem(@PathVariable(name = "id")UUID id, @RequestBody CatalogEntity catalogEntity) {
        boolean result = catalogService.catalogValidateEntity(catalogEntity);
        if (result) {
            Optional<CatalogEntity> resEdit = catalogService.catalogEditEntity(id, catalogEntity);
            return resEdit
                    .map(entity -> ResponseEntity.ok(entity))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCatalogItem(@PathVariable(name = "id") UUID id) {
        Optional<Boolean> result = catalogService.catalogDeleteEntity(id);
        return result
                .map(e -> ResponseEntity.noContent().build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
