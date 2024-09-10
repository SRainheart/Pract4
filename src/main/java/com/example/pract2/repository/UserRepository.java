package com.example.pract2.repository;

import com.example.pract2.model.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Long> {
    Users findByUsername(String username);
    boolean existsByUsername(String username);
}
