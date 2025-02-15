package org.mercatto.mercatto_backend.service.impl;

import org.mercatto.mercatto_backend.dto.request.ProductRequest;
import org.mercatto.mercatto_backend.dto.response.ProductResponse;
import org.mercatto.mercatto_backend.mapper.ProductMapper;
import org.mercatto.mercatto_backend.model.ProductModel;
import org.mercatto.mercatto_backend.model.StoreModel;
import org.mercatto.mercatto_backend.repositories.ProductRepository;
import org.mercatto.mercatto_backend.repositories.StoreRepository;
import org.mercatto.mercatto_backend.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final StoreRepository storeRepository;
    private final ProductMapper mapper;

    public ProductServiceImpl(ProductRepository repository, StoreRepository storeRepository, ProductMapper mapper) {
        this.repository = repository;
        this.storeRepository = storeRepository;
        this.mapper = mapper;
    }

    @Override
    public ProductResponse create(ProductRequest request) {
        StoreModel store = storeRepository.findById(request.getStoreId())
                .orElseThrow(()->new RuntimeException("Loja não encontrada"));

        ProductModel product = mapper.toEntity(request, store);
        ProductModel saved = repository.save(product);

        store.addProduct(product);
        storeRepository.save(store);

        return mapper.toResponse(saved);
    }

    @Override
    public ProductResponse findById(Long id) {
        ProductModel product = repository.findById(id)
                .orElseThrow(()->new RuntimeException("Produto não encontrado"));
        return mapper.toResponse(product);
    }

    @Override
    public List<ProductResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {
        ProductModel product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        StoreModel store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new RuntimeException("Loja não encontrada"));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());

        if (request.getStoreId() != null) {
            StoreModel storeId = storeRepository.findById(request.getStoreId())
                    .orElseThrow(() -> new RuntimeException("Store not found"));
            product.setStore(storeId);
        }

        ProductModel saved = repository.save(product);
        return mapper.toResponse(saved);
    }

    @Override
    public void delete(Long id) {
        ProductModel product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrtado"));
        repository.delete(product);
    }
}