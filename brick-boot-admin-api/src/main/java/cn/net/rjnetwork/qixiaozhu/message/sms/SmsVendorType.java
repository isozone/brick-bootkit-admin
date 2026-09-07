package cn.net.rjnetwork.qixiaozhu.message.sms;

/**
 * Public SMS vendor type enum definition.
 */
public enum SmsVendorType {

    ALIYUN("ALIYUN", "Aliyun SMS"),
    QINIU("QINIU", "Qiniu SMS"),
    JINGDONG("JINGDONG", "JingDong SMS"),
    TENCENT("TENCENT", "Tencent SMS"),
    BAIDU("BAIDU", "Baidu SMS");

    private final String code;
    private final String displayName;

    SmsVendorType(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public String getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static SmsVendorType getByCode(String code) {
        if (code == null) {
            return null;
        }
        for (SmsVendorType type : values()) {
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
