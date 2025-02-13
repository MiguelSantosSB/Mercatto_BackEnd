package org.mercatto.mercatto_backend.dto;

import org.mercatto.mercatto_backend.service.impl.InicializationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final InicializationService inicializationService;

    public DataInitializer(InicializationService inicializationService) {
        this.inicializationService = inicializationService;
    }

    public void run(String... args) throws Exception {
        inicializationService.inicializeRoles();

        inicializationService.inicializeAdminUser();
    }
}
