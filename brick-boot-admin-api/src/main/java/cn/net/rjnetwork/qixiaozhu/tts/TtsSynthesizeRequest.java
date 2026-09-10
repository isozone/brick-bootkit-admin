package cn.net.rjnetwork.qixiaozhu.tts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Public TTS synthesize request payload.
 *
 * <p>Carries everything a vendor needs to synthesize speech: the text, the
 * voice/model selection and the vendor credentials.</p>
 *
 * <p><b>Credential policy</b>: plugins only need to fill the business fields
 * ({@code text}, and optionally {@code voice} / {@code model} / {@code format} /
 * prosody). The host resolves credentials from its own enabled TTS
 * configuration and injects them before dispatch, so credentials never need to
 * travel through plugin code. When a caller does supply credentials explicitly
 * they take precedence, which is what the admin "test connection" action uses.</p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TtsSynthesizeRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Text to synthesize. Required. */
    private String text;

    /** Vendor type override; when blank the host default configuration is used. */
    private String vendorType;

    /** Synthesis model, e.g. {@code cosyvoice-v2}. */
    private String model;

    /** Voice name, e.g. {@code longxiaochun}. */
    private String voice;

    /** Audio format, e.g. {@code mp3} / {@code wav} / {@code pcm}. Defaults to mp3. */
    private String format;

    /** Sample rate in Hz, e.g. 24000. */
    private Integer sampleRate;

    /** Speech rate multiplier, 0.5 - 2.0. */
    private Float speed;

    /** Volume, 0 - 100. */
    private Integer volume;

    /** Pitch multiplier, 0.5 - 2.0. */
    private Float pitch;

    /** Language hint for vendors that need it, e.g. {@code en} / {@code zh}. */
    private String languageType;

    /** Vendor API key (DashScope style). */
    private String apiKey;

    /** Vendor access key id (Aliyun NLS style). */
    private String accessKeyId;

    /** Vendor access key secret (Aliyun NLS style). */
    private String accessKeySecret;

    /** Vendor application key (Aliyun NLS style). */
    private String appKey;

    /** Vendor endpoint / region domain. */
    private String endpoint;

    /** Whether the synthesized audio must be persisted to storage. Defaults to {@code true}. */
    private Boolean persist;

    /** Storage platform name to use when persisting; blank means the default platform. */
    private String platformName;

    /** Logical storage path prefix, e.g. {@code study/words/}. */
    private String pathPrefix;

    /** Extra vendor-specific payload (JSON string), optional. */
    private String extJson;
}
