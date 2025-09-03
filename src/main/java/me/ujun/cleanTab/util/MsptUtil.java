package me.ujun.cleanTab.util;

// MsptUtil.java
import org.bukkit.Bukkit;

import java.util.Arrays;

public final class MsptUtil {
    private MsptUtil() {
    }

    public static double averageMspt() {
        return Bukkit.getServer().getAverageTickTime();
    }

    public static double averageMspt(int lastNTicks) {
        long[] nanos = Bukkit.getServer().getTickTimes(); // ns
        int n = Math.min(lastNTicks, nanos.length);
        if (n <= 0) return 0.0;
        long sum = 0L;
        for (int i = 0; i < n; i++) sum += nanos[i];
        return sum / 1_000_000.0 / n; // ms
    }
}
