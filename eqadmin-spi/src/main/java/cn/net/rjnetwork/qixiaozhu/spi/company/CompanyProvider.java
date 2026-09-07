package cn.net.rjnetwork.qixiaozhu.spi.company;

import cn.net.rjnetwork.qixiaozhu.company.CompanyInfo;

import java.util.List;

/**
 * Public company provider contract for plugins.
 */
public interface CompanyProvider {

    /**
     * Resolve a company by id.
     *
     * @param id company id
     * @return the company, or {@code null} when not found
     */
    CompanyInfo getById(Long id);

    /**
     * Resolve the company id that a department belongs to.
     *
     * @param deptId department id
     * @return the owning company id, or {@code null} when not found
     */
    Long queryCompanyIdByDeptId(Long deptId);

    /**
     * List all companies.
     *
     * @return all companies, never {@code null}
     */
    List<CompanyInfo> list();
}
