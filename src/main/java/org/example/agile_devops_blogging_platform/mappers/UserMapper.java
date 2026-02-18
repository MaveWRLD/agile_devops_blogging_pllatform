package org.example.agile_devops_blogging_platform.mappers;


import org.example.agile_devops_blogging_platform.dtos.userDtos.CreateUserRequest;
import org.example.agile_devops_blogging_platform.dtos.userDtos.UserDto;
import org.example.agile_devops_blogging_platform.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(CreateUserRequest createUserRequest);
}
