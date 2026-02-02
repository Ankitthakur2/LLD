package com.ratelimiter.model;

public class FixedWindowState implements RateLimiterState {
    public int count;
    public long startWindowTime;
}

