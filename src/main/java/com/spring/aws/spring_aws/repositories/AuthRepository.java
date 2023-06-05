package com.spring.aws.spring_aws.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.aws.spring_aws.models.User;

public interface AuthRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByEmailIgnoreCase(String username);
}
