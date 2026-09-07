package cn.net.rjnetwork.qixiaozhu.storage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Public file detail model.
 *
 * <p>Safe projection of the host file record, used by plugins to resolve file
 * metadata (url, size, ext, platform, thumbnail, business object relation)
 * without depending on host service beans or storage-library internals.</p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDetailInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /** Access url. */
    private String url;

    /** File size in bytes. */
    private Long size;

    /** Stored file name. */
    private String filename;

    /** Original file name. */
    private String originalFilename;

    /** Base path. */
    private String basePath;

    /** Relative path. */
    private String path;

    /** File extension. */
    private String ext;

    /** Storage platform name. */
    private String platform;

    /** Thumbnail url. */
    private String thUrl;

    /** Thumbnail file name. */
    private String thFilename;

    /** Thumbnail size in bytes. */
    private Long thSize;

    /** Business object id. */
    private String objectId;

    /** Business object type. */
    private String objectType;

    /** Company boundary id, optional. */
    private Long companyId;

    /** Department boundary id, optional. */
    private Long deptId;

    private Date createTime;
}
