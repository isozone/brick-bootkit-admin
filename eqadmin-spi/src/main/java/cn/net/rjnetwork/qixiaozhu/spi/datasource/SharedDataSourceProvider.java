package cn.net.rjnetwork.qixiaozhu.spi.datasource;

import javax.sql.DataSource;

/**
 * Public shared datasource contract.
 *
 * <p>Hosts implement this contract so plugins can obtain the host's primary
 * {@code DataSource} (or a clean clone of it) without reflecting into the host
 * Spring context for a bean named {@code mainDataSource}. Plugins typically use
 * this when they share the host database schema for auxiliary tables.</p>
 */
public interface SharedDataSourceProvider {

    /**
     * @return the host primary {@link DataSource}, never {@code null}
     */
    DataSource getMainDataSource();
}
