package org.mercatto.mercatto_backend.mapper;

import org.mercatto.mercatto_backend.dto.request.UserRequest;
import org.mercatto.mercatto_backend.dto.response.UserResponse;
import org.mercatto.mercatto_backend.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserModel insertRequestToModel(UserRequest request) {
        var response = new UserModel();
        BeanUtils.copyProperties(request, response);
        return response;
    }

    public static UserResponse modelToInsertResponse(UserModel model) {
        var response = new UserResponse();
        BeanUtils.copyProperties(model, response);

        return response;
    }



}
