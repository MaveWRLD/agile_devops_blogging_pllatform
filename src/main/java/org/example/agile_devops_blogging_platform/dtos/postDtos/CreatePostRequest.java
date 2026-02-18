package org.example.agile_devops_blogging_platform.dtos.postDtos;

import lombok.Data;

@Data
public class CreatePostRequest {
    private String title;

    private String body;

    private int userId;

    private String status;
}