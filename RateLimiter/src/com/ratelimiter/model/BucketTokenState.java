package com.ratelimiter.model;

public class BucketTokenState implements RateLimiterState{
    public double token;
    public long lastRefillTimestamp;
}
