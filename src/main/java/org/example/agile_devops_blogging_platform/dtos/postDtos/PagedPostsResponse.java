package org.example.agile_devops_blogging_platform.dtos.postDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.agile_devops_blogging_platform.entities.Post;

import java.util.List;

@Data
@AllArgsConstructor
public class PagedPostsResponse {
    private List<Post> posts;
    private int page;
    private int size;
    private long total;
    private int totalPages;
    private boolean hasPrevious;
    private boolean hasNext;
}
