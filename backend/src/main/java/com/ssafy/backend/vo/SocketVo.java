package com.ssafy.backend.vo;

import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SocketVo {
    private String type;
    private String sender;
    private String channelId;
    private Object data;

    public void newConnect(){this.type = "new";}
    public void closeConnect(){this.type="close";}
}
