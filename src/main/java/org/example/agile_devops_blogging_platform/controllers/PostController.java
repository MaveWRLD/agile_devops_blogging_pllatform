package org.example.agile_devops_blogging_platform.controllers;

import lombok.RequiredArgsConstructor;
import org.example.agile_devops_blogging_platform.dtos.postDtos.CreatePostRequest;
import org.example.agile_devops_blogging_platform.dtos.postDtos.PostDto;
import org.example.agile_devops_blogging_platform.entities.Post;
import org.example.agile_devops_blogging_platform.mappers.PostMapper;
import org.example.agile_devops_blogging_platform.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
