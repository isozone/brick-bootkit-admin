# Provider Contract Candidates（插件依赖调研结果）

> 生成日期：2026-09-07
> 方法：统计 `/Users/vim/Desktop/codes/plugins/` 下全部插件（business/cms/crm/geo/asset/xianyu/wemprss 等）
> 对 `cn.net.rjnetwork.qixiaozhu` 宿主包的直接 `import`，按引用次数排序。

## 结论摘要

- 插件大量 import 的 `cn.net.rjnetwork.qixiaozhu.plugins.*`（business/geo/crm/cms.generic/wemprss/xianyu/asset 的 entity/mapper/service）
  定义在**插件仓自身**（非宿主），属于插件生态共享 SDK，**不是宿主 Provider 契约候选**；如需治理应另建公共插件 SDK 产物。
- 插件真正直接依赖的**宿主内部服务**集中在：字典、文件、OSS 配置、账号、公司、数据权限、超管判定——这些是 Provider 契约的候选清单。

## A. Provider 契约候选（SPI，按插件引用次数排序）

| 候选契约 | 现被直接引用的宿主内部类 | 引用数 | 建议公开面 |
|---|---|---|---|
| `DictionaryProvider` | `service.IQixiaozhuDictService` | 8 | 字典项查询（按字典码/分组） |
| `FileDetailProvider`（或 `FileStorageClient`） | `service.IFileDetailService` | 7 | 文件详情/上传/URL 解析 |
| `OssConfigProvider` | `service.IQixiaozhuOssConfigService` | 5 | OSS 配置查询（平台类型/参数） |
| `AccountQueryProvider` | `service.IQixiaozhuAccountService` | 5 | 账号查询（扩展 `CurrentAccountProvider` 语义） |
| `DataScopeProvider`（并入数据权限方向） | `db.service.DataPermInfoService` / `service.OrganizationDataPermissionService` / `db.service.QixiaozhuAccountCompanyConcatService` / `db.service.QixiaozhuAccountDataPermsConcatsService` | 3+3+3+1 | 数据权限/组织边界查询（与 `DataScopeContributor` SPI 配套） |
| `PermissionProvider` | `service.SuperAdminResolver` / `service.AdminService` | 2+1 | 超管/权限判定 |
| 扩充 `CompanyProvider` | `service.IQixiaozhuCompanyService` | 3 | 现有公开接口缺方法，插件仍直连内部 service |

## B. 公共模型（DTO/VO）候选

| 模型 | 现状 | 建议 |
|---|---|---|
| `AccountVO` | 宿主 `entity.QixiaozhuAccount` 被插件 **20 处** import 当 DTO 用 | 在 `brick-boot-admin-api` 补公开账号模型（含公司/部门关联） |
| 字典/OSS 配置 DTO | 随 SPI 设计按需补充 | — |

## C. 已排除项

- `core.constpara.BaseConst`（22 处）——已在公共 `eqadmin-core-constants` 模块，非候选
- `cn.net.rjnetwork.qixiaozhu.plugins.*`——插件仓自建共享包，非宿主契约
- `spi.*` / `account.*` / `company.*` / `annotation.*` / `result.*` 等——已是公开契约

## 建议执行顺序

1. `DictionaryProvider` + `AccountVO`（引用最多、模型最简）
2. `FileDetailProvider` + `OssConfigProvider`（宿主已有 oss 模块适配基础）
3. `DataScopeProvider`（与 `DataScopeContributor` 一并设计，见 roadmap）
4. `PermissionProvider` / 扩充 `CompanyProvider`（视插件真实调用面再细化）
