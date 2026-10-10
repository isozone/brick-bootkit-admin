package cn.net.rjnetwork.qixiaozhu.spi.email;

import java.io.File;
import java.util.List;

/**
 * 邮件发送服务的 SPI 契约。
 * <p>
 * 主应用的 message 模块实现此接口，插件通过 {@code ObjectProvider<EmailServiceSpi>}
 * 获取实现，避免直接依赖 message 模块的具体类。
 * </p>
 */
public interface EmailServiceSpi {

    /**
     * 发送邮件（带附件）。
     *
     * @param configId  发件配置 ID（null 表示使用默认配置）
     * @param to        收件人数组
     * @param subject   邮件主题
     * @param content   邮件正文
     * @param html      是否为 HTML 格式
     * @param attachments 附件列表
     * @param cc        抄送人（可为 null）
     */
    void send(Long configId, String[] to, String subject, String content,
              boolean html, List<AttachmentItem> attachments, String cc);

    /**
     * 获取默认发件配置 ID。
     *
     * @return 默认配置 ID，无配置时返回 null
     */
    Long getDefaultConfigId();

    /**
     * 检查邮件服务是否可用。
     */
    default boolean isAvailable() {
        return true;
    }

    /**
     * 邮件附件。
     */
    record AttachmentItem(
            File file,
            String fileName,
            String contentType,
            long size
    ) {
    }
}
