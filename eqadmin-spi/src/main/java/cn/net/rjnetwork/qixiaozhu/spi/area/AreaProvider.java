package cn.net.rjnetwork.qixiaozhu.spi.area;

import cn.net.rjnetwork.qixiaozhu.area.AreaNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Public administrative-area query contract.
 *
 * <p>Area master data lives in the platform database
 * ({@code qixiaozhu_area}) and is maintained by a host page. Plugins read it
 * through this contract instead of copying it: a copy would need its own
 * ingestion job, version pointer and rollback path, and every one of those
 * exists only because the data was not reachable in the first place.</p>
 *
 * <p>Codes are the fixed 12-digit GB/T 2260 form (province 2 + city 2 +
 * county 2 + town 3 + village 3, right-padded with zeros). Callers may pass a
 * 6- or 9-digit code anywhere a code is accepted; implementations normalize it.
 * Because the padding is on the right, an ancestor lookup is a string prefix:
 * everything under Hangzhou is {@code 3301*}.</p>
 */
public interface AreaProvider {

    /** Highest supported level (village/community). */
    int MAX_LEVEL = 5;

    /**
     * Direct children of a node, ordered by {@code sortNo} then code.
     *
     * @param parentCode      parent code; {@code null} or blank means the roots (provinces)
     * @param includeDisabled whether to include disabled rows; pass {@code true} when
     *                        resolving historical references, {@code false} when offering choices
     * @return children, never {@code null}
     */
    List<AreaNode> getChildren(String parentCode, boolean includeDisabled);

    /**
     * Resolve one node by code.
     *
     * @param code area code in 6-, 9- or 12-digit form
     * @return the node, or {@code null} when the code is unknown or malformed
     */
    AreaNode getByCode(String code);

    /**
     * Search by name fragment, for pickers.
     *
     * @param keyword name fragment, matched against the node's own name
     * @param level   optional level filter (1..{@value #MAX_LEVEL})
     * @param limit   maximum rows to return; implementations clamp it to a sane bound
     * @return matching nodes, never {@code null}
     */
    List<AreaNode> search(String keyword, Integer level, int limit);

    /**
     * Fingerprint of the current data set (version plus row count).
     *
     * <p>Caches key on it so a plugin can tell "the platform edited 西湖区" from
     * "nothing changed since my last read" in one cheap call.</p>
     *
     * @return opaque version string, never {@code null}
     */
    String dataVersion();

    /**
     * Direct children, enabled rows only — the common case for user-facing pickers.
     *
     * @param parentCode parent code; {@code null} or blank means the roots
     * @return children, never {@code null}
     */
    default List<AreaNode> getChildren(String parentCode) {
        return getChildren(parentCode, false);
    }

    /**
     * Whether a code exists at all, disabled rows counting as existing.
     *
     * @param code area code in any supported digit form
     * @return {@code true} when the platform knows this code
     */
    default boolean exists(String code) {
        return getByCode(code) != null;
    }

    /**
     * Walk from the root down to a node.
     *
     * <p>This is the bubble-up primitive: a demand filed in a district is offered
     * to the city and the province by walking this list, so callers need the whole
     * chain, not just the parent.</p>
     *
     * @param code area code in any supported digit form
     * @return root-first chain, empty when the code is unknown
     */
    default List<AreaNode> getPath(String code) {
        AreaNode node = getByCode(code);
        if (node == null) {
            return List.of();
        }
        List<AreaNode> path = new ArrayList<>(MAX_LEVEL);
        AreaNode cursor = node;
        // level is the loop bound on purpose: a hand-edited cycle in parent_code
        // must not hang a request thread.
        while (cursor != null && path.size() < MAX_LEVEL) {
            path.add(cursor);
            String parent = cursor.getParentCode();
            if (parent == null || parent.isBlank()) {
                break;
            }
            cursor = getByCode(parent);
        }
        Collections.reverse(path);
        return path;
    }

    /**
     * Human-readable full name built from the ancestor chain.
     *
     * @param code area code in any supported digit form
     * @return full name such as {@code 浙江省杭州市西湖区}, or empty string when unknown
     */
    default String fullName(String code) {
        StringBuilder out = new StringBuilder();
        for (AreaNode node : getPath(code)) {
            String name = node.getName();
            if (name == null || name.isBlank()) {
                continue;
            }
            // Municipalities repeat themselves (北京市北京市); dropping the repeated
            // segment keeps group titles from reading like a data fault.
            int tail = Math.min(out.length(), name.length());
            if (tail > 0 && out.substring(out.length() - tail).equals(name)) {
                continue;
            }
            out.append(name);
        }
        return out.toString();
    }
}
