package com.example.BatchSizePower.service;

import com.example.BatchSizePower.mapper.EntityMapper;
import com.example.BatchSizePower.mapper.MainEntityDto;
import com.example.BatchSizePower.mapper.SubEntityDto;
import com.example.BatchSizePower.mapper.SubSubEntityDto;
import com.example.BatchSizePower.repo.BatchSizeEntityRepo;
import com.example.BatchSizePower.repo.EntityGraphEntityRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    private static final String NAME = "NAME";

    @Transactional
    public void saveMainEntities(int mainEntitiesCount, int subEntitiesCount, int subSubEntitiesCount) {
        log.info("Saving entities");
        var currentEntityGraphEntitiesCount = (int) entityGraphEntityRepo.count();
        var currentBatchSizeEntitiesCount = (int) batchSizeEntityRepo.count();
        saveEntityGraphEntities(
                currentEntityGraphEntitiesCount == 0
                        ? mainEntitiesCount
                        : mainEntitiesCount - currentEntityGraphEntitiesCount,
                subEntitiesCount,
                subSubEntitiesCount
        );
        saveBatchSizeEntities(
                currentBatchSizeEntitiesCount == 0
                        ? mainEntitiesCount
                        : mainEntitiesCount - currentBatchSizeEntitiesCount,
                subEntitiesCount,
                subSubEntitiesCount
        );
    }

    @Transactional
    public void deleteAllEntities() {
        log.info("Deleting all entities");
        entityGraphEntityRepo.deleteAll();
        batchSizeEntityRepo.deleteAll();
    }

    @Transactional
    public void processEntityGraphFindById(){
        log.info("processEntityGraphFindById");
        var id = entityGraphEntityRepo.findAllIds(PageRequest.of(0,1)).iterator().next();
        entityMapper.toDto(entityGraphEntityRepo.findById(id).orElseThrow());
    }

    @Transactional
    public void processEntityGraphEntitiesOneByOne() {
        log.info("processEntityGraphEntitiesOneByOne");
        var ids = entityGraphEntityRepo.findAllIds();
        ids.forEach(id -> {
            var entity = entityGraphEntityRepo.findById(id).orElseThrow();
            entityMapper.toDto(entity);
        });
    }

    @Transactional
    public void processEntityGraphEntities() {
        log.info("processEntityGraphEntities");
        var entityGraphEntities = entityGraphEntityRepo.findAll();
        entityGraphEntities.forEach(entityMapper::toDto);
    }

    @Transactional
    public void processEntityGraphEntityPagesSeparate() {
        log.info("processEntityGraphEntityPagesSeparate");
        var idsPage = entityGraphEntityRepo.findAllIds(PageRequest.of(0, 10));
        entityGraphEntityRepo.findAllByIdIn(Set.copyOf(idsPage.getContent()))
                .forEach(entityMapper::toDto);
        while (idsPage.hasNext()) {
            idsPage = entityGraphEntityRepo.findAllIds(idsPage.nextPageable());
            entityGraphEntityRepo.findAllByIdIn(Set.copyOf(idsPage.getContent()))
                    .forEach(entityMapper::toDto);
        }
    }

    @Transactional
    public void processEntityGraphEntitySinglePageSeparate() {
        log.info("processEntityGraphEntitySinglePageSeparate");
        var idsPage = entityGraphEntityRepo.findAllIds(PageRequest.of(0, 10));
        entityGraphEntityRepo.findAllByIdIn(Set.copyOf(idsPage.getContent()))
                .forEach(entityMapper::toDto);
    }

    @Transactional
    public void processEntityGraphEntityPages() {
        log.info("processEntityGraphEntityPages");
        var page = entityGraphEntityRepo.findAll(PageRequest.of(0, 10));
        page.forEach(entityMapper::toDto);
        while (page.hasNext()) {
            page = entityGraphEntityRepo.findAll(page.nextPageable());
            page.forEach(entityMapper::toDto);
        }
    }

    @Transactional
    public void processEntityGraphEntitySinglePage() {
        log.info("processEntityGraphEntitySinglePage");
        var page = entityGraphEntityRepo.findAll(PageRequest.of(0, 10));
        page.forEach(entityMapper::toDto);
    }

    @Transactional
    public void processBatchSizeFindById(){
        log.info("processBatchSizeFindById");
        var id = batchSizeEntityRepo.findAllIds(PageRequest.of(0,1)).iterator().next();
        entityMapper.toDto(batchSizeEntityRepo.findById(id).orElseThrow());
    }

    @Transactional
    public void processBatchSizeEntitiesOneByOne() {
        log.info("processBatchSizeEntitiesOneByOne");
        var ids = batchSizeEntityRepo.findAllIds();
        ids.forEach(id -> {
            var entity = batchSizeEntityRepo.findById(id).orElseThrow();
            entityMapper.toDto(entity);
        });
    }

    @Transactional
    public void processBatchSizeEntities() {
        log.info("processBatchSizeEntities");
        var batchSizeEntities = batchSizeEntityRepo.findAll();
        batchSizeEntities
                .forEach(entityMapper::toDto);
    }

    @Transactional
    public void processBatchSizeEntityPages() {
        log.info("processBatchSizeEntityPages");
        var page = batchSizeEntityRepo.findAll(PageRequest.of(0, 10));
        page.forEach(entityMapper::toDto);
        while (page.hasNext()) {
            page = batchSizeEntityRepo.findAll(page.nextPageable());
            page.forEach(entityMapper::toDto);
        }
    }

    @Transactional
    public void processBatchSizeEntitiesSinglePage() {
        log.info("processBatchSizeEntitiesSinglePage");
        var page = batchSizeEntityRepo.findAll(PageRequest.of(0, 10));
        page.forEach(entityMapper::toDto);
    }

    private void saveEntityGraphEntities(int mainEntitiesCount, int subEntitiesCount, int subSubEntitiesCount) {
        var dtos = getMainEntityDtos(mainEntitiesCount, subEntitiesCount, subSubEntitiesCount);
        entityGraphEntityRepo.saveAll(entityMapper.toEntityGraphEntities(dtos));
    }

    private void saveBatchSizeEntities(int mainEntitiesCount, int subEntitiesCount, int subSubEntitiesCount) {
        var dtos = getMainEntityDtos(mainEntitiesCount, subEntitiesCount, subSubEntitiesCount);
        batchSizeEntityRepo.saveAll(entityMapper.toBatchSizeEntities(dtos));
    }

    private List<MainEntityDto> getMainEntityDtos(int mainEntitiesCount, int subEntitiesCount, int subSubEntitiesCount) {
        return IntStream.range(0, mainEntitiesCount)
                .mapToObj(index -> getMainEntityDto(subEntitiesCount, subSubEntitiesCount))
                .collect(Collectors.toList());
    }

    private MainEntityDto getMainEntityDto(int subEntitiesCount, int subSubEntitiesCount) {
        return new MainEntityDto()
                .setSub1(getSubEntityDtos(subEntitiesCount, subSubEntitiesCount))
                .setSub2(getSubEntityDtos(subEntitiesCount, subSubEntitiesCount))
                .setSub3(getSubEntityDtos(subEntitiesCount, subSubEntitiesCount))
                .setSub4(getSubEntityDtos(subEntitiesCount, subSubEntitiesCount))
                .setSub5(getSubEntityDtos(subEntitiesCount, subSubEntitiesCount))
                .setName(NAME);
    }

    private List<SubEntityDto> getSubEntityDtos(int subEntitiesCount, int subSubEntitiesCount) {
        return IntStream.range(0, subEntitiesCount)
                .mapToObj(index -> getSubEntityDto(subSubEntitiesCount))
                .collect(Collectors.toList());
    }

    private SubEntityDto getSubEntityDto(int subSubEntitiesCount) {
        return new SubEntityDto()
                .setSub1(getSubSubEntityDtos(subSubEntitiesCount))
                .setSub2(getSubSubEntityDtos(subSubEntitiesCount))
                .setName(NAME);
    }

    private List<SubSubEntityDto> getSubSubEntityDtos(int subSubEntitiesCount) {
        return IntStream.range(0, subSubEntitiesCount)
                .mapToObj(i -> getSubSubEntityDto())
                .collect(Collectors.toList());
    }

    private SubSubEntityDto getSubSubEntityDto() {
        return new SubSubEntityDto()
                .setName(NAME);
    }

}
