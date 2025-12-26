package com.example.restapik8s.config;

import com.example.restapik8s.model.Product;
import com.example.restapik8s.model.User;
import com.example.restapik8s.repository.ProductRepository;
import com.example.restapik8s.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile({"dev", "k8s"})
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Initialize sample users
        if (userRepository.count() == 0) {
            userRepository.save(new User("John Doe", "john.doe@example.com", "Engineering"));
            userRepository.save(new User("Jane Smith", "jane.smith@example.com", "Marketing"));
            userRepository.save(new User("Mike Johnson", "mike.johnson@example.com", "Sales"));
            userRepository.save(new User("Sarah Wilson", "sarah.wilson@example.com", "HR"));
            userRepository.save(new User("David Brown", "david.brown@example.com", "Engineering"));
        }
        
        // Initialize sample products
        if (productRepository.count() == 0) {
            productRepository.save(new Product("Laptop Pro", "High-performance laptop for professionals", 
                    new BigDecimal("1299.99"), "Electronics"));
            productRepository.save(new Product("Wireless Mouse", "Ergonomic wireless mouse with precision tracking", 
                    new BigDecimal("29.99"), "Electronics"));
            productRepository.save(new Product("Coffee Mug", "Premium ceramic coffee mug", 
                    new BigDecimal("12.99"), "Kitchen"));
            productRepository.save(new Product("Notebook", "High-quality spiral notebook", 
                    new BigDecimal("5.99"), "Office"));
            productRepository.save(new Product("Smartphone", "Latest smartphone with advanced features", 
                    new BigDecimal("799.99"), "Electronics"));
        }
    }
}