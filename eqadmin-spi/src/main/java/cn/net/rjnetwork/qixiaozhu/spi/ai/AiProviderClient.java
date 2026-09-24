package cn.net.rjnetwork.qixiaozhu.spi.ai;

import java.util.List;
import java.util.function.Consumer;

/**
 * Public client for host-managed AI providers.
 *
 * <p>Plugins (e.g. eq-admin-business) call this to invoke chat / stream against
 * the providers configured centrally in the host (main app), instead of
 * maintaining their own provider credentials and {@code zcj_ai_provider} tables.
 * The host resolves the active provider, injects credentials, routes the
 * protocol adapter, applies rate-limit / quota / circuit-breaker / cache, and
 * returns the normalized text — plugins never touch vendor SDKs or secrets.</p>
 *
 * <p><b>Availability</b>: AI is an optional host capability. Plugins must check
 * {@link #available()} first and degrade gracefully (hide the AI affordance)
 * when it returns {@code false} or when the bean itself is not wired, instead of
 * failing the surrounding business flow.</p>
 *
 * @since 2026-09-24
 */
public interface AiProviderClient {

    /**
     * Whether at least one enabled text-capable provider is configured and usable.
     *
     * @return {@code true} when {@link #chat(ChatRequest)} / {@link #chatStream(ChatRequest, Consumer)}
     * can run
     */
    boolean available();

    /**
     * Lightweight summary of all providers (admin UIs / capability probing).
     */
    List<ProviderInfo> listProviders();

    /**
     * The default enabled provider, or {@code null} when none is configured.
     */
    ProviderInfo getDefaultProvider();

    /**
     * Provider by id, or {@code null} when missing / deleted.
     */
    ProviderInfo getProvider(Long id);

    /**
     * Synchronous text chat. Never throws for upstream errors — callers inspect
     * {@link ChatResult#success()} and {@link ChatResult#error()}.
     *
     * <p>Provider resolution: {@code req.providerId} wins; otherwise the default
     * enabled provider is used. The model is resolved by {@code req.modelName}
     * within that provider (first enabled model when the name is blank).</p>
     */
    ChatResult chat(ChatRequest req);

    /**
     * Streaming text chat. Emits incremental text deltas via {@code onToken};
     * an error event is delivered as a final token carrying the error message.
     * Never throws for upstream errors.
     */
    void chatStream(ChatRequest req, Consumer<String> onToken);

    /**
     * 图片生成（文生图 / 图生图），经宿主 Agnes 等图片适配器完成。
     * 返回图片 URL 列表；失败时在 {@link ImageResult#error()} 携带原因。
     *
     * <p>Provider 解析同 {@link #chat(ChatRequest)}；{@code req.img2img} 为 true 且
     * {@code req.inputImageUrl} 非空时按图生图处理。</p>
     */
    ImageResult generateImage(ImageRequest req);

    /**
     * 只读厂商摘要，不含任何凭据字段。
     */
    record ProviderInfo(
            Long id,
            String name,
            String protocol,
            String capability,
            boolean enabled,
            boolean isDefault
    ) {
    }

    /**
     * 一次调用请求：厂商 + 模型名 + 系统/用户提示 + 最大 token。
     */
    record ChatRequest(
            Long providerId,
            String modelName,
            String systemPrompt,
            String userPrompt,
            int maxTokens
    ) {
    }

    /**
     * 调用结果：成功时 {@link #content()} 为文本，失败时 {@link #error()} 为原因。
     */
    record ChatResult(boolean success, String content, String error) {
        public static ChatResult ok(String content) {
            return new ChatResult(true, content, null);
        }

        public static ChatResult fail(String error) {
            return new ChatResult(false, null, error);
        }
    }

    /**
     * 图片生成请求：厂商 + 模型名 + 提示词 + 尺寸 + 图生图输入图。
     */
    record ImageRequest(
            Long providerId,
            String modelName,
            String prompt,
            String size,
            String inputImageUrl,
            boolean img2img
    ) {
    }

    /**
     * 图片生成结果：成功时 {@link #urls()} 为图片地址列表，失败时 {@link #error()} 为原因。
     */
    record ImageResult(boolean success, List<String> urls, String error) {
        public static ImageResult ok(List<String> urls) {
            return new ImageResult(true, urls, null);
        }

        public static ImageResult fail(String error) {
            return new ImageResult(false, null, error);
        }
    }
}
