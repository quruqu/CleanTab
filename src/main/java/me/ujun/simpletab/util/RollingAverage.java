package me.ujun.simpletab.util;

class RollingAverage {
    private final double[] ring;
    private int idx = 0;
    private boolean filled = false;
    private double sum = 0.0;

    RollingAverage(int capacity) {
        this.ring = new double[capacity];
    }

    void add(double v) {
        if (filled) {
            sum -= ring[idx];
        }
        ring[idx] = v;
        sum += v;
        idx = (idx + 1) % ring.length;
        if (idx == 0) filled = true;
    }

    double avg() {
        int n = filled ? ring.length : idx;
        return n == 0 ? 0.0 : (sum / n);
    }

    int size() {
        return filled ? ring.length : idx;
    }
}
