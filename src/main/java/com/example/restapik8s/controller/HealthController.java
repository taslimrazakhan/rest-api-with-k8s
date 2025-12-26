package com.example.restapik8s.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HealthController {
    
    @Value("${spring.application.name:rest-api-k8s}")
    private String applicationName;
    
    @Value("${server.port:8080}")
    private String serverPort;
    
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        health.put("timestamp", LocalDateTime.now());
        health.put("application", applicationName);
        health.put("port", serverPort);
        health.put("message", "Application is running successfully");
        return ResponseEntity.ok(health);
    }
    
    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> info() {
        Map<String, Object> info = new HashMap<>();
        info.put("application", applicationName);
        info.put("version", "1.0.0");
        info.put("description", "Spring Boot REST API for Kubernetes Testing");
        info.put("java.version", System.getProperty("java.version"));
        info.put("spring.profiles.active", System.getProperty("spring.profiles.active", "default"));
        info.put("build.time", LocalDateTime.now());
        return ResponseEntity.ok(info);
    }
    
    @GetMapping("/ready")
    public ResponseEntity<Map<String, Object>> ready() {
        Map<String, Object> readiness = new HashMap<>();
        readiness.put("status", "READY");
        readiness.put("timestamp", LocalDateTime.now());
        readiness.put("message", "Application is ready to serve requests");
        return ResponseEntity.ok(readiness);
    }
}