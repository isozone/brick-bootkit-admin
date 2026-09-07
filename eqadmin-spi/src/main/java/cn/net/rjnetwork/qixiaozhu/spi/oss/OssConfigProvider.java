package cn.net.rjnetwork.qixiaozhu.spi.oss;

import cn.net.rjnetwork.qixiaozhu.oss.OssConfigInfo;

import java.util.List;

/**
 * Public OSS configuration query contract.
 *
 * <p>Hosts implement this contract on top of their internal OSS config
 * service so plugins can discover configured storage platforms (enabled,
 * default, metadata) without depending on host service beans. Credentials
 * are never exposed; platform capability extensions belong to
 * {@link cn.net.rjnetwork.qixiaozhu.spi.storage.StoragePlatformProvider}.</p>
 */
public interface OssConfigProvider {

    /**
     * List all enabled storage configs.
     *
     * @return enabled configs, never {@code null}
     */
    List<OssConfigInfo> getEnabledConfigs();

    /**
     * List all storage configs regardless of enabled state.
     *
     * @return all configs, never {@code null}
     */
    List<OssConfigInfo> getAllConfigs();

    /**
     * Resolve a storage config by platform name.
     *
     * @param platformName platform display name
     * @return the matching config, or {@code null} when not found
     */
    OssConfigInfo getConfigByPlatformName(String platformName);

    /**
     * Resolve the default storage config among enabled ones.
     *
     * @return the default config, or {@code null} when none is marked default
     */
    default OssConfigInfo getDefaultConfig() {
        return getEnabledConfigs().stream()
                .filter(config -> config != null && Boolean.TRUE.equals(config.getIsDefault()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Resolve the platform name of the default storage config.
     *
     * @return default platform name, or {@code null} when none is marked default
     */
    default String getDefaultPlatformName() {
        OssConfigInfo defaultConfig = getDefaultConfig();
        return defaultConfig == null ? null : defaultConfig.getPlatformName();
    }
}
