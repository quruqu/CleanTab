package me.ujun.simpletab.listener;

import com.destroystokyo.paper.event.server.ServerTickEndEvent;
import com.destroystokyo.paper.event.server.ServerTickStartEvent;
import me.ujun.simpletab.util.MsptTracker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TickListener implements Listener {
    private final MsptTracker tracker;
    private long start = -1L;

    public TickListener(MsptTracker tracker) {
        this.tracker = tracker;
    }

    @EventHandler
    public void onTickStart(ServerTickStartEvent e) {
        start = System.nanoTime();
    }

    @EventHandler
    public void onTickEnd(ServerTickEndEvent e) {
        if (start > 0) {
            long end = System.nanoTime();
            tracker.setMspt(end - start);
            start = -1L;
        }
    }

}
