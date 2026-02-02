package com.ratelimiter.limiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.ratelimiter.config.BucketTokenConfig;
import com.ratelimiter.model.BucketTokenState;
import com.ratelimiter.model.RateLimiterState;

public class BucketTokenLimiter implements RateLimiter{
    private final Map<String, RateLimiterState> stateMap = new ConcurrentHashMap<>();
    private final BucketTokenConfig config;

    public BucketTokenLimiter(BucketTokenConfig config)
    {
     this.config = config;
    }
    
    
    @Override
    public boolean allowRequest(String userId)
    {
        stateMap.putIfAbsent(userId, createInitialState());
        
        BucketTokenState state = (BucketTokenState) stateMap.get(userId);
        long now = System.currentTimeMillis();
        
        synchronized (state)
        {
            double tokensToAdd = (now - state.lastRefillTimestamp) * config.getFillRate() / 1000.0;
            state.token = Math.min(config.getCapacity(), state.token + tokensToAdd);
            state.lastRefillTimestamp = now;
            
            if(state.token >= 1.0)
            {
                state.token--;
                return true;
            }
        }

        return false;
    }
    
    private BucketTokenState createInitialState()
    {
        BucketTokenState state = new BucketTokenState();
        state.token = config.getCapacity(); // Start with full bucket
        state.lastRefillTimestamp = System.currentTimeMillis();
        return state;
    }

}
