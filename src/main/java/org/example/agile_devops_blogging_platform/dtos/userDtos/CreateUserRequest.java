package org.example.agile_devops_blogging_platform.dtos.userDtos;

import lombok.Data;

@Data
public class CreateUserRequest {

    private String username;

    private String email;

    private String password;
}