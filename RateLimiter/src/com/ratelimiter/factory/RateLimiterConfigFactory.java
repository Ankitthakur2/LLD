package com.ratelimiter.factory;

import com.ratelimiter.config.BucketTokenConfig;
import com.ratelimiter.config.FixedWindowConfig;
import com.ratelimiter.config.RateLimiterConfig;
import com.ratelimiter.enums.RateLimiterType;
import com.ratelimiter.enums.UserType;

public class RateLimiterConfigFactory {
    public static RateLimiterConfig getConfig(UserType userType, RateLimiterType limiterType) {
        switch (limiterType) {
            case FIXED_WINDOW:
                return getFixedWindowConfig(userType);
            case TOKEN_BUCKET:
                return getTokenBucketConfig(userType);
            default:
                throw new IllegalArgumentException("Unsupported limiter type");
        }
    }
    
    public static FixedWindowConfig getConfig(UserType userType) {
        return getFixedWindowConfig(userType);
    }
    
    private static FixedWindowConfig getFixedWindowConfig(UserType userType) {
        switch (userType) {
            case PREMIUM:
                return new FixedWindowConfig(10, 300_000);
            case FREE:
            default:
                return new FixedWindowConfig(3, 300_000);
        }
    }
    
    private static BucketTokenConfig getTokenBucketConfig(UserType userType) {
        switch (userType) {
            case PREMIUM:
                return new BucketTokenConfig(10, 2.0);
            case FREE:
            default:
                return new BucketTokenConfig(5, 1.0);
        }
    }
}

