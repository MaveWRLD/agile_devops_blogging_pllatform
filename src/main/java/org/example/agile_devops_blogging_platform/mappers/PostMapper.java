package org.example.agile_devops_blogging_platform.mappers;

import org.example.agile_devops_blogging_platform.dtos.postDtos.CreatePostRequest;
import org.example.agile_devops_blogging_platform.dtos.postDtos.PostDto;
import org.example.agile_devops_blogging_platform.entities.Post;
import org.mapstruct.*;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PostMapper {


    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.username", target = "author")
    PostDto toDto(Post post);

    Post toEntity(CreatePostRequest createPostRequest);
}