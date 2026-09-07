package cn.net.rjnetwork.qixiaozhu.spi.dict;

import cn.net.rjnetwork.qixiaozhu.dict.DictItem;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Public dictionary query contract.
 *
 * <p>Hosts implement this contract on top of their internal dictionary
 * service so plugins can read dictionary items without depending on
 * host-internal service beans or MyBatis-Plus APIs.</p>
 */
public interface DictionaryProvider {

    /**
     * List all items of a dictionary group.
     *
     * @param groupCode dictionary group code
     * @return items of the group, never {@code null}
     */
    List<DictItem> getItems(String groupCode);

    /**
     * Resolve a single item by group code and item code (case-insensitive).
     *
     * @param groupCode dictionary group code
     * @param code      item code
     * @return the matching item, or {@code null} when not found
     */
    default DictItem getItem(String groupCode, String code) {
        if (groupCode == null || code == null) {
            return null;
        }
        return getItems(groupCode).stream()
                .filter(item -> item != null && code.equalsIgnoreCase(item.getCode()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Build a code-to-value map for a dictionary group.
     *
     * @param groupCode dictionary group code
     * @return ordered code-to-value map, never {@code null}
     */
    default Map<String, String> getItemMap(String groupCode) {
        Map<String, String> map = new LinkedHashMap<>();
        for (DictItem item : getItems(groupCode)) {
            if (item != null && item.getCode() != null) {
                map.put(item.getCode(), item.getValue());
            }
        }
        return map;
    }

    /**
     * Resolve the value of a single item.
     *
     * @param groupCode dictionary group code
     * @param code      item code
     * @return the item value, or {@code null} when not found
     */
    default String getValue(String groupCode, String code) {
        DictItem item = getItem(groupCode, code);
        return item == null ? null : item.getValue();
    }
}
