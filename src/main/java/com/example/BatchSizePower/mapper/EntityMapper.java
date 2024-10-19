package com.example.BatchSizePower.mapper;

import com.example.BatchSizePower.entity.batchSize.BatchSizeEntity;
import com.example.BatchSizePower.entity.entityGraph.EntityGraphEntity;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface EntityMapper {
    MainEntityDto toDto(EntityGraphEntity entity);

    MainEntityDto toDto(BatchSizeEntity entity);

    Set<EntityGraphEntity> toEntityGraphEntities(Collection<MainEntityDto> dto);

    Set<BatchSizeEntity> toBatchSizeEntities(Collection<MainEntityDto> dto);
}
