package com.ssafy.backend.controller;

import com.ssafy.backend.service.OpenviduService;
import com.ssafy.backend.service.SocketService;
import com.ssafy.backend.vo.SocketVo;
import org.json.simple.JSONObject;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SocketController {

    private final SimpMessagingTemplate messagingTemplate;
    private final SocketService socketService;

    public SocketController(SimpMessagingTemplate  messagingTemplate, SocketService socketService) {
        this.messagingTemplate = messagingTemplate;
        this.socketService = socketService;
    }

    @MessageMapping("/sub")
    @SendTo("/pub")
    public void socketHandler(SocketVo socketVo){
        System.out.println(socketVo);
        messagingTemplate.convertAndSend("/sub/channel/" + socketVo.getChannelId(), socketVo);

        socketService.serverToClientMessage(socketVo.getChannelId(), new JSONObject());
    }
}
