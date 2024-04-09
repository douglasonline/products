package com.example.Products.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestController
@RequestMapping("/custom-health")
public class CustomHealthController {

    @Autowired
    @Qualifier("diskSpaceHealthIndicator")
    private HealthIndicator healthIndicator;

    @GetMapping
    public ResponseEntity<String> getHealthStatus() {
        // Obtém o status do HealthIndicator
        Health health = healthIndicator.health();

        // Verifica se o status é "UP"
        if (health.getStatus().equals(Status.UP)) {
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Serviço não disponível", HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

}