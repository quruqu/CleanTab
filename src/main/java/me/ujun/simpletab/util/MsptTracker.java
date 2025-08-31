package me.ujun.simpletab.util;

public class MsptTracker {
    private volatile int mspt = 0;

    public void setMspt(long nanos) {
        this.mspt = (int) (nanos / 1_000_000); // ns → ms 변환 후 int 저장
    }

    public int getMspt() {
        return mspt;
    }

}
