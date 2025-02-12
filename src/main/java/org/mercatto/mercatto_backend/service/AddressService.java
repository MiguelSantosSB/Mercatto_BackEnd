package org.mercatto.mercatto_backend.service;

import org.mercatto.mercatto_backend.dto.request.AddressRequest;
import org.mercatto.mercatto_backend.dto.response.AddressResponse;

import java.util.List;

public interface AddressService {
    AddressResponse create(AddressRequest addressRequest);
    AddressResponse findById(Long id);
    List<AddressResponse> findAll();
    AddressResponse update(Long id, AddressRequest addressRequest);
    void delete(Long id);
}
