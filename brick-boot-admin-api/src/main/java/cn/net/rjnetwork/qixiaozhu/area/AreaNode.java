package cn.net.rjnetwork.qixiaozhu.area;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Public administrative-area node model (GB/T 2260).
 *
 * <p>One row of the platform area tree, decoupled from the host entity so
 * plugins never touch host MyBatis-Plus types. Codes are always the fixed
 * 12-digit form (province 2 + city 2 + county 2 + town 3 + village 3,
 * right-padded with zeros), which makes "parent = code without its last
 * segment" a plain string prefix.</p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AreaNode implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Area code, normalized to 12 digits. */
    private String code;

    /** Parent area code, {@code null} for province level. */
    private String parentCode;

    /** Name of this level only; the full name is derived, see {@code AreaProvider#fullName}. */
    private String name;

    /** 1 province, 2 city, 3 county/district, 4 town/street, 5 village/community. */
    private Integer level;

    /** Order among siblings; 0 means natural order by area code. */
    private Integer sortNo;

    /** {@code enabled} or {@code disabled}; disabled rows stay queryable so existing references still resolve. */
    private String status;

    /** Whether the node has children, so a lazy tree does not have to ask again. */
    private Boolean hasChildren;
}
