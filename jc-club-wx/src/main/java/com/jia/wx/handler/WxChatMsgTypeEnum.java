package com.jia.wx.handler;

//微信类型枚举
public enum WxChatMsgTypeEnum {
    SUBSCRIBE("event.subscribe","用户关注事件"),
    TEXT_MSG("text","接受用户文本信息");

    private String msgType;
    private String desc;

    private WxChatMsgTypeEnum(String msgType, String desc) {
        this.msgType = msgType;
        this.desc = desc;
    }

    //静态方法循环获取枚举的value
    public static WxChatMsgTypeEnum getByMsgType(String msgType) {
        for(WxChatMsgTypeEnum wxChatMsgTypeEnum : WxChatMsgTypeEnum.values()) {
            if(msgType.equals(wxChatMsgTypeEnum)){
                return wxChatMsgTypeEnum;
            }
        }
        return null;
    }
}
