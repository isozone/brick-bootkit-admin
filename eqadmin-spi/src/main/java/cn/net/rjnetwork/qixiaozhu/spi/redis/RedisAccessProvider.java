package cn.net.rjnetwork.qixiaozhu.spi.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Public Redis access contract.
 *
 * <p>Hosts implement this contract on top of their internal Redis client
 * (e.g. {@code RedisClient} backed by {@code StringRedisTemplate}) so plugins
 * can read/write Redis without depending on host-internal beans or
 * Spring Data Redis APIs. Plugins obtain an instance via
 * {@code SpiRegistry.getFirst(RedisAccessProvider.class)} or by injecting the
 * bean directly when running inside the host context.</p>
 *
 * <p>All methods are best-effort: a {@code null}/blank key yields a safe
 * default ({@code false}/{@code null}/{@code 0}) rather than throwing.
 * Implementations must be thread-safe.</p>
 */
public interface RedisAccessProvider {

    /* ────────────── String ────────────── */

    boolean set(String key, String value);

    String get(String key);

    boolean remove(String key);

    boolean setEx(String key, String value, long timeoutSeconds);

    boolean setIfAbsent(String key, String value, long timeout, TimeUnit timeUnit);

    long getExpire(String key);

    boolean hasKey(String key);

    void del(String... keys);

    long incr(String key, long delta);

    long decr(String key, long delta);

    /* ────────────── Hash ────────────── */

    Object hget(String key, String item);

    Map<Object, Object> hmget(String key);

    boolean hmset(String key, Map<String, Object> map);

    boolean hmset(String key, Map<String, Object> map, long timeoutSeconds);

    boolean hset(String key, String item, Object value);

    boolean hset(String key, String item, Object value, long timeoutSeconds);

    void hdel(String key, Object... items);

    boolean hHasKey(String key, String item);

    double hincr(String key, String item, double by);

    double hdecr(String key, String item, double by);

    /* ────────────── Set ────────────── */

    Set<String> sGet(String key);

    boolean sHasKey(String key, Object value);

    long sSet(String key, String... values);

    long sSetAndTime(String key, long timeoutSeconds, String... values);

    long sGetSetSize(String key);

    long setRemove(String key, Object... values);

    /* ────────────── List ────────────── */

    List<String> lGet(String key, long start, long end);

    long lGetListSize(String key);

    Object lGetIndex(String key, long index);

    boolean lSet(String key, String value);

    boolean lSet(String key, String value, long timeoutSeconds);

    boolean lSet(String key, List<String> value);

    boolean lSet(String key, List<String> value, long timeoutSeconds);

    boolean lUpdateIndex(String key, long index, String value);

    long lRemove(String key, long count, Object value);

    /* ────────────── Common ────────────── */

    boolean expire(String key, long timeoutSeconds);
}
