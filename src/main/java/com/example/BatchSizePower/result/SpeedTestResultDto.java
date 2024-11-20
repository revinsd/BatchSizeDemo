package com.example.BatchSizePower.result;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SpeedTestResultDto {
    private ProcessStatsDto processBatchSizeFindByIdInStats;
    private ProcessStatsDto processBatchSizeFindAllStats;
    private ProcessStatsDto processEntityGraphFindByIdInStats;
    private ProcessStatsDto processEntityGraphFindAllStats;

}
