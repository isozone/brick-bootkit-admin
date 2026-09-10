package cn.net.rjnetwork.qixiaozhu.tts;

/**
 * Public TTS vendor type enum definition.
 *
 * <p>Mirrors {@link cn.net.rjnetwork.qixiaozhu.message.sms.SmsVendorType} so the
 * host can route synthesis requests to the matching vendor provider.</p>
 */
public enum TtsVendorType {

    DASHSCOPE("DASHSCOPE", "Aliyun Bailian DashScope"),
    ALIYUN_NLS("ALIYUN_NLS", "Aliyun Intelligent Speech Interaction"),
    TENCENT("TENCENT", "Tencent Cloud TTS"),
    BAIDU("BAIDU", "Baidu Speech TTS");

    private final String code;
    private final String displayName;

    TtsVendorType(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public String getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static TtsVendorType getByCode(String code) {
        if (code == null) {
            return null;
        }
        for (TtsVendorType type : values()) {
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
