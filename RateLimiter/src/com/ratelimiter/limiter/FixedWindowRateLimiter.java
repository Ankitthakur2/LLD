package com.ratelimiter.limiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.ratelimiter.config.FixedWindowConfig;
import com.ratelimiter.model.FixedWindowState;
import com.ratelimiter.model.RateLimiterState;

public class FixedWindowRateLimiter implements RateLimiter {
    private final Map<String, RateLimiterState> stateMap = new ConcurrentHashMap<>();
    private final FixedWindowConfig config;

    public FixedWindowRateLimiter(FixedWindowConfig config) {
        this.config = config;
    }

    @Override
    public boolean allowRequest(String userId) {
        long now = System.currentTimeMillis();

        stateMap.putIfAbsent(userId, new FixedWindowState());
        FixedWindowState state = (FixedWindowState) stateMap.get(userId);

        synchronized (state) {
            if (now - state.startWindowTime >= config.getMaxWindowTime()) {
                state.startWindowTime = now;
                state.count = 1;
                return true;
            }
            
            if (state.count < config.getMaxRequests()) {
                state.count++;
                return true;
            }

            return false;
        }
    }
}

