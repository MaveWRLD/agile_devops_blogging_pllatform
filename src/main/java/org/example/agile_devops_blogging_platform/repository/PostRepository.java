package org.example.agile_devops_blogging_platform.repository;

import org.example.agile_devops_blogging_platform.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

/**
 * Repository interface for Post CRUD operations.
 * Abstracts the data access layer from business logic.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{
}

