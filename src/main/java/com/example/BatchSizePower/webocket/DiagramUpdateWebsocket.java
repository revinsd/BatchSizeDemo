package com.example.BatchSizePower.webocket;

import com.example.BatchSizePower.result.SpeedTestResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class DiagramUpdateWebsocket {
    private final SimpMessagingTemplate messagingTemplate;

    public void updateDiagram(SpeedTestResultDto speedTestResultDto)  {
        messagingTemplate.convertAndSend("/topic/diagramUpdate", speedTestResultDto);
    }
}
