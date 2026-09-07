package cn.net.rjnetwork.qixiaozhu.spi.datascope;

import cn.net.rjnetwork.qixiaozhu.vo.UserPerm;

import java.util.Set;

/**
 * Public data-permission query contract.
 *
 * <p>Pairs with {@link DataScopeContributor} (which declares which tables carry
 * scope columns) to form the complete data-permission SPI face: contributors
 * declare the boundary, this provider exposes the runtime scope so plugins can
 * filter their own queries by the same organization boundary as host tables.</p>
 */
public interface DataScopeProvider {

    /**
     * Snapshot of the current request's organization scope context.
     *
     * @return current context, never {@code null}; when no context is present
     *         {@code contextPresent} is {@code false} and sets are empty
     */
    DataScopeContext getContext();

    /**
     * Resolve the data-permission settings of a user.
     *
     * @param userId account id
     * @return the user's data scope, never {@code null}; when the user has no
     *         recorded permission an empty {@link UserPerm} is returned
     */
    UserPerm getUserDataScope(Long userId);

    /**
     * Resolve the organization ids a user can access, mirroring the host
     * scope resolver (super admin / ALL permission expands to everything).
     *
     * @param userId account id
     * @return accessible organization ids, or {@code null} when the scope is
     *         unrestricted (super admin or ALL permission)
     */
    Set<Long> resolveAccessibleOrgIds(Long userId);

    /**
     * Organization scope snapshot of the current request.
     *
     * @param userId            current user id, may be {@code null}
     * @param companyId         current company boundary id, may be {@code null}
     * @param deptId            current department boundary id, may be {@code null}
     * @param contextPresent    whether an organization context is present
     * @param unrestricted      whether the current scope is unrestricted
     * @param denyAll           whether the current scope denies all rows
     * @param accessibleOrgIds  accessible organization ids, never {@code null}
     */
    record DataScopeContext(
            Long userId,
            Long companyId,
            Long deptId,
            boolean contextPresent,
            boolean unrestricted,
            boolean denyAll,
            Set<Long> accessibleOrgIds
    ) {
    }
}
