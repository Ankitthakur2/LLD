package com.ratelimiter.config;

import com.ratelimiter.enums.RateLimiterType;

public class BucketTokenConfig implements RateLimiterConfig{

    private final int capacity;
    private final double fillRate;
    
    public BucketTokenConfig(int capacity, double fillRate)
    {
      this.capacity = capacity;
      this.fillRate = fillRate;
    }

    public int getCapacity()
    {
        return capacity;
    }

    public double getFillRate()
    {
        return fillRate;
    }
    
    
    
    
    @Override

    public RateLimiterType getType()
    {
        return RateLimiterType.TOKEN_BUCKET;
    }
    
}
