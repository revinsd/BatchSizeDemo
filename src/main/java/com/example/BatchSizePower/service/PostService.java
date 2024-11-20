package com.example.BatchSizePower.service;

import com.example.BatchSizePower.entity.Comment;
import com.example.BatchSizePower.entity.Post;
import com.example.BatchSizePower.entity.UserLike;
import com.example.BatchSizePower.mapper.PostMapper;
import com.example.BatchSizePower.repo.PostRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostRepo postRepository;
    private final PostMapper postMapper;

    @Transactional
    public void saveMainEntities() {
        log.info("Saving entities");
        postRepository.saveAll(getPosts());
    }

    @Transactional
    public void entityGraphFindByIdIn() {
        log.info("Finding by id in with entity graph");
        var ids = postRepository.findAllIds(PageRequest.of(0, 1000)).getContent();
        var posts = postRepository.findWithEverythingByIdIn(ids);
        postMapper.toDtos(posts);
    }

    @Transactional
    public void entityGraphFindAll() {
        log.info("Finding all with entity graph");
        var posts = postRepository.findAllWithEverything();
        postMapper.toDtos(posts);
    }

    @Transactional
    public void batchSizeFindByIdIn() {
        log.info("Finding by id in with batch size");
        var ids = postRepository.findAllIds(PageRequest.of(0, 1000)).getContent();
        var posts = postRepository.findByIdIn(ids);
        postMapper.toDtos(posts);
    }

    @Transactional
    public void batchSizeFindAll() {
        log.info("Finding all in with batch size");
        var posts = postRepository.findAll();
        postMapper.toDtos(posts);
    }

    private Set<Post> getPosts() {
        return IntStream.range(0, 10000)
                .mapToObj(i -> {
                            var post = new Post();
                            return post.setComments(getComments(post))
                                    .setLikedBy(getUsers(post))
                                    .setContent("content" + i)
                                    .setTitle("title" + i);
                        }
                ).collect(Collectors.toSet());
    }

    private Set<Comment> getComments(Post post) {
        return IntStream.range(0, 21)
                .mapToObj(i -> new Comment().setContent("content" + i).setPost(post))
                .collect(Collectors.toSet());
    }

    private Set<UserLike> getUsers(Post post) {
        return IntStream.range(0, 21)
                .mapToObj(i -> new UserLike().setUsername("name" + i).setLikedPost(post))
                .collect(Collectors.toSet());
    }

}
