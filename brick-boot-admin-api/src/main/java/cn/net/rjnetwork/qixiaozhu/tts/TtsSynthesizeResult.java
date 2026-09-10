package cn.net.rjnetwork.qixiaozhu.tts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Public TTS synthesize result payload.
 *
 * <p>The primary delivery path is {@link #audioUrl}: the host synthesizes the
 * audio and persists it to the configured storage platform, so callers can use
 * the returned URL directly without touching storage internals. When the caller
 * explicitly asks for the raw payload ({@code persist=false}), {@link #audioBytes}
 * is populated instead.</p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TtsSynthesizeResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Whether synthesis succeeded. */
    private boolean success;

    /** Publicly accessible audio URL after persistence; {@code null} when not persisted or failed. */
    private String audioUrl;

    /** Raw audio bytes; only populated when the caller asked for the raw payload. */
    private byte[] audioBytes;

    /** Audio format of the produced payload, e.g. {@code mp3}. */
    private String format;

    /** Audio duration in milliseconds, when the vendor reports it. */
    private Integer durationMs;

    /** Vendor type that produced this result. */
    private String vendorType;

    /** Whether the result was served from cache. */
    private boolean cached;

    /** Machine readable error code when failed. */
    private String errorCode;

    /** Human readable error detail when failed. */
    private String errorMsg;

    /** Raw vendor response, when available. */
    private String rawResponse;

    public static TtsSynthesizeResult ok(String audioUrl, String format, Integer durationMs) {
        return TtsSynthesizeResult.builder()
                .success(true)
                .audioUrl(audioUrl)
                .format(format)
                .durationMs(durationMs)
                .build();
    }

    public static TtsSynthesizeResult okBytes(byte[] audioBytes, String format, Integer durationMs) {
        return TtsSynthesizeResult.builder()
                .success(true)
                .audioBytes(audioBytes)
                .format(format)
                .durationMs(durationMs)
                .build();
    }

    public static TtsSynthesizeResult fail(String errorCode, String errorMsg) {
        return TtsSynthesizeResult.builder()
                .success(false)
                .errorCode(errorCode)
                .errorMsg(errorMsg)
                .build();
    }
}
