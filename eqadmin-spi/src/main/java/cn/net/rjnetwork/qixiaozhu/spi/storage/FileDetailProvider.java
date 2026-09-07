package cn.net.rjnetwork.qixiaozhu.spi.storage;

import cn.net.rjnetwork.qixiaozhu.storage.model.FileDetailInfo;

import java.util.List;

/**
 * Public file detail query contract.
 *
 * <p>Hosts implement this contract on top of their internal file record
 * service so plugins can resolve uploaded file metadata by url or by business
 * object relation, without depending on host service beans.</p>
 */
public interface FileDetailProvider {

    /**
     * Resolve a file record by its access url.
     *
     * @param url file access url
     * @return the file record, or {@code null} when not found
     */
    FileDetailInfo getByUrl(String url);

    /**
     * List file records bound to a business object.
     *
     * @param objectId   business object id
     * @param objectType business object type
     * @return matching file records, never {@code null}
     */
    List<FileDetailInfo> listByObject(String objectId, String objectType);
}
