package cn.net.rjnetwork.qixiaozhu.spi.tts;

import cn.net.rjnetwork.qixiaozhu.tts.TtsSynthesizeRequest;
import cn.net.rjnetwork.qixiaozhu.tts.TtsSynthesizeResult;

/**
 * Public client contract for speech synthesis.
 *
 * <p>Plugins call {@link #synthesize(TtsSynthesizeRequest)} to turn text into
 * an audio URL. The host resolves the active vendor configuration, injects
 * credentials, synthesizes and persists the audio, then returns an accessible
 * URL; plugins never touch vendor SDKs, credentials or storage internals.</p>
 *
 * <p><b>Availability</b>: TTS is an optional host capability. Plugins must
 * check {@link #available()} first and degrade gracefully (hide the speaker
 * affordance) when it returns {@code false} or when the bean itself is not
 * wired, instead of failing the surrounding business flow.</p>
 */
public interface TtsClient {

    /**
     * Whether at least one TTS vendor configuration is enabled and usable.
     *
     * @return {@code true} when {@link #synthesize(TtsSynthesizeRequest)} can run
     */
    boolean available();

    /**
     * Synthesize speech and persist it to the host storage platform.
     *
     * @param request synthesize payload; only business fields ({@code text} and
     *                optionally {@code voice}/{@code model}/{@code format}) are
     *                required, credentials are injected by the host
     * @return synthesize result carrying {@code audioUrl}; never {@code null}
     */
    TtsSynthesizeResult synthesize(TtsSynthesizeRequest request);

    /**
     * Synthesize speech and return the raw audio bytes without persisting.
     *
     * @param request synthesize payload
     * @return synthesize result carrying {@code audioBytes}; never {@code null}
     */
    TtsSynthesizeResult synthesizeRaw(TtsSynthesizeRequest request);
}
