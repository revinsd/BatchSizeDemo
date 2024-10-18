package com.example.BatchSizePower.repo;

import com.example.BatchSizePower.entity.batchSize.BatchSizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface BatchSizeEntityRepo extends JpaRepository<BatchSizeEntity, Long> {

    @Query("select entity.id from BatchSizeEntity entity")
    Set<Long> findAllIds();
}
