package cn.net.rjnetwork.qixiaozhu.spi.permission;

/**
 * Public permission judgement contract.
 *
 * <p>Exposes the host super-admin determination so plugins can gate
 * privileged operations without depending on host service beans or internal
 * account entities.</p>
 */
public interface PermissionProvider {

    /**
     * Whether the given user is a super admin.
     *
     * @param userId account id
     * @return {@code true} when the user holds the super-admin privilege
     */
    boolean isSuperAccount(Long userId);

    /**
     * Whether the current request's user is a super admin.
     *
     * @return {@code true} when the current user holds the super-admin
     *         privilege; {@code false} when no user context is present
     */
    boolean isCurrentUserSuper();
}
