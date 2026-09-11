package cn.net.rjnetwork.qixiaozhu.spi.account;

import cn.net.rjnetwork.qixiaozhu.account.AccountVO;

import java.util.Collection;
import java.util.List;

/**
 * Public account query contract.
 *
 * <p>Complementary to {@link CurrentAccountProvider} (slim current-account
 * snapshot): resolves richer account views ({@link AccountVO}) by id, id
 * batch or login account, without exposing host service beans or internal
 * entities. Credentials and internal fields are never returned.</p>
 */
public interface AccountQueryProvider {

    /**
     * Resolve the account view by user id.
     *
     * @param userId account id
     * @return the account view, or {@code null} when not found
     */
    AccountVO getById(Long userId);

    /**
     * Resolve account views by user ids.
     *
     * @param userIds account ids, may be {@code null} or empty
     * @return matching account views, never {@code null}
     */
    List<AccountVO> listByIds(Collection<Long> userIds);

    /**
     * Resolve the account view by login account name.
     *
     * @param account login account name
     * @return the account view, or {@code null} when not found
     */
    AccountVO getByAccount(String account);

    /**
     * Search account views by free-text keyword for picker-style UIs.
     *
     * <p>Host implementations typically match against login account, real
     * name, mobile and email. Callers pass a small {@code limit} and render
     * the result as a selectable list.</p>
     *
     * @param keyword free-text keyword; a blank keyword means "no filter"
     * @param limit   maximum number of results, clamped by the host
     * @return matching account views, never {@code null}
     */
    default List<AccountVO> search(String keyword, int limit) {
        return List.of();
    }
}
