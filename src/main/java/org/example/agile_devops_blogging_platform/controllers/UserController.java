package org.example.agile_devops_blogging_platform.controllers;

import lombok.RequiredArgsConstructor;
import org.example.agile_devops_blogging_platform.dtos.userDtos.CreateUserRequest;
import org.example.agile_devops_blogging_platform.dtos.userDtos.UserDto;
import org.example.agile_devops_blogging_platform.mappers.UserMapper;
import org.example.agile_devops_blogging_platform.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserDto> registerUser(
            @RequestBody CreateUserRequest request) {

        var user = userService.createUser(userMapper.toEntity(request));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userMapper.toDto(user));
    }
}
