package org.example.agile_devops_blogging_platform.dtos.postDtos;


import org.example.agile_devops_blogging_platform.entities.PostStatus;

import java.time.Instant;

public record PostDto(
        Integer id,
        String title,
        String body,
        Instant createdAt,
        Instant updatedAt,
        String excerpt,
        Instant publishedAt,
        Integer likeCount,
        Integer viewCount,
        Integer commentCount,
        PostStatus status,
        Long userId,
        String author
) {}
