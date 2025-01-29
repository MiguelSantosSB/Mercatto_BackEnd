package org.mercatto.mercatto_backend.exeption;

public class DataAlreadyregisteredException extends RuntimeException {
    public DataAlreadyregisteredException(String message) {
        super(message);
    }
}
