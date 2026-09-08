package cn.net.rjnetwork.qixiaozhu.message.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Public channel config DTO.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelConfigDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String channelType;
    private String configJson;
    private Integer priority;
    private EmailChannelConfig emailConfig;
    private SmsChannelConfig smsConfig;
    private WecomChannelConfig wecomConfig;
    private DingtalkChannelConfig dingtalkConfig;
    private LarkChannelConfig larkConfig;
    private QqOpenBotChannelConfig qqOpenBotConfig;
    private WechatMpChannelConfig wechatMpConfig;
    private DingtalkBotChannelConfig dingtalkBotConfig;
    private WecomBotChannelConfig wecomBotConfig;
    private WebhookChannelConfig webhookConfig;
    private JpushChannelConfig jpushConfig;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EmailChannelConfig implements Serializable {
        private Long configId;
        private String[] toEmails;
        private String[] ccEmails;
        private Boolean sendToOwner;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SmsChannelConfig implements Serializable {
        private Long appId;
        private Long templateId;
        private Long signId;
        private Boolean sendToOwner;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WecomChannelConfig implements Serializable {
        private Long robotId;
        private String[] toUsers;
        private String[] toParty;
        private Boolean sendToOwner;
        private Boolean isTextCard;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DingtalkChannelConfig implements Serializable {
        private Long agentId;
        private Long[] toDeptIds;
        private Long[] toUserIds;
        private Boolean sendToOwner;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LarkChannelConfig implements Serializable {
        private Long appId;
        private String[] openIds;
        private String[] deptIds;
        private Boolean sendToOwner;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QqOpenBotChannelConfig implements Serializable {
        private String appId;
        private String appSecret;
        private String channelId;
        private String guildId;
        private String unionAppId;
        private String accessToken;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WechatMpChannelConfig implements Serializable {
        private String sendMode;
        private String appId;
        private String appSecret;
        private String accessToken;
        private String templateId;
        private String pagePath;
        private String miniProgramState;
        private String lang;
        private String jumpUrl;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DingtalkBotChannelConfig implements Serializable {
        private String webhookUrl;
        private String secret;
        private String[] atMobiles;
        private Boolean atAll;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WecomBotChannelConfig implements Serializable {
        private String webhookKey;
        private String webhookUrl;
        private String[] mentionedList;
        private String[] mentionedMobileList;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WebhookChannelConfig implements Serializable {
        private String webhookUrl;
        private String secret;
        private String method;
        private String contentType;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JpushChannelConfig implements Serializable {
        private String appKey;
        private String masterSecret;
        private String audienceMode;
        private Boolean sendToOwner;
    }
}
