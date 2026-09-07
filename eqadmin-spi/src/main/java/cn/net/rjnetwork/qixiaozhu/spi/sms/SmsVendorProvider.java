package cn.net.rjnetwork.qixiaozhu.spi.sms;

import cn.net.rjnetwork.qixiaozhu.message.sms.SmsSendRequest;
import cn.net.rjnetwork.qixiaozhu.message.sms.SmsSendResult;
import cn.net.rjnetwork.qixiaozhu.message.sms.SmsVendorType;

import java.util.List;

/**
 * Public SMS vendor extension contract.
 *
 * <p>Vendors implement this contract to plug a new SMS provider into the host
 * message engine. The host routes by {@link #getVendorType()} and exposes
 * configuration items through {@link #getConfigItems()}.</p>
 */
public interface SmsVendorProvider {

    /**
     * Vendor type of this provider.
     */
    SmsVendorType getVendorType();

    /**
     * Check whether this provider handles the given vendor type code
     * (case-insensitive).
     */
    default boolean supports(String vendorType) {
        return getVendorType() != null && getVendorType().getCode().equalsIgnoreCase(vendorType);
    }

    /**
     * Send an SMS through this vendor.
     *
     * @param request unified send payload
     * @return send result, never {@code null}
     */
    SmsSendResult send(SmsSendRequest request);

    /**
     * Verify vendor credentials/connectivity using the credentials carried in
     * the probe request.
     *
     * @param probe request carrying accessKeyId/accessKeySecret/endpoint
     * @return {@code true} when the vendor connection works
     */
    boolean testConnection(SmsSendRequest probe);

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
