package com.ssafy.backend.service;

import com.ssafy.backend.vo.SocketVo;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocketService {

    private final SimpMessageSendingOperations messagingTemplate;

    public void serverToClientMessage(SocketVo socketVo) {
        System.out.println("serverToClientMessage");
        messagingTemplate.convertAndSend("/sub/" + socketVo.getChannelId(), socketVo);
    }
}
