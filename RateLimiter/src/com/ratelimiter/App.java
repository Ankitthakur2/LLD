package com.ratelimiter;

import com.ratelimiter.config.RateLimiterConfig;
import com.ratelimiter.enums.RateLimiterType;
import com.ratelimiter.enums.UserType;
import com.ratelimiter.factory.RateLimiterConfigFactory;
import com.ratelimiter.factory.RateLimiterFactory;
import com.ratelimiter.limiter.RateLimiter;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("=== Testing Token Bucket Rate Limiter ===");
        testTokenBucket();
        
        System.out.println("\n=== Testing Fixed Window Rate Limiter ===");
        testFixedWindow();
    }
    
    private static void testTokenBucket() throws InterruptedException {
        UserType userType = UserType.FREE;
        String userId = "ankit";
        RateLimiterConfig config = RateLimiterConfigFactory.getConfig(userType, RateLimiterType.TOKEN_BUCKET);
        RateLimiter rateLimiter = RateLimiterFactory.create(config);
        
        System.out.println("Config: 5 tokens capacity, 1 token/sec refill rate");
        System.out.println("\nInitial burst of 7 requests:");
        for (int i = 1; i <= 7; i++) {
            boolean isAllowed = rateLimiter.allowRequest(userId);
            System.out.println("Request " + i + ": " + (isAllowed ? "✓ Allowed" : "✗ Rate Limited (429)"));
        }
        
        System.out.println("\nWaiting 2 seconds for token refill...");
        Thread.sleep(2000);
        
        System.out.println("After 2 seconds (should have ~2 tokens):");
        for (int i = 1; i <= 3; i++) {
            boolean isAllowed = rateLimiter.allowRequest(userId);
            System.out.println("Request " + i + ": " + (isAllowed ? "✓ Allowed" : "✗ Rate Limited (429)"));
        }
    }
    
    private static void testFixedWindow() {
        UserType userType = UserType.FREE;
        String userId = "ankit";
        RateLimiterConfig config = RateLimiterConfigFactory.getConfig(userType, RateLimiterType.FIXED_WINDOW);
        RateLimiter rateLimiter = RateLimiterFactory.create(config);
        
        System.out.println("Config: 3 requests per 5 minutes");
        for (int i = 1; i <= 5; i++) {
            boolean isAllowed = rateLimiter.allowRequest(userId);
            System.out.println("Request " + i + ": " + (isAllowed ? "✓ Allowed" : "✗ Rate Limited (429)"));
        }
    }
}

