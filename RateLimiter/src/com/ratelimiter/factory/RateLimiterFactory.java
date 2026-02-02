package com.ratelimiter.factory;

import com.ratelimiter.config.BucketTokenConfig;
import com.ratelimiter.config.FixedWindowConfig;
import com.ratelimiter.config.RateLimiterConfig;
import com.ratelimiter.limiter.BucketTokenLimiter;
import com.ratelimiter.limiter.FixedWindowRateLimiter;
import com.ratelimiter.limiter.RateLimiter;

public class RateLimiterFactory {
    public static RateLimiter create(RateLimiterConfig config) {
        switch (config.getType()) {
            case FIXED_WINDOW:
                return new FixedWindowRateLimiter((FixedWindowConfig) config);
            case TOKEN_BUCKET:
                return new BucketTokenLimiter((BucketTokenConfig) config);
            default:
                throw new IllegalArgumentException("Unsupported type");
        }
    }
}

