package cn.net.rjnetwork.qixiaozhu.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Public account view model used by plugins.
 *
 * <p>Safe projection of the host account entity: credentials and internal
 * fields are intentionally excluded. Organization association
 * ({@code companyId}/{@code deptId}) and display names are included so
 * plugins can render account context without host-internal lookups.</p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String account;

    private String realName;

    private String avatar;

    private String mobileAccount;

    private String emailAccount;

    private String accountStatus;

    private String accountType;

    private String fromResource;

    private String openId;

    private String unionId;

    private Date createTime;

    private Long createUserId;

    /** Company boundary id, optional. */
    private Long companyId;

    /** Department boundary id, optional. */
    private Long deptId;

    /** Company display name, optional (filled by the host when available). */
    private String companyName;

    /** Department display name, optional (filled by the host when available). */
    private String deptName;

    /** Role id list, optional. */
    private List<Long> roleIds;
}
