package org.mercatto.mercatto_backend.service.impl;

import org.mercatto.mercatto_backend.dto.request.StoreRequest;
import org.mercatto.mercatto_backend.dto.response.StoreResponse;
import org.mercatto.mercatto_backend.mapper.StoreMapper;
import org.mercatto.mercatto_backend.model.AddressModel;
import org.mercatto.mercatto_backend.model.StoreModel;
import org.mercatto.mercatto_backend.model.UserModel;
import org.mercatto.mercatto_backend.repositories.AddressRepository;
import org.mercatto.mercatto_backend.repositories.StoreRepository;
import org.mercatto.mercatto_backend.repositories.UserRepository;
import org.mercatto.mercatto_backend.service.StoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository repository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final StoreMapper mapper;

    public StoreServiceImpl(StoreRepository repository, UserRepository userRepository, AddressRepository addressRepository, StoreMapper mapper) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.mapper = mapper;
    }

    @Override
    public StoreResponse create(StoreRequest request) {
        System.out.println("Address ID recebido: "+ request.getAddress());
        System.out.println("Owner ID recebido: "+ request.getOwner());
        if (request.getOwner() == null || request.getAddress() == null) {
            throw new IllegalArgumentException("IDs não podem ser nulos");
        }


        AddressModel address = addressRepository.findById(request.getAddress()).orElseThrow(() -> new RuntimeException("Address not found"));
        UserModel owner = userRepository.findById(request.getOwner()).orElseThrow(() -> new RuntimeException("Owner not found"));

        StoreModel store = mapper.toEntity(request, address, owner);
        StoreModel saved = repository.save(store);
        return mapper.toResponse(saved);
    }

    @Override
    public StoreResponse findById(Long id) {
        StoreModel store = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found"));
        return mapper.toResponse(store);
    }

    @Override
    public List<StoreResponse> findAll() {
        List<StoreModel> stores = repository.findAll();
        return stores.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public StoreResponse update(Long id, StoreRequest request) {
        StoreModel store = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        store.setName(request.getName());
        store.setDescription(request.getDescription());
        store.setCnpj(request.getCnpj());
        store.setTelephone(request.getTelephone());

        if (request.getAddress() != null ) {
            AddressModel address = addressRepository
                    .findById(request.getAddress()).orElseThrow(() -> new RuntimeException("Address not found"));
            store.setAddress(address);
        }

        if (request.getOwner() != null ) {
            UserModel owner = userRepository
                    .findById(request.getOwner()).orElseThrow(() -> new RuntimeException("Owner not found"));
            store.setOwner(owner);
        }

        StoreModel saved = repository.save(store);
        return mapper.toResponse(saved);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public StoreResponse findByOwnerId(Long ownerId) {
        StoreModel store = repository.findByOwnerId(ownerId)
                .orElseThrow(() -> new RuntimeException("Store not found"));
        return mapper.toResponse(store);
    }
}
