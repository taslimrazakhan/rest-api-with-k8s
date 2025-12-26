package com.example.restapik8s.repository;

import com.example.restapik8s.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    List<Product> findByCategory(String category);
    
    List<Product> findByAvailable(Boolean available);
    
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
    
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
    List<Product> findByNameContaining(@Param("name") String name);
    
    @Query("SELECT p FROM Product p WHERE p.category = :category AND p.available = :available")
    List<Product> findByCategoryAndAvailable(@Param("category") String category, @Param("available") Boolean available);
    
    @Query("SELECT p FROM Product p WHERE p.price <= :maxPrice ORDER BY p.price ASC")
    List<Product> findProductsUnderPrice(@Param("maxPrice") BigDecimal maxPrice);
}