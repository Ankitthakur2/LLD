package com.ratelimiter.limiter;

public interface RateLimiter {
    boolean allowRequest(String userId);
}

