package com.jia.wx.handler;

import java.util.Map;

//统一接口
public interface WxChatMsgHandler {

    WxChatMsgTypeEnum getMsgType();
    String dealMsg(Map<String, String> msgMap);

}
