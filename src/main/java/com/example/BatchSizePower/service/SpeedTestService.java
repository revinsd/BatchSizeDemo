package com.example.BatchSizePower.service;

import com.example.BatchSizePower.rest.request.StartSpeedTestRequestDto;
import com.example.BatchSizePower.result.ProcessStatsDto;
import com.example.BatchSizePower.result.SpeedTestResultDto;
import com.example.BatchSizePower.webocket.DiagramUpdateWebsocket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SpeedTestService {
    private final EntityService entityService;
    private final DiagramUpdateWebsocket diagramUpdateWebsocket;

    private static final List<Integer> MAIN_ENTITY_COUNTS = List.of(10, 50, 100, 500, 1000);

    public void startTest(StartSpeedTestRequestDto startSpeedTesRequestDto) {
        log.info("Speed test started");
        MAIN_ENTITY_COUNTS.forEach(mainEntityCount -> computeAndSend(mainEntityCount, startSpeedTesRequestDto));
        log.info("Speed test finished");
    }

    private void computeAndSend(int mainEntitiesCount, StartSpeedTestRequestDto startSpeedTestRequestDto) {
        entityService.saveMainEntities(
                mainEntitiesCount,
                startSpeedTestRequestDto.getSubEntityCount(),
                startSpeedTestRequestDto.getSubSubEntityCount()
        );
        var result = computeResult(startSpeedTestRequestDto);
        diagramUpdateWebsocket.updateDiagram(result);
        entityService.deleteAllEntities();
    }

    private SpeedTestResultDto computeResult(StartSpeedTestRequestDto startSpeedTestRequestDto) {
        var speedTestResult = new SpeedTestResultDto();

        if (startSpeedTestRequestDto.isProcessEntityGraphFindById()) {
            speedTestResult.setProcessEntityGraphFindByIdStats(
                    processAndGetStats(entityService::processEntityGraphFindById)
            );
        }
        if (startSpeedTestRequestDto.isProcessEntityGraphEntitiesOneByOne()) {
            speedTestResult.setProcessEntityGraphEntitiesOneByOneStats(
                    processAndGetStats(entityService::processEntityGraphEntitiesOneByOne)
            );
        }
        if (startSpeedTestRequestDto.isProcessEntityGraphEntities()) {
            speedTestResult.setProcessEntityGraphEntitiesStats(
                    processAndGetStats(entityService::processEntityGraphEntities)
            );
        }
        if (startSpeedTestRequestDto.isProcessEntityGraphEntityPages()) {
            speedTestResult.setProcessEntityGraphEntityPagesStats(
                    processAndGetStats(entityService::processEntityGraphEntityPages)
            );
        }
        if (startSpeedTestRequestDto.isProcessEntityGraphEntitySinglePage()) {
            speedTestResult.setProcessEntityGraphEntitySinglePageStats(
                    processAndGetStats(entityService::processEntityGraphEntitySinglePage)
            );
        }
        if (startSpeedTestRequestDto.isProcessEntityGraphEntityPagesSeparate()) {
            speedTestResult.setProcessEntityGraphEntityPagesSeparateStats(
                    processAndGetStats(entityService::processEntityGraphEntityPagesSeparate)
            );
        }
        if (startSpeedTestRequestDto.isProcessEntityGraphEntitySinglePageSeparate()) {
            speedTestResult.setProcessEntityGraphEntitySinglePageSeparateStats(
                    processAndGetStats(entityService::processEntityGraphEntitySinglePageSeparate)
            );
        }

        if (startSpeedTestRequestDto.isProcessBatchSizeFindById()) {
            speedTestResult.setProcessBatchSizeFindByIdStats(
                    processAndGetStats(entityService::processBatchSizeFindById)
            );
        }
        if (startSpeedTestRequestDto.isProcessBatchSizeEntitiesOneByOne()) {
            speedTestResult.setProcessBatchSizeEntitiesOneByOneStats(
                    processAndGetStats(entityService::processBatchSizeEntitiesOneByOne)
            );
        }
        if (startSpeedTestRequestDto.isProcessBatchSizeEntities()) {
            speedTestResult.setProcessBatchSizeEntitiesStats(
                    processAndGetStats(entityService::processBatchSizeEntities)
            );
        }
        if (startSpeedTestRequestDto.isProcessBatchSizeEntityPages()) {
            speedTestResult.setProcessBatchSizeEntityPagesStats(
                    processAndGetStats(entityService::processBatchSizeEntityPages)
            );
        }
        if (startSpeedTestRequestDto.isProcessBatchSizeEntitiesSinglePage()) {
            speedTestResult.setProcessBatchSizeEntitiesSinglePageStats(
                    processAndGetStats(entityService::processBatchSizeEntitiesSinglePage)
            );
        }
        return speedTestResult;
    }

    private ProcessStatsDto processAndGetStats(Runnable runnable) {
        var startTime = Instant.now();
        runnable.run();
        var endTime = Instant.now();
        return new ProcessStatsDto(Duration.between(startTime, endTime).toMillis());
    }


}
