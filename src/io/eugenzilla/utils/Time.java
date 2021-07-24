package io.eugenzilla.utils;

public class Time {

    public static final long SECOND  = 1000000000l; // 1 sec in nanosec

    public static long get() {
        return System.nanoTime();
    }
}
