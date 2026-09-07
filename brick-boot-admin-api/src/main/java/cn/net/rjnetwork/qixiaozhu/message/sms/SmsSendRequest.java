package cn.net.rjnetwork.qixiaozhu.message.sms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Public SMS send request payload.
 *
 * <p>Carries everything a vendor needs to send an SMS: recipient phones,
 * sign/template identity and vendor credentials. Vendor-specific fields
 * ({@code signCode}, {@code appCode}, {@code extJson}...) are optional and
 * only used by the vendors that require them.</p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SmsSendRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Recipient phone numbers. */
    private List<String> phones;

    /** SMS sign name, used by vendors like Aliyun/Tencent. */
    private String signName;

    /** SMS sign code, used by vendors like Baidu. */
    private String signCode;

    /** SMS template code. */
    private String templateCode;

    /** Rendered template content (JSON string), optional. */
    private String templateContent;

    /** Template parameters for rendering. */
    private Map<String, String> templateParams;

    /** Vendor access key id. */
    private String accessKeyId;

    /** Vendor access key secret. */
    private String accessKeySecret;

    /** Vendor endpoint. */
    private String endpoint;

    /** Vendor app code / app id, used by Tencent. */
    private String appCode;

    /** Extra vendor-specific payload (JSON string), optional. */
    private String extJson;
}
