package org.example.agile_devops_blogging_platform.services;

import org.example.agile_devops_blogging_platform.entities.Post;
import org.example.agile_devops_blogging_platform.repository.PostRepository;
import org.example.agile_devops_blogging_platform.repository.UserRepository;
import org.springframework.stereotype.Service;

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

        return postRepository.save(post);
    }
}
