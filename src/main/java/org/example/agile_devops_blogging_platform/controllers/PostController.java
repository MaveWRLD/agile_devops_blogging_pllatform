package org.example.agile_devops_blogging_platform.controllers;

import lombok.RequiredArgsConstructor;
import org.example.agile_devops_blogging_platform.dtos.postDtos.CreatePostRequest;
import org.example.agile_devops_blogging_platform.dtos.postDtos.PagedPostsResponse;
import org.example.agile_devops_blogging_platform.dtos.postDtos.PostDto;
import org.example.agile_devops_blogging_platform.entities.Post;
import org.example.agile_devops_blogging_platform.mappers.PostMapper;
import org.example.agile_devops_blogging_platform.services.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PostDto> createPost(@RequestBody CreatePostRequest request) {

        Post post = postMapper.toEntity(request);

        var createdPost = postService.createPost(post);

        return ResponseEntity.ok(postMapper.toDto(createdPost));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable Integer id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build();
        }

        var post = postService.findPostById(id);

        return ResponseEntity.ok(postMapper.toDto(post));
    }

    @GetMapping
    public ResponseEntity<PagedPostsResponse> getAllPosts(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "12") int size,
            @RequestParam(required = false, defaultValue = "createdAt") String sortBy,
            @RequestParam(required = false, defaultValue = "desc") String sortDir
    ) {
        PostPagination result = getPostpagination(page, size, sortBy, sortDir);


        List<Post> posts = result.pagedPost().getContent();

        PagedPostsResponse pagedResponse = new PagedPostsResponse(
                posts, result.page(), result.size(), result.total(), result.totalPages(), result.hasPrevious(), result.hasNext()
        );

        return ResponseEntity.ok(pagedResponse);
    }

    private PostPagination getPostpagination(int page, int size, String sortBy, String sortDir) {
        page = Math.max(page, 0);
        size = Math.max(size, 1);

        Sort sort =
                sortDir.equalsIgnoreCase("ASC") ?
                        Sort.by(sortBy).ascending() :
                        Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(
                page,
                size,
                sort
        );

        Page<Post> pagedPost = postService.getPosts(pageable);

        long total = pagedPost.getTotalElements();
        int totalPages = pagedPost.getTotalPages();
        boolean hasNext = pagedPost.hasNext();
        boolean hasPrevious = pagedPost.hasPrevious();
        return new PostPagination(page, size, pagedPost, total, totalPages, hasNext, hasPrevious);
    }

    private record PostPagination(int page, int size, Page<Post> pagedPost, long total, int totalPages, boolean hasNext, boolean hasPrevious) {
    }
}
