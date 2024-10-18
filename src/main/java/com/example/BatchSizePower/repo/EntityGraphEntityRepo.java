package com.example.BatchSizePower.repo;

import com.example.BatchSizePower.entity.entityGraph.EntityGraphEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface EntityGraphEntityRepo extends JpaRepository<EntityGraphEntity, Long> {
    @EntityGraph(attributePaths = {
            "sub1",
            "sub2",
            "sub3",
            "sub4",
            "sub5",
            "sub6",
            "sub7",
            "sub8",
            "sub9",
            "sub10"
    })
    List<EntityGraphEntity> findAll();

    @EntityGraph(attributePaths = {
            "sub1",
            "sub2",
            "sub3",
            "sub4",
            "sub5",
            "sub6",
            "sub7",
            "sub8",
            "sub9",
            "sub10"
    })
    Page<EntityGraphEntity> findAll(Pageable pageable);

    @Query("SELECT entity.id from EntityGraphEntity entity")
    Page<Long> findAllIds(Pageable pageable);

    @Query("SELECT entity.id from EntityGraphEntity entity")
    Set<Long> findAllIds();

    @EntityGraph(attributePaths = {
            "sub1",
            "sub2",
            "sub3",
            "sub4",
            "sub5",
            "sub6",
            "sub7",
            "sub8",
            "sub9",
            "sub10"
    })
    Set<EntityGraphEntity> findAllByIdIn(Set<Long> ids);

    @EntityGraph(attributePaths = {
            "sub1",
            "sub2",
            "sub3",
            "sub4",
            "sub5",
            "sub6",
            "sub7",
            "sub8",
            "sub9",
            "sub10"
    })
    Optional<EntityGraphEntity> findById(Long id);
}
