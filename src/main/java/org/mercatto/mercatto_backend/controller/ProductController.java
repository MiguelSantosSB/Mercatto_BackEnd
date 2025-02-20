package org.mercatto.mercatto_backend.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.mercatto.mercatto_backend.dto.request.ProductRequest;
import org.mercatto.mercatto_backend.dto.response.ProductResponse;
import org.mercatto.mercatto_backend.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "produtos", description = "Crud de produtos")
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest request ) {
        ProductResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Long id) {
        ProductResponse response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        List<ProductResponse> response = service.findAll();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id, @RequestBody ProductRequest request ) {
        ProductResponse response = service.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponse> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<ProductResponse>> getProductsByStore(@PathVariable Long storeId) {
        List<ProductResponse> products = service.findByStoreId(storeId);
        return ResponseEntity.ok(products);
    }
}
