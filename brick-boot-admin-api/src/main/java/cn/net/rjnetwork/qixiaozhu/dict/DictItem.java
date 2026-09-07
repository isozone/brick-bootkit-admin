package cn.net.rjnetwork.qixiaozhu.dict;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Public dictionary item model.
 *
 * <p>Mirrors the host dictionary entity in a vendor-neutral form: a dictionary
 * group ({@code groupCode}) holds items identified by {@code code} with a
 * display {@code name} and a {@code value}.</p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DictItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Dictionary item id. */
    private Long id;

    /** Dictionary group code. */
    private String groupCode;

    /** Dictionary item code, unique within the group. */
    private String code;

    /** Display name. */
    private String name;

    /** Item value. */
    private String value;

    /** Description. */
    private String description;

    /** Item status. */
    private String status;
}
