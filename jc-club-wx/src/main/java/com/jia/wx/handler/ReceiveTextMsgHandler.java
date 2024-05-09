package com.jia.wx.handler;

import com.jia.wx.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

//处理回复消息业务
@Component
@Slf4j
public class ReceiveTextMsgHandler implements WxChatMsgHandler{

    private static final String KEY_WORD = "验证码";

    @Resource
    private RedisUtil redisUtil;

    @Override
    public WxChatMsgTypeEnum getMsgType() {
        return WxChatMsgTypeEnum.TEXT_MSG;
    }

    @Override
    public String dealMsg(Map<String, String> msgMap) {
        String content = msgMap.get("Content");
        if (!KEY_WORD.equals(content)) {
            return "";
        }
        String fromUserName = msgMap.get("FromUserName");
        String toUserName = msgMap.get("ToUserName");

        Random random = new Random();
        int num = random.nextInt(1000);
        String numContent = "您当前的验证码是：" + num + "！ 5分钟内有效";
        //将验证码放到redis,用发来信息的用户
        String buildKey = redisUtil.buildKey(fromUserName, String.valueOf(num));
        redisUtil.setNx(buildKey,"1",5L, TimeUnit.MINUTES);
        String msg ="<xml>\n" +
                "  <ToUserName><![CDATA["+fromUserName+"]]></ToUserName>\n" +
                "  <FromUserName><![CDATA["+toUserName+"]]></FromUserName>\n" +
                "  <CreateTime>1348831860</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA["+numContent+"]]></Content>\n" +
                "</xml>";
        return msg;
    }
}
