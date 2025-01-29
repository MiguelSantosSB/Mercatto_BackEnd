package org.mercatto.mercatto_backend.dto.response;

public class ResponseDTO {

    String email;
    String token;

    public ResponseDTO() {
    }

    public ResponseDTO(String email, String token) {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
