package cn.net.rjnetwork.qixiaozhu.starter.autoconfigure;

import cn.net.rjnetwork.qixiaozhu.starter.spi.SpiRegistry;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * Public auto-configuration facade for the eqadmin SPI contracts.
 *
 * <p>Registered via {@code META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports}
 * and exposed by default in every Spring Boot application that depends on
 * {@code eqadmin-starter-api}. It provides a default {@link SpiRegistry} bean
 * collecting all public SPI implementations from the application context;
 * hosts can override the bean with their own registry.</p>
 */
@AutoConfiguration
public class EqadminSpiAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(SpiRegistry.class)
    public SpiRegistry spiRegistry(ApplicationContext applicationContext) {
        return new ApplicationContextSpiRegistry(applicationContext);
    }

    /**
     * Default {@link SpiRegistry} implementation backed by the application
     * context bean lookup.
     */
    static final class ApplicationContextSpiRegistry implements SpiRegistry {

        private final ApplicationContext applicationContext;

        ApplicationContextSpiRegistry(ApplicationContext applicationContext) {
            this.applicationContext = applicationContext;
        }

        @Override
        public <T> List<T> getAll(Class<T> spiType) {
            return List.copyOf(applicationContext.getBeansOfType(spiType).values());
        }

        @Override
        public <T> T getFirst(Class<T> spiType) {
            List<T> all = getAll(spiType);
            return all.isEmpty() ? null : all.get(0);
        }

        @Override
        public boolean has(Class<?> spiType) {
            return !applicationContext.getBeansOfType(spiType).isEmpty();
        }
    }
}
