package org.example.agile_devops_blogging_platform.repository;

import org.example.agile_devops_blogging_platform.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
