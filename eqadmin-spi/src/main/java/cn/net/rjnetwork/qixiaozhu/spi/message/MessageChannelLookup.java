package cn.net.rjnetwork.qixiaozhu.spi.message;

import cn.net.rjnetwork.qixiaozhu.message.constant.MessageChannelType;

import java.util.List;

/**
 * Public lookup contract for registered message channels.
 *
 * <p>Host implementations bridge the internal channel registry and always
 * expose channels through the public {@link MessageChannel} contract, so
 * plugins never depend on host-internal factory beans.</p>
 */
public interface MessageChannelLookup {

    /**
     * Resolve a registered channel by its channel type code (case-insensitive).
     *
     * @param channelType channel type code, e.g. {@code EMAIL} / {@code SMS}
     * @return the registered channel, or {@code null} when not registered
     */
    MessageChannel getChannel(String channelType);

    /**
     * Resolve a registered channel by its channel type enum.
     *
     * @param channelType channel type enum
     * @return the registered channel, or {@code null} when not registered
     */
    default MessageChannel getChannel(MessageChannelType channelType) {
        return channelType == null ? null : getChannel(channelType.getCode());
    }

    /**
     * Check whether a channel type code is registered.
     */
    default boolean hasChannel(String channelType) {
        return getChannel(channelType) != null;
    }

    /**
     * Check whether a channel type code is registered and available.
     */
    default boolean isChannelAvailable(String channelType) {
        MessageChannel channel = getChannel(channelType);
        return channel != null && channel.isAvailable();
    }

    /**
     * List all currently registered channels.
     *
     * @return immutable list of registered public channels
     */
    List<MessageChannel> getAllChannels();

    /**
     * Count of currently registered channels.
     */
    default int getChannelCount() {
        return getAllChannels().size();
    }
}
