package com.example.BatchSizePower.result;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SpeedTestResultDto {
    private ProcessStatsDto processBatchSizeEntitiesOneByOneStats;
    private ProcessStatsDto processBatchSizeEntitiesStats;
    private ProcessStatsDto processBatchSizeEntityPagesStats;
    private ProcessStatsDto processBatchSizeEntitiesSinglePageStats;

    private ProcessStatsDto processEntityGraphEntitiesOneByOneStats;
    private ProcessStatsDto processEntityGraphEntitiesStats;
    private ProcessStatsDto processEntityGraphEntityPagesStats;
    private ProcessStatsDto processEntityGraphEntitySinglePageStats;
    private ProcessStatsDto processEntityGraphEntityPagesSeparateStats;
    private ProcessStatsDto processEntityGraphEntitySinglePageSeparateStats;
}
