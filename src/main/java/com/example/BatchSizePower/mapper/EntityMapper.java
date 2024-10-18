package com.example.BatchSizePower.mapper;

import com.example.BatchSizePower.entity.batchSize.BatchSizeEntity;
import com.example.BatchSizePower.entity.entityGraph.EntityGraphEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntityMapper {
    MainEntityDto map(EntityGraphEntity entity);
    MainEntityDto map(BatchSizeEntity entity);
}
