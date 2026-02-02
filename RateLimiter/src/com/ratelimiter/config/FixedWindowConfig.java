package com.ratelimiter.config;

import com.ratelimiter.enums.RateLimiterType;

public class FixedWindowConfig implements RateLimiterConfig {

    private final int maxRequests;
    private final long maxWindowTime;

    public FixedWindowConfig(int maxRequests, long maxWindowTime) {
        this.maxRequests = maxRequests;
        this.maxWindowTime = maxWindowTime;
    }

    public int getMaxRequests() {
        return maxRequests;
    }

    public long getMaxWindowTime() {
        return maxWindowTime;
    }

    @Override
    public RateLimiterType getType() {
        return RateLimiterType.FIXED_WINDOW;
    }
}

