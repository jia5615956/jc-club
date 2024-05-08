package com.jia.wx.handler;

//统一接口
public interface WxChatMsgHandler {

    WxChatMsgTypeEnum getMsgType();
    String dealMsg();

}
