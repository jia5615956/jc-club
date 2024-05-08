package com.jia.wx.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

//处理回复消息业务
@Component
@Slf4j
public class ReceiveTextMsgHandler implements WxChatMsgHandler{
    @Override
    public WxChatMsgTypeEnum getMsgType() {
        return WxChatMsgTypeEnum.TEXT_MSG;
    }

    @Override
    public String dealMsg() {
        return "处理回复消息";
    }
}
