package org.example.agile_devops_blogging_platform.services;

import org.example.agile_devops_blogging_platform.entities.Post;
import org.example.agile_devops_blogging_platform.entities.User;
import org.example.agile_devops_blogging_platform.exception.ResourceNotFoundException;
import org.example.agile_devops_blogging_platform.exception.ValidationException;
import org.example.agile_devops_blogging_platform.repository.PostRepository;
import org.example.agile_devops_blogging_platform.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    @Test
    void getPosts_shouldReturnPagedPosts() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by("id").descending());

        Post p1 = new Post();
        p1.setId(1);

        Post p2 = new Post();
        p2.setId(2);

        Page<Post> page = new PageImpl<>(List.of(p1, p2));

        when(postRepository.findAll(pageable)).thenReturn(page);

        Page<Post> result = postService.getPosts(pageable);

        assertThat(result.getContent().get(0).getId()).isEqualTo(1);

        verify(postRepository).findAll(pageable);
    }

    @Test
    void findPostById_shouldReturnPost_whenExists() {
        Post post = new Post();
        post.setId(10);

        when(postRepository.findById(10)).thenReturn(Optional.of(post));

        Post result = postService.findPostById(10);

        assertThat(result.getId()).isEqualTo(10);
        verify(postRepository).findById(10);
    }

    @Test
    void findPostById_shouldThrowValidationException_whenIdInvalid() {
        assertThatThrownBy(() -> postService.findPostById(0))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Invalid post ID");

        verifyNoInteractions(postRepository);
    }

    @Test
    void findPostById_shouldThrowNotFound_whenMissing() {
        when(postRepository.findById(5)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> postService.findPostById(5))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Post not found with ID: 5");

        verify(postRepository).findById(5);
    }
}
