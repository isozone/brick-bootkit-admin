package cn.net.rjnetwork.qixiaozhu.starter.spi;

import java.util.List;

/**
 * Runtime registry of public SPI implementations.
 *
 * <p>The starter auto-configuration facade exposes a default bean of this
 * type that collects SPI implementations from the application context; hosts
 * may replace it with their own implementation.</p>
 */
public interface SpiRegistry {

    /**
     * Resolve all registered beans of the given public SPI type.
     *
     * @param spiType public SPI contract type
     * @return immutable list of matching beans, never {@code null}
     */
    <T> List<T> getAll(Class<T> spiType);

    /**
     * Resolve the first registered bean of the given public SPI type.
     *
     * @param spiType public SPI contract type
     * @return the first matching bean, or {@code null} when none is registered
     */
    <T> T getFirst(Class<T> spiType);

    /**
     * Check whether any bean of the given public SPI type is registered.
     */
    boolean has(Class<?> spiType);
}
