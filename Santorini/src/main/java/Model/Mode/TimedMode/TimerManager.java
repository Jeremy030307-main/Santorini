package Model.Mode.TimedMode;

import Model.Player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class TimerManager {

    private static final long TOTAL_TIME_MS = 15 * 60 * 1000; // 15 minutes
    private final Map<Player, PlayerClock> clocks = new HashMap<>();

    public TimerManager(Player[] players, Runnable gameOverEvent) {

        for (Player p: players) {
            clocks.put(p, new PlayerClock(TOTAL_TIME_MS, () -> gameOverEvent.run()));
        }
    }

    public void startTimer(Player currentPlayer){
        clocks.get(currentPlayer).start();
    }

    public void stopTimer(Player currentPlayer){
        clocks.get(currentPlayer).stop();
    }

    public boolean stillAlive(Player player) {
        return clocks.get(player).getRemainingTimeMs() > 0;
    }

    public long getRemainingSeconds(Player player){
        return clocks.get(player).getRemainingTimeMs() / 1000;
    }

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
}
