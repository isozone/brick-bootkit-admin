package cn.net.rjnetwork.qixiaozhu.oss;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Public OSS configuration info model.
 *
 * <p>Safe projection of the host OSS config record: credentials
 * ({@code accessKey}/{@code secretKey}) are intentionally excluded. Plugins
 * use this model to discover configured storage platforms and their
 * metadata.</p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OssConfigInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /** Platform display name, unique. */
    private String platformName;

    /** Platform type code, e.g. {@code ALIYUN_OSS} / {@code MINIO}. */
    private String platformType;

    /** Platform description. */
    private String platformDesc;

    /** Bucket name. */
    private String bucketName;

    /** Endpoint. */
    private String endpoint;

    /** Public domain. */
    private String domain;

    /** Base path within the bucket. */
    private String basePath;

    /** Region. */
    private String region;

    /** Whether this platform is the default one. */
    private Boolean isDefault;

    /** Config status. */
    private String status;

    /** Whether the platform is enabled. */
    private Boolean enabled;
}
