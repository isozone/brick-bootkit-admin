package cn.net.rjnetwork.qixiaozhu.spi.jwt;

/**
 * Public JWT validation contract.
 *
 * <p>Hosts implement this contract so plugins can validate access tokens and
 * revoke sessions without depending on the host-internal {@code JwtTokenProvider}.
 * The validation result is intentionally host-agnostic: callers receive a
 * boolean plus the resolved user id when valid.</p>
 */
public interface JwtTokenValidator {

    /**
     * Validate an access token and return the resolved account id when valid.
     *
     * @param accessToken the JWT access token
     * @return validation outcome, never {@code null}
     */
    TokenValidation validateAccessToken(String accessToken);

    /**
     * Revoke (blacklist) a single access token so it can no longer be used.
     *
     * @param accessToken the JWT access token to revoke
     * @return {@code true} when revoked successfully
     */
    boolean revokeAccessToken(String accessToken);

    /**
     * Resolve the remaining lifetime of a token in milliseconds.
     *
     * @param token JWT access or refresh token
     * @return remaining millis, or {@code -1} when token is invalid/expired
     */
    long getTokenRemainingTime(String token);

    /**
     * Immutable validation outcome.
     */
    final class TokenValidation {
        private final boolean valid;
        private final Long userId;
        private final String username;
        private final String reason;

        public TokenValidation(boolean valid, Long userId, String username, String reason) {
            this.valid = valid;
            this.userId = userId;
            this.username = username;
            this.reason = reason;
        }

        public boolean isValid() { return valid; }
        public Long getUserId() { return userId; }
        public String getUsername() { return username; }
        public String getReason() { return reason; }
    }
}
