package cn.net.rjnetwork.qixiaozhu.message.sms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Public SMS send result payload.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SmsSendResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Whether the send was accepted/succeeded. */
    private boolean success;

    /** Human readable message. */
    private String message;

    /** Error detail when failed. */
    private String error;

    /** Vendor request id, when available. */
    private String requestId;

    /** Raw vendor response, when available. */
    private String rawResponse;

    public static SmsSendResult ok(String message) {
        return SmsSendResult.builder().success(true).message(message).build();
    }

    public static SmsSendResult fail(String error) {
        return SmsSendResult.builder().success(false).error(error).build();
    }
}
