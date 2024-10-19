package com.example.BatchSizePower.entity.entityGraph;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NamedEntityGraph(
        name = "main",
        attributeNodes = {
                @NamedAttributeNode(value = "sub1", subgraph = "sub"),
                @NamedAttributeNode(value = "sub2", subgraph = "sub"),
                @NamedAttributeNode(value = "sub3", subgraph = "sub"),
                @NamedAttributeNode(value = "sub4", subgraph = "sub"),
                @NamedAttributeNode(value = "sub5", subgraph = "sub")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "sub",
                        attributeNodes = {
                                @NamedAttributeNode(value = "sub1"),
                                @NamedAttributeNode(value = "sub2")
                        }
                )
        }
)
public class EntityGraphEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<SubEntityGraphEntity> sub1;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<SubEntityGraphEntity> sub2;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<SubEntityGraphEntity> sub3;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<SubEntityGraphEntity> sub4;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<SubEntityGraphEntity> sub5;
}
