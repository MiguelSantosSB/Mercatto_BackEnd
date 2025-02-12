package org.mercatto.mercatto_backend.service.impl;

import org.mercatto.mercatto_backend.dto.request.AddressRequest;
import org.mercatto.mercatto_backend.dto.response.AddressResponse;
import org.mercatto.mercatto_backend.mapper.AddressMapper;
import org.mercatto.mercatto_backend.model.AddressModel;
import org.mercatto.mercatto_backend.repositories.AddressRepository;
import org.mercatto.mercatto_backend.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;
    private final AddressMapper mapper;

    public AddressServiceImpl(AddressRepository repository, AddressMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AddressResponse create(AddressRequest request) {
        AddressModel address = mapper.toEntity(request);
        AddressModel saved = repository.save(address);
        return mapper.toResponse(saved);
    }

    @Override
    public AddressResponse findById(Long id) {
        AddressModel address = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));
        return mapper.toResponse(address);
    }

    @Override
    public List<AddressResponse> findAll() {
        List<AddressModel> addresses = repository.findAll();
        return addresses.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AddressResponse update(Long id, AddressRequest request) {
        AddressModel address = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        address.setStreet(request.getStreet());
        address.setNumber(request.getNumber());
        address.setNeighborhood(request.getNeighborhood());
        address.setCity(request.getCity());
        address.setState(request.getState());
        address.setCep(request.getCep());

        AddressModel updated = repository.save(address);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
