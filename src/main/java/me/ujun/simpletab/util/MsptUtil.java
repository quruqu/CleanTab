package me.ujun.simpletab.util;

// MsptUtil.java
import org.bukkit.Bukkit;

import java.util.Arrays;

public final class MsptUtil {
    private MsptUtil() {
    }

    public static double averageMspt() {
        return Bukkit.getServer().getAverageTickTime(); // 이미 ms 단위
    }

    public static double averageMspt(int lastNTicks) {
        long[] nanos = Bukkit.getServer().getTickTimes(); // ns
        int n = Math.min(lastNTicks, nanos.length);
        if (n <= 0) return 0.0;
        long sum = 0L;
        for (int i = 0; i < n; i++) sum += nanos[i];
        return sum / 1_000_000.0 / n; // ms
    }

    /**
     * 최근 샘플의 p퍼센타일 MSPT (예: p=95, 99)
     */
    public static double percentileMspt(double p) {
        long[] nanos = Bukkit.getServer().getTickTimes();
        if (nanos.length == 0) return 0.0;
        // 사본 만들어 정렬 (ns → ms 변환은 나중에)
        long[] copy = Arrays.copyOf(nanos, nanos.length);
        Arrays.sort(copy); // 오름차순
        int idx = (int) Math.ceil(p / 100.0 * copy.length) - 1;
        idx = Math.max(0, Math.min(idx, copy.length - 1));
        return copy[idx] / 1_000_000.0; // ms
    }
}
