package com.mkmt.retrofitmvvm;

import java.util.concurrent.ScheduledExecutorService;

public class Executors {
    private static Executors executorsInstance;

    public static Executors getExecutorsInstance() {
        if (executorsInstance == null) {
            executorsInstance = new Executors();
        }
        return executorsInstance;
    }

    private final ScheduledExecutorService scheduledExecutorService = java.util.concurrent.Executors.newScheduledThreadPool(3);

    public ScheduledExecutorService getScheduledExecutorService() {
        return scheduledExecutorService;
    }
}
