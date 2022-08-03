package com.ssafy.backend.controller;

import com.ssafy.backend.service.MatchingService;
import com.ssafy.backend.service.SocketService;
import com.ssafy.backend.vo.SocketVo;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SocketController {

    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final SocketService socketService;

    public SocketController(SimpMessageSendingOperations simpMessageSendingOperations, SocketService socketService) {
        this.simpMessageSendingOperations = simpMessageSendingOperations;
        this.socketService = socketService;
    }

    @MessageMapping("/sub")
    @SendTo("/pub")
    public void socketHandler(SocketVo socketVo){
        simpMessageSendingOperations.convertAndSend("/sub/channel/" + socketVo.getChannelId(), socketVo);
    }
}
