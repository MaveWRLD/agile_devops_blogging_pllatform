package org.example.agile_devops_blogging_platform.services;

import org.example.agile_devops_blogging_platform.dtos.postDtos.PostDto;
import org.example.agile_devops_blogging_platform.entities.Post;
import org.example.agile_devops_blogging_platform.exception.ResourceNotFoundException;
import org.example.agile_devops_blogging_platform.exception.ValidationException;
import org.example.agile_devops_blogging_platform.repository.PostRepository;
import org.example.agile_devops_blogging_platform.repository.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;


@Service
public class PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Post createPost(Post post) {

        var user = userRepository.findById(1).orElseThrow();
        post.setUser(user);
        post.setCreatedAt(Instant.now());
        post.setPublishedAt(Instant.now());

        return postRepository.save(post);
    }

    /**
     * Get paged posts (simple latest)
     */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Cacheable(value="allPosts")
    public Page<Post> getPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    /**
     * Get a post by ID with comments
     */
    @Transactional(readOnly = true)
    @Cacheable(value = "post:detail", key = "#id")
    public Post findPostById(int id) {
        if (id <= 0) throw new ValidationException("Invalid post ID");


        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with ID: " + id));
    }
}
