package org.example.agile_devops_blogging_platform.repository;

import org.example.agile_devops_blogging_platform.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String email);

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
