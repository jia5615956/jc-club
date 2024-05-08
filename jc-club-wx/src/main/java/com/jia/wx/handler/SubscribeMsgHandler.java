package com.jia.wx.handler;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SubscribeMsgHandler implements WxChatMsgHandler{

    //获取枚举
    @Override
    public WxChatMsgTypeEnum getMsgType() {
        return WxChatMsgTypeEnum.SUBSCRIBE;
    }

    //处理消息
    @Override
    public String dealMsg() {
        return "处理订阅消息";
    }
}
