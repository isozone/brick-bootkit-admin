package cn.net.rjnetwork.qixiaozhu.starter.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker annotation to explicitly opt into the public eqadmin SPI
 * auto-configuration facade.
 *
 * <p>The facade is registered automatically through Spring Boot's
 * {@code AutoConfiguration.imports}; this annotation documents the intent on
 * the host application class and serves as the explicit extension point for
 * feature-flag based registration in the future.</p>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EnableEqadminSpi {
}
