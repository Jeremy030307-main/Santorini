package Model.Mode.TimedMode;

import java.util.Timer;
import java.util.TimerTask;

public class PlayerClock {

    private long remainingTimeMs;
    private Timer timer;
    private Runnable onTimeout;

    public PlayerClock(long initialTimeMs, Runnable onTimeout) {
        this.remainingTimeMs = initialTimeMs;
        this.onTimeout = onTimeout;
    }

    public void start() {
        if (timer != null) stop();

        timer = new Timer();
        final long[] lastUpdate = {System.currentTimeMillis()}; // Use array to mutate inside inner class


        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                long now = System.currentTimeMillis();
                long elapsed = now - lastUpdate[0];
                lastUpdate[0] = now; // update for next cycle

                remainingTimeMs -= elapsed;

                if (remainingTimeMs <= 0) {
                    remainingTimeMs = 0;
                    stop();
                    onTimeout.run();
                }
            }
        }, 0, 100); // check every 100ms
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public long getRemainingTimeMs() {
        return remainingTimeMs;
    }
}

