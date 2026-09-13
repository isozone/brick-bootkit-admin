package cn.net.rjnetwork.qixiaozhu.spi.tenant;

import java.util.Set;

/**
 * Public tenant / data-permission context contract.
 *
 * <p>Hosts implement this contract so plugins can read the current request's
 * tenant and data-permission context without depending on host-internal
 * {@code ThreadLocal} holders such as {@code OrganizationTenantContextHolder}
 * or {@code DataPermissionContextHolder}. Plugins must read this context at
 * request time (the values are request-scoped) and never cache instances.</p>
 */
public interface TenantContextProvider {

    /**
     * @return the current user id, or {@code null} when no context is bound
     */
    Long getUserId();

    /**
     * @return the current username, or {@code null}
     */
    String getUsername();

    /**
     * @return the current company (tenant) id, or {@code null}
     */
    Long getCompanyId();

    /**
     * @return the current department id, or {@code null}
     */
    Long getDeptId();

    /**
     * @return ids of organizations the current user can access; empty set when unrestricted
     */
    Set<Long> getAccessibleOrgIds();

    /**
     * @return company ids the current user can access; empty set when unrestricted
     */
    Set<Long> getAccessibleCompanyIds();

    /**
     * @return {@code true} when the current user is a super admin (bypasses all data scope)
     */
    boolean isSuperAdmin();

    /**
     * @return {@code true} when data permission is enabled for the current request
     */
    boolean isDataPermissionEnabled();

    /**
     * @return {@code true} when a tenant/data-permission context is bound to the current thread
     */
    boolean hasContext();
}
