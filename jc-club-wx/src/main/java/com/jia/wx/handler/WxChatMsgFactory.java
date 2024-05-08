package com.jia.wx.handler;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class WxChatMsgFactory implements InitializingBean {


    @Resource
    private List<WxChatMsgHandler> handlerList;

    //开始时，将所以handler放进map里
    private Map<WxChatMsgTypeEnum, WxChatMsgHandler> handlerMap = new HashMap<>();


    public WxChatMsgHandler getHandlerByMsgType(String msgType) {
        WxChatMsgTypeEnum msgTypeEnum = WxChatMsgTypeEnum.getByMsgType(msgType);
        return handlerMap.get(msgTypeEnum);
    }



    //启动时，将所以handler放进去
    @Override
    public void afterPropertiesSet() throws Exception {
        for (WxChatMsgHandler wxChatMsgHandler : handlerList) {
            System.out.println("handlerList：" + handlerList);
            System.out.println("wxChatMsgHandler：" + wxChatMsgHandler);
            handlerMap.put(wxChatMsgHandler.getMsgType(), wxChatMsgHandler);
        }
    }
}
