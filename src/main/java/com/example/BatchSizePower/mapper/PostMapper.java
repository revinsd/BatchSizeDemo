package com.example.BatchSizePower.mapper;

import com.example.BatchSizePower.entity.Post;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
    List<PostDto> toDtos(Collection<Post> posts);
}
