package com.jia.wx.handler;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

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
    public String dealMsg(Map<String, String> msgMap) {
        //获取map里的值
        String toUserName = msgMap.get("ToUserName");
        String fromUserName = msgMap.get("FromUserName");
        String sbscribeMsg = "谢谢关注。";
        String msg ="<xml>\n" +
                "  <ToUserName><![CDATA["+fromUserName+"]]></ToUserName>\n" +
                "  <FromUserName><![CDATA["+toUserName+"]]></FromUserName>\n" +
                "  <CreateTime>1348831860</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA["+sbscribeMsg+"]]></Content>\n" +
                "</xml>";
        return msg;
    }
}
