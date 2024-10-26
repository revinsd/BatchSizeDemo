package com.example.BatchSizePower.service;

import com.example.BatchSizePower.result.ProcessStatsDto;
import com.example.BatchSizePower.result.SpeedTestResultDto;
import com.example.BatchSizePower.webocket.DiagramUpdateWebsocket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class SpeedTestService {
    private final PostService postService;
    private final DiagramUpdateWebsocket diagramUpdateWebsocket;

    public void startTest() {
        log.info("Speed test started");
        computeAndSend();
        log.info("Speed test finished");
    }

    private void computeAndSend() {
        var result = computeResult();
        diagramUpdateWebsocket.updateDiagram(result);
    }

    private SpeedTestResultDto computeResult() {
        var speedTestResult = new SpeedTestResultDto();

        speedTestResult.setProcessEntityGraphFindByIdInStats(
                processAndGetStats(postService::entityGraphFindByIdIn)
        );
        speedTestResult.setProcessEntityGraphFindAllStats(
                processAndGetStats(postService::entityGraphFindAll)
        );
        speedTestResult.setProcessBatchSizeFindByIdInStats(
                processAndGetStats(postService::batchSizeFindByIdIn)
        );
        speedTestResult.setProcessBatchSizeFindAllStats(
                processAndGetStats(postService::batchSizeFindAll)
        );
        return speedTestResult;
    }

    private ProcessStatsDto processAndGetStats(Runnable runnable) {
        var startTime = Instant.now();
        runnable.run();
        var endTime = Instant.now();
        return new ProcessStatsDto(Duration.between(startTime, endTime).toMillis());
    }


}
