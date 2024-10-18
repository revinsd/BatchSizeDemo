package com.example.BatchSizePower.service;

import com.example.BatchSizePower.entity.batchSize.BatchSizeEntity;
import com.example.BatchSizePower.entity.batchSize.SubBatchSizeEntity;
import com.example.BatchSizePower.entity.entityGraph.EntityGraphEntity;
import com.example.BatchSizePower.entity.entityGraph.SubEntityGraphEntity;
import com.example.BatchSizePower.mapper.EntityMapper;
import com.example.BatchSizePower.repo.BatchSizeEntityRepo;
import com.example.BatchSizePower.repo.EntityGraphEntityRepo;
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
public class EntityService {
    private final EntityGraphEntityRepo entityGraphEntityRepo;
    private final BatchSizeEntityRepo batchSizeEntityRepo;
    private final EntityMapper entityMapper;

    private static final String NAME_TEMPLATE = "NAME_%s";

    @Transactional
    public void saveMainEntities(int mainEntitiesCount, int subEntitiesCount) {
        saveEntityGraphEntities(mainEntitiesCount, subEntitiesCount);
        saveBatchSizeEntities(mainEntitiesCount, subEntitiesCount);
    }

    @Transactional
    public void deleteAllEntities() {
        log.info("Deleting all entities");
        entityGraphEntityRepo.deleteAll();
        batchSizeEntityRepo.deleteAll();
    }

    @Transactional
    public void processEntityGraphEntitiesOneByOne(){
        log.info("processEntityGraphEntitiesOneByOne");
        var ids = entityGraphEntityRepo.findAllIds();
        ids.forEach(id -> {
            var entity = entityGraphEntityRepo.findById(id).orElseThrow();
            entityMapper.map(entity);
        });
    }

    @Transactional
    public void processEntityGraphEntities() {
        log.info("processEntityGraphEntities");
        var entityGraphEntities = entityGraphEntityRepo.findAll();
        entityGraphEntities.forEach(entityMapper::map);
    }

    @Transactional
    public void processEntityGraphEntityPagesSeparate(){
        log.info("processEntityGraphEntityPagesSeparate");
        var idsPage = entityGraphEntityRepo.findAllIds(PageRequest.of(0, 10));
        entityGraphEntityRepo.findAllByIdIn(Set.copyOf(idsPage.getContent()))
            .forEach(entityMapper::map);
        while(idsPage.hasNext()){
            idsPage= entityGraphEntityRepo.findAllIds(idsPage.nextPageable());
            entityGraphEntityRepo.findAllByIdIn(Set.copyOf(idsPage.getContent()))
                    .forEach(entityMapper::map);
        }
    }

    @Transactional
    public void processEntityGraphEntitySinglePageSeparate(){
        log.info("processEntityGraphEntitySinglePageSeparate");
        var idsPage = entityGraphEntityRepo.findAllIds(PageRequest.of(0, 10));
        entityGraphEntityRepo.findAllByIdIn(Set.copyOf(idsPage.getContent()))
                .forEach(entityMapper::map);
    }

    @Transactional
    public void processEntityGraphEntityPages() {
        log.info("processEntityGraphEntityPages");
        var page = entityGraphEntityRepo.findAll(PageRequest.of(0, 10));
        page.forEach(entityMapper::map);
        while (page.hasNext()) {
            page = entityGraphEntityRepo.findAll(page.nextPageable());
            page.forEach(entityMapper::map);
        }
    }

    @Transactional
    public void processEntityGraphEntitySinglePage() {
        log.info("processEntityGraphEntitySinglePage");
        var page = entityGraphEntityRepo.findAll(PageRequest.of(0, 10));
        page.forEach(entityMapper::map);
    }

    @Transactional
    public void processBatchSizeEntitiesOneByOne(){
        log.info("processBatchSizeEntitiesOneByOne");
        var ids = batchSizeEntityRepo.findAllIds();
        ids.forEach(id -> {
            var entity = batchSizeEntityRepo.findById(id).orElseThrow();
            entityMapper.map(entity);
        });
    }

    @Transactional
    public void processBatchSizeEntities() {
        log.info("processBatchSizeEntities");
        var batchSizeEntities = batchSizeEntityRepo.findAll();
        batchSizeEntities
                .forEach(entityMapper::map);
    }

    @Transactional
    public void processBatchSizeEntityPages() {
        log.info("processBatchSizeEntityPages");
        var page = batchSizeEntityRepo.findAll(PageRequest.of(0, 10));
        page.forEach(entityMapper::map);
        while (page.hasNext()) {
            page = batchSizeEntityRepo.findAll(page.nextPageable());
            page.forEach(entityMapper::map);
        }
    }

    @Transactional
    public void processBatchSizeEntitiesSinglePage() {
        log.info("processBatchSizeEntitiesSinglePage");
        var page = batchSizeEntityRepo.findAll(PageRequest.of(0, 10));
        page.forEach(entityMapper::map);
    }

    private void saveEntityGraphEntities(int mainEntitiesCount, int subEntitiesCount) {
        var mainEntities = IntStream.range(0, mainEntitiesCount)
                .mapToObj(i -> getEntityGraphEntity(subEntitiesCount))
                .collect(Collectors.toSet());
        entityGraphEntityRepo.saveAllAndFlush(mainEntities);
    }

    private void saveBatchSizeEntities(int mainEntitiesCount, int subEntitiesCount) {
        var mainEntities = IntStream.range(0, mainEntitiesCount)
                .mapToObj(i -> getBatchSizeEntity(subEntitiesCount))
                .collect(Collectors.toSet());
        batchSizeEntityRepo.saveAllAndFlush(mainEntities);
    }

    private EntityGraphEntity getEntityGraphEntity(int subEntitiesCount) {
        return new EntityGraphEntity()
                .setSub1(getSubEntityGraphEntities(subEntitiesCount))
                .setSub2(getSubEntityGraphEntities(subEntitiesCount))
                .setSub3(getSubEntityGraphEntities(subEntitiesCount))
                .setSub4(getSubEntityGraphEntities(subEntitiesCount))
                .setSub5(getSubEntityGraphEntities(subEntitiesCount))
                .setSub6(getSubEntityGraphEntities(subEntitiesCount))
                .setSub7(getSubEntityGraphEntities(subEntitiesCount))
                .setSub8(getSubEntityGraphEntities(subEntitiesCount))
                .setSub9(getSubEntityGraphEntities(subEntitiesCount))
                .setSub10(getSubEntityGraphEntities(subEntitiesCount));
    }

    private BatchSizeEntity getBatchSizeEntity(int subEntitiesCount) {
        return new BatchSizeEntity()
                .setSub1(getSubBatchSizeEntities(subEntitiesCount))
                .setSub2(getSubBatchSizeEntities(subEntitiesCount))
                .setSub3(getSubBatchSizeEntities(subEntitiesCount))
                .setSub4(getSubBatchSizeEntities(subEntitiesCount))
                .setSub5(getSubBatchSizeEntities(subEntitiesCount))
                .setSub6(getSubBatchSizeEntities(subEntitiesCount))
                .setSub7(getSubBatchSizeEntities(subEntitiesCount))
                .setSub8(getSubBatchSizeEntities(subEntitiesCount))
                .setSub9(getSubBatchSizeEntities(subEntitiesCount))
                .setSub10(getSubBatchSizeEntities(subEntitiesCount));
    }

    private Set<SubEntityGraphEntity> getSubEntityGraphEntities(int subEntitiesCount) {
        return IntStream.range(0, subEntitiesCount)
                .mapToObj(this::getSubEntityGraphEntity)
                .collect(Collectors.toSet());
    }

    private Set<SubBatchSizeEntity> getSubBatchSizeEntities(int subEntitiesCount) {
        return IntStream.range(0, subEntitiesCount)
                .mapToObj(this::getSubBatchSizeEntity)
                .collect(Collectors.toSet());
    }

    private SubEntityGraphEntity getSubEntityGraphEntity(int index) {
        return new SubEntityGraphEntity()
                .setName(String.format(NAME_TEMPLATE, index));
    }

    private SubBatchSizeEntity getSubBatchSizeEntity(int index) {
        return new SubBatchSizeEntity()
                .setName(String.format(NAME_TEMPLATE, index));
    }
}
