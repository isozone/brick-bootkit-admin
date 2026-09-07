package cn.net.rjnetwork.qixiaozhu.starter;

/**
 * Public starter metadata for the {@code eqadmin-starter-api} module.
 *
 * <p>Exposes stable coordinates and runtime metadata so consumers and tooling
 * can identify the starter without reading build files.</p>
 */
public final class EqadminStarterMetadata {

    public static final String GROUP_ID = "cn.net.rjnetwork";

    public static final String ARTIFACT_ID = "eqadmin-starter-api";

    public static final String NAME = "eqadmin-starter-api";

    public static final String DESCRIPTION =
            "Public auto-configuration facade, starter metadata and SPI bootstrap helpers.";

    private EqadminStarterMetadata() {
    }

    /**
     * Resolve the starter implementation version.
     *
     * @return implementation version from the package manifest, or {@code "unknown"}
     *         when the manifest is unavailable (e.g. running from IDE classes)
     */
    public static String version() {
        Package pkg = EqadminStarterMetadata.class.getPackage();
        String version = pkg == null ? null : pkg.getImplementationVersion();
        return version == null || version.isBlank() ? "unknown" : version;
    }
}
