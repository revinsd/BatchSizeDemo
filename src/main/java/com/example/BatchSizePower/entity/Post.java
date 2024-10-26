package com.example.BatchSizePower.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.BatchSize;

import java.util.Set;

@Entity
@Getter
@Setter
@Accessors(chain = true)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String content;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @BatchSize(size = 1000)
    private Set<Comment> comments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "likedPost", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @BatchSize(size = 1000)
    private Set<UserLike> likedBy;
}
