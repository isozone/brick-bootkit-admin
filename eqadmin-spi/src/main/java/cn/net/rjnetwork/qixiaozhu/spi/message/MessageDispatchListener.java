package cn.net.rjnetwork.qixiaozhu.spi.message;

import cn.net.rjnetwork.qixiaozhu.message.event.MessageEvent;
import cn.net.rjnetwork.qixiaozhu.message.event.SendResult;

/**
 * Public listener contract for message dispatch lifecycle events.
 *
 * <p>The host message engine invokes these callbacks while dispatching an
 * event, so plugins can observe or react to dispatch progress. All callbacks
 * are no-ops by default; implement only the ones you need. The host may invoke
 * multiple registered listeners in an unspecified order, and exceptions thrown
 * by a listener must never break the dispatch flow.</p>
 */
public interface MessageDispatchListener {

    /**
     * Called when a dispatch run starts for an event.
     *
     * @param messageId generated message id of this dispatch run
     * @param event     the event being dispatched
     */
    default void onDispatchStart(String messageId, MessageEvent event) {
    }

    /**
     * Called after a channel has sent a message successfully.
     *
     * @param messageId   generated message id of this dispatch run
     * @param event       the event being dispatched
     * @param channelType channel type code of the sending channel
     * @param result      send result reported by the channel
     */
    default void onChannelSuccess(String messageId, MessageEvent event, String channelType, SendResult result) {
    }

    /**
     * Called after a channel failed to send a message.
     *
     * @param messageId   generated message id of this dispatch run
     * @param event       the event being dispatched
     * @param channelType channel type code of the sending channel
     * @param result      send result reported by the channel
     */
    default void onChannelFailure(String messageId, MessageEvent event, String channelType, SendResult result) {
    }

    /**
     * Called when the whole dispatch run finishes for an event.
     *
     * @param messageId generated message id of this dispatch run
     * @param event     the event being dispatched
     */
    default void onDispatchCompleted(String messageId, MessageEvent event) {
    }
}
