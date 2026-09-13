package cn.net.rjnetwork.qixiaozhu.spi.env;

/**
 * Public environment/configuration access contract.
 *
 * <p>Hosts implement this contract so plugins can read resolved configuration
 * properties without reflecting into the host Spring context for an
 * {@code Environment} bean. This keeps plugin code decoupled from Spring's
 * config abstraction while still allowing access to host-defined settings.</p>
 */
public interface EnvironmentAccessProvider {

    /**
     * Resolve a property by key.
     *
     * @param key          configuration key
     * @param defaultValue fallback when the key is absent
     * @return resolved value, or {@code defaultValue} when missing
     */
    String getProperty(String key, String defaultValue);

    /**
     * Resolve a property by key.
     *
     * @param key configuration key
     * @return resolved value, or {@code null} when missing
     */
    default String getProperty(String key) {
        return getProperty(key, null);
    }

    /**
     * Resolve a required property, throwing when absent.
     *
     * @param key configuration key
     * @return resolved value
     * @throws IllegalStateException when the key is missing
     */
    default String getRequiredProperty(String key) {
        String value = getProperty(key);
        if (value == null) {
            throw new IllegalStateException("Missing required property: " + key);
        }
        return value;
    }

    /**
     * Resolve a property and convert to the given type.
     *
     * @param key          configuration key
     * @param targetType   target type (Integer, Long, Boolean, etc.)
     * @param defaultValue fallback when the key is absent
     * @return resolved value converted to {@code targetType}, or {@code defaultValue}
     */
    @SuppressWarnings("unchecked")
    default <T> T getProperty(String key, Class<T> targetType, T defaultValue) {
        String raw = getProperty(key);
        if (raw == null) {
            return defaultValue;
        }
        if (targetType == String.class) {
            return (T) raw;
        }
        if (targetType == Integer.class) {
            try { return (T) Integer.valueOf(raw.trim()); } catch (Exception e) { return defaultValue; }
        }
        if (targetType == Long.class) {
            try { return (T) Long.valueOf(raw.trim()); } catch (Exception e) { return defaultValue; }
        }
        if (targetType == Boolean.class) {
            return (T) Boolean.valueOf(raw.trim());
        }
        if (targetType == Double.class) {
            try { return (T) Double.valueOf(raw.trim()); } catch (Exception e) { return defaultValue; }
        }
        return defaultValue;
    }

    /**
     * Check whether the host environment contains the given key.
     */
    boolean containsProperty(String key);

    /**
     * @return the active Spring profile names, possibly empty
     */
    String[] getActiveProfiles();
}
