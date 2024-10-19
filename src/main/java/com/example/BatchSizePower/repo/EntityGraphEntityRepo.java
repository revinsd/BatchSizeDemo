package com.example.BatchSizePower.repo;

import com.example.BatchSizePower.entity.entityGraph.EntityGraphEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface EntityGraphEntityRepo extends JpaRepository<EntityGraphEntity, Long> {
    @EntityGraph("main")
    List<EntityGraphEntity> findAll();

    @EntityGraph("main")
    Page<EntityGraphEntity> findAll(Pageable pageable);

    @EntityGraph("main")
    Set<EntityGraphEntity> findAllByIdIn(Collection<Long> ids);

    @EntityGraph("main")
    Optional<EntityGraphEntity> findById(Long id);

    @Query("SELECT entity.id from EntityGraphEntity entity")
    Page<Long> findAllIds(Pageable pageable);

    @Query("SELECT entity.id from EntityGraphEntity entity")
    Set<Long> findAllIds();
}
