package com.example.BatchSizePower.rest.request;

import lombok.Data;

@Data
public class StartSpeedTestRequestDto {
    private int subEntityCount;
    private int subSubEntityCount;

    private boolean processBatchSizeFindById;
    private boolean processBatchSizeEntitiesOneByOne;
    private boolean processBatchSizeEntities;
    private boolean processBatchSizeEntityPages;
    private boolean processBatchSizeEntitiesSinglePage;

    private boolean processEntityGraphFindById;
    private boolean processEntityGraphEntitiesOneByOne;
    private boolean processEntityGraphEntities;
    private boolean processEntityGraphEntityPages;
    private boolean processEntityGraphEntitySinglePage;
    private boolean processEntityGraphEntityPagesSeparate;
    private boolean processEntityGraphEntitySinglePageSeparate;
}
