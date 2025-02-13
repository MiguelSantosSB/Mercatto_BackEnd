package org.mercatto.mercatto_backend.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.mercatto.mercatto_backend.dto.request.StoreRequest;
import org.mercatto.mercatto_backend.dto.response.StoreResponse;
import org.mercatto.mercatto_backend.service.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Lojas", description = "Crud de loja")
@RequestMapping("/store")
public class StoreController {

    private final  StoreService service;

    public StoreController(StoreService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<StoreResponse> create(@RequestBody StoreRequest request) {
        StoreResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreResponse> findById(@PathVariable Long id) {
        StoreResponse response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<StoreResponse>> findAll() {
        List<StoreResponse> response = service.findAll();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreResponse> update(@PathVariable Long id, @RequestBody StoreRequest request) {
        StoreResponse response = service.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StoreResponse> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}