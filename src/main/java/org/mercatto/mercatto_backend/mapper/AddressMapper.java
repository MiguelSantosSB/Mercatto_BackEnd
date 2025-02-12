package org.mercatto.mercatto_backend.mapper;

import org.mercatto.mercatto_backend.dto.request.AddressRequest;
import org.mercatto.mercatto_backend.dto.response.AddressResponse;
import org.mercatto.mercatto_backend.model.AddressModel;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressModel toEntity(AddressRequest request) {
        AddressModel address = new AddressModel();
        address.setStreet(request.getStreet());
        address.setNumber(request.getNumber());
        address.setNeighborhood(request.getNeighborhood());
        address.setCity(request.getCity());
        address.setState(request.getState());
        address.setCep(request.getCep());
        return address;
    }

    public AddressResponse toResponse(AddressModel address) {
        AddressResponse response = new AddressResponse();
        response.setId(address.getId());
        response.setStreet(address.getStreet());
        response.setNumber(address.getNumber());
        response.setNeighborhood(address.getNeighborhood());
        response.setCity(address.getCity());
        response.setState(address.getState());
        response.setCep(address.getCep());
        return response;
    }
}
