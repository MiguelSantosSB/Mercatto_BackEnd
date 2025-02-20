package org.mercatto.mercatto_backend.service;

import org.mercatto.mercatto_backend.dto.request.StoreRequest;
import org.mercatto.mercatto_backend.dto.response.StoreResponse;

import java.util.List;

public interface StoreService {

    StoreResponse create(StoreRequest storeRequest);
    StoreResponse findById(Long id);
    List<StoreResponse> findAll();
    StoreResponse update(Long id, StoreRequest storeRequest);
    void delete(Long id);
    StoreResponse findByOwnerId(Long id);
}
