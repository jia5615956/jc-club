package com.jia.wx.controller;


import com.jia.wx.handler.WxChatMsgFactory;
import com.jia.wx.handler.WxChatMsgHandler;
import com.jia.wx.utils.MessageUtil;
import com.jia.wx.utils.SHA1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;


@RestController
@Slf4j
public class CallBackController {


    @Resource
    private WxChatMsgFactory wxChatMsgFactory;

    private static final String token = "adwidhaidwoaid";

    @RequestMapping("/test")
    public String test() {
        return "hello word";
    }
    /**
     * 回调消息校验
     */
    @GetMapping("/callback")
    public String callback(@RequestParam("signature") String signature,
                           @RequestParam("timestamp") String timestamp,
                           @RequestParam("nonce") String nonce,
                           @RequestParam("echostr") String echostr) {
        log.info("get验签请求参数：signature:{}，timestamp:{}，nonce:{}，echostr:{}",
                signature, timestamp, nonce, echostr);
        String shaStr = SHA1.getSHA1(token, timestamp, nonce, "");
        if (signature.equals(shaStr)) {
            return echostr;
        }
        return "unknown";
    }


    @PostMapping(value = "/callback", produces = "application/xml;charset=UTF-8")
    public String callback(
            @RequestBody String requestBody,
            @RequestParam("signature") String signature,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("nonce") String nonce,
            @RequestParam(value = "msg_signature", required = false) String msgSignature) {
        log.info("接收到微信消息：requestBody：{}", requestBody);
        //将用户登录的信息通过map获取
        Map<String, String> msgMap = MessageUtil.parseXml(requestBody);
        //获取给谁发送消息和发送给谁
        String toUserName = msgMap.get("ToUserName");
        String fromUserName = msgMap.get("FromUserName");
        String msgType = msgMap.get("MsgType");
        String event = msgMap.get("Event") == null ? "" : msgMap.get("Event");
        //拼接枚举类型
        String msgTypeKey = event+"."+msgType;
        //调用工厂
        WxChatMsgHandler handlerByMsgType = wxChatMsgFactory.getHandlerByMsgType(msgTypeKey);


        String msg ="<xml>\n" +
                "  <ToUserName><![CDATA["+fromUserName+"]]></ToUserName>\n" +
                "  <FromUserName><![CDATA["+toUserName+"]]></FromUserName>\n" +
                "  <CreateTime>1348831860</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[很烦。]]></Content>\n" +
                "</xml>";

        return msg;
    }
}