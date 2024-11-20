package com.example.BatchSizePower.repo;

import com.example.BatchSizePower.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface PostRepo extends JpaRepository<Post, Long> {
    @EntityGraph(attributePaths = {"comments", "likedBy"})
    Set<Post> findWithEverythingByIdIn(List<Long> ids);
    Set<Post> findByIdIn(List<Long> ids);
    @EntityGraph(attributePaths = {"comments", "likedBy"})
    @Query("select post from Post post")
    Set<Post> findAllWithEverything();

    @Query("select id from Post")
    Page<Long> findAllIds(Pageable pageable);
}
