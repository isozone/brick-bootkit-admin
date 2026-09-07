package cn.net.rjnetwork.qixiaozhu.spi.datascope;

/**
 * Public contract letting plugins and consumers declare the organization-scope
 * columns of their own tables.
 *
 * <p>The host collects all {@link DataScopeContributor} beans at startup and
 * merges their table declarations into the global data-permission whitelist,
 * so plugin tables participate in the same organization/tenant boundary as
 * host tables without touching host code.</p>
 *
 * <p>Contribution is declarative: implement {@link #contribute(DataScopeRegistry)}
 * and register your tables through the provided registry.</p>
 */
public interface DataScopeContributor {

    /**
     * Declare the scope columns of your tables.
     *
     * @param registry registry accepting table-scope declarations
     */
    void contribute(DataScopeRegistry registry);

    /**
     * Column pair used to scope a table to accessible organizations.
     *
     * @param companyColumn company/tenant boundary column, may be {@code null}
     * @param deptColumn    department boundary column, may be {@code null}
     */
    record DataScopeColumns(String companyColumn, String deptColumn) {

        public static DataScopeColumns of(String companyColumn, String deptColumn) {
            return new DataScopeColumns(companyColumn, deptColumn);
        }

        /** Default company/dept boundary ({@code company_id} / {@code dept_id}). */
        public static DataScopeColumns companyDept() {
            return new DataScopeColumns("company_id", "dept_id");
        }
    }

    /**
     * Registry accepting table-scope declarations from contributors.
     */
    interface DataScopeRegistry {

        /**
         * Register the scope columns of a table.
         *
         * @param tableName table name, normalized to lower case by the host
         * @param columns   scope columns for the table
         */
        void register(String tableName, DataScopeColumns columns);

        /**
         * Register tables with the default company/dept boundary
         * ({@code company_id} / {@code dept_id}).
         */
        default void registerCompanyDepartment(String... tableNames) {
            registerTables(DataScopeColumns.companyDept(), tableNames);
        }

        /**
         * Register tables with the tenant/department boundary
         * ({@code tenant_id} / {@code department_id}).
         */
        default void registerTenantDepartment(String... tableNames) {
            registerTables(DataScopeColumns.of("tenant_id", "department_id"), tableNames);
        }

        /**
         * Register tables with a tenant-only boundary ({@code tenant_id}).
         */
        default void registerTenantOnly(String... tableNames) {
            registerTables(DataScopeColumns.of("tenant_id", null), tableNames);
        }

        private void registerTables(DataScopeColumns columns, String... tableNames) {
            if (columns == null || tableNames == null) {
                return;
            }
            for (String tableName : tableNames) {
                if (tableName != null && !tableName.isBlank()) {
                    register(tableName.trim(), columns);
                }
            }
        }
    }
}
