package com.example.BatchSizePower.rest;

import com.example.BatchSizePower.rest.request.StartSpeedTestRequestDto;
import com.example.BatchSizePower.service.SpeedTestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/speedTest")
@Slf4j
public class SpeedTestController {
    private final SpeedTestService speedTestService;

    @PostMapping( "/start")
    public void start(StartSpeedTestRequestDto startSpeedTestRequestDto){
        log.info("Speed test request {}", startSpeedTestRequestDto);
        speedTestService.startTest(startSpeedTestRequestDto);
    }
}
