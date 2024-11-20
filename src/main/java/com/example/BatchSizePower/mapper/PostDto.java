package com.example.BatchSizePower.mapper;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@Accessors(chain = true)
public class PostDto {
    private long id;
    private String title;
    private String content;
    private Set<CommentDto> comments;
    private Set<UserDto> likedBy;
}
