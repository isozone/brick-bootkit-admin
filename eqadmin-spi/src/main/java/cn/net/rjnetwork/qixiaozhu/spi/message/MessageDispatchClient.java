package cn.net.rjnetwork.qixiaozhu.spi.message;

import cn.net.rjnetwork.qixiaozhu.message.event.MessageEvent;

/**
 * Public client contract for dispatching message events.
 *
 * <p>Plugins call {@link #dispatch(MessageEvent)} to hand a business event to
 * the host message engine. The host resolves active policies, receivers,
 * templates and channels internally and returns a generated message id;
 * plugins never touch policy/channel internals.</p>
 */
public interface MessageDispatchClient {

    /**
     * Dispatch a message event through the host message engine.
     *
     * @param event the message event to dispatch (must not be {@code null})
     * @return the generated message id; never {@code null} when accepted
     */
    String dispatch(MessageEvent event);
}
