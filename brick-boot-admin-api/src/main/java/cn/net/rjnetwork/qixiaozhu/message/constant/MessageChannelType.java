package cn.net.rjnetwork.qixiaozhu.message.constant;

/**
 * Public message channel enum definition.
 */
public enum MessageChannelType {

    EMAIL("EMAIL", "Email"),
    SMS("SMS", "SMS"),
    WECOM("WECOM", "WeCom"),
    DINGTALK("DINGTALK", "DingTalk"),
    LARK("LARK", "Lark"),
    QQ_OPEN_BOT("QQ_OPEN_BOT", "QQ Open Bot"),
    WECHAT_MP("WECHAT_MP", "WeChat MP"),
    DINGTALK_BOT("DINGTALK_BOT", "DingTalk Bot"),
    WECOM_BOT("WECOM_BOT", "WeCom Bot"),
    WEBHOOK("WEBHOOK", "Generic Webhook"),
    JPUSH("JPUSH", "JPush App Push");

    private final String code;
    private final String displayName;

    MessageChannelType(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public String getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static MessageChannelType getByCode(String code) {
        if (code == null) {
            return null;
        }
        for (MessageChannelType type : values()) {
            if (type.code.equalsIgnoreCase(code)) {
                return type;
            }
        }
        return null;
    }

    public static boolean isValid(String code) {
        return getByCode(code) != null;
    }
}
