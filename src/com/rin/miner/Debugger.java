package com.rin.miner;

/**
 * Created by Hamda on 5/23/2017.
 */
public class Debugger {
    // calculate execution time
    private static long startTime;
    private static long endTime;

    // calculate memory usage
    private static long beforeUsedMemory;
    private static long afterUsedMemory;

    public static void setStartTime() {
        startTime = System.currentTimeMillis();
    }

    public static void setEndTime() {
        endTime = System.currentTimeMillis();
    }

    public static long executionTime() {
        return endTime - startTime;
    }

    public static void setBeforeUsedMemory() {
        beforeUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    public static void setAfterUsedMemory() {
        afterUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    public static long memoryUsage() {
        return afterUsedMemory - beforeUsedMemory;
    }
}
