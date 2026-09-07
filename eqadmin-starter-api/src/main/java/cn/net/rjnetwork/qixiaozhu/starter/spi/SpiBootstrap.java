package cn.net.rjnetwork.qixiaozhu.starter.spi;

import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * Static bootstrap helpers to resolve public SPI beans from an application
 * context.
 *
 * <p>Intended for plugins and host code that need a null-safe, dependency-free
 * way to locate SPI implementations; prefer {@link SpiRegistry} when a Spring
 * bean is available.</p>
 */
public final class SpiBootstrap {

    private SpiBootstrap() {
    }

    /**
     * Resolve the first bean of the given public SPI type.
     *
     * @param context application context; {@code null} yields {@code null}
     * @param spiType public SPI contract type
     * @return the first matching bean, or {@code null} when none is registered
     */
    public static <T> T resolveFirst(ApplicationContext context, Class<T> spiType) {
        Map<String, T> beans = beansOfType(context, spiType);
        return beans.isEmpty() ? null : beans.values().iterator().next();
    }

    /**
     * Resolve all beans of the given public SPI type.
     *
     * @param context application context; {@code null} yields an empty list
     * @param spiType public SPI contract type
     * @return immutable list of matching beans, never {@code null}
     */
    public static <T> List<T> resolveAll(ApplicationContext context, Class<T> spiType) {
        return List.copyOf(beansOfType(context, spiType).values());
    }

    /**
     * Resolve the first bean of the given public SPI type or fail with a clear
     * message when the host has not wired the contract.
     *
     * @param context application context
     * @param spiType public SPI contract type
     * @return the first matching bean
     * @throws IllegalStateException when no matching bean is registered
     */
    public static <T> T require(ApplicationContext context, Class<T> spiType) {
        T first = resolveFirst(context, spiType);
        if (first == null) {
            throw new IllegalStateException(
                    "No bean of public SPI type [" + spiType.getName() + "] registered. "
                            + "Check that the host application wires the eqadmin SPI contracts.");
        }
        return first;
    }

    private static <T> Map<String, T> beansOfType(ApplicationContext context, Class<T> spiType) {
        if (context == null) {
            return Map.of();
        }
        return context.getBeansOfType(spiType);
    }
}
