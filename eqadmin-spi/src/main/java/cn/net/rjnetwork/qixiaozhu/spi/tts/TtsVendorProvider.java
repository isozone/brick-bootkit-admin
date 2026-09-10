package cn.net.rjnetwork.qixiaozhu.spi.tts;

import cn.net.rjnetwork.qixiaozhu.tts.TtsSynthesizeRequest;
import cn.net.rjnetwork.qixiaozhu.tts.TtsSynthesizeResult;
import cn.net.rjnetwork.qixiaozhu.tts.TtsVendorType;

import java.util.List;

/**
 * Public TTS vendor extension contract.
 *
 * <p>Vendors implement this contract to plug a new speech synthesis provider
 * into the host TTS engine. The host routes by {@link #getVendorType()} and
 * exposes configuration items through {@link #getConfigItems()}.</p>
 *
 * <p>Implementations only perform <b>synthesis</b>; persistence and URL
 * resolution belong to the host client facade
 * ({@link TtsClient}).</p>
 */
public interface TtsVendorProvider {

    /**
     * Vendor type of this provider.
     */
    TtsVendorType getVendorType();

    /**
     * Check whether this provider handles the given vendor type code
     * (case-insensitive).
     */
    default boolean supports(String vendorType) {
        return getVendorType() != null && getVendorType().getCode().equalsIgnoreCase(vendorType);
    }

    /**
     * Synthesize speech and return the raw audio payload.
     *
     * @param request unified synthesize payload
     * @return synthesize result, never {@code null}
     */
    TtsSynthesizeResult synthesize(TtsSynthesizeRequest request);

    /**
     * Verify vendor credentials/connectivity using the credentials carried in
     * the probe request.
     *
     * @param probe request carrying apiKey/accessKeyId/accessKeySecret/endpoint
     * @return {@code true} when the vendor connection works
     */
    boolean testConnection(TtsSynthesizeRequest probe);

    /**
     * Configuration items required by this vendor, used by the host UI and
     * config validation.
     */
    List<ConfigItem> getConfigItems();

    /**
     * Configuration item definition.
     */
    record ConfigItem(
            String key,
            String name,
            String type,
            boolean required,
            String defaultValue,
            String description
    ) {
    }
}
