package com.example.restapik8s.repository;

import com.example.restapik8s.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
    
    List<User> findByDepartment(String department);
    
    List<User> findByActive(Boolean active);
    
    @Query("SELECT u FROM User u WHERE u.name LIKE %:name%")
    List<User> findByNameContaining(@Param("name") String name);
    
    @Query("SELECT u FROM User u WHERE u.department = :department AND u.active = :active")
    List<User> findByDepartmentAndActive(@Param("department") String department, @Param("active") Boolean active);
}