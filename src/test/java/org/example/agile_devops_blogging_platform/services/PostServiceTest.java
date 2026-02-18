package org.example.agile_devops_blogging_platform.services;

import org.example.agile_devops_blogging_platform.entities.Post;
import org.example.agile_devops_blogging_platform.entities.User;
import org.example.agile_devops_blogging_platform.repository.PostRepository;
import org.example.agile_devops_blogging_platform.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PostService postService;

    @Test
    void shouldCreatePostAndAssignUser() {
        User user = new User();
        user.setId(1L);

        Post post = new Post();
        post.setTitle("Test Post");
        post.setBody("Content");
        post.setUser(user);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(postRepository.save(any(Post.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Post savedPost = postService.createPost(post);

        assertNotNull(savedPost);
        assertEquals(user, savedPost.getUser());
        assertEquals("Test Post", savedPost.getTitle());

        verify(userRepository).findById(1);
        verify(postRepository).save(post);
    }
}
