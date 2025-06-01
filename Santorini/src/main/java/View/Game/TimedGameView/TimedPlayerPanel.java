package View.Game.TimedGameView;

import View.Game.BasicGameView.ActivePlayerPanel;
import View.Game.MapComponent.JPlayer;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;

public class TimedPlayerPanel extends ActivePlayerPanel {

    private JLabel timerLabel;
    private Timer timer;
    private Integer timeRemaining; // in seconds

    public TimedPlayerPanel(JPlayer player, String playerName, String godName, int width) {
        super(player, playerName, godName, width);
        timeRemaining = null;
    }

    public void startTimer(int seconds) {
        if (timer != null) {
            timer.cancel(); // Cancel any previous timer
        }

        this.timeRemaining = seconds;

        if (timerLabel == null) {
            timerLabel = new JLabel(formatTime(timeRemaining));
            timerLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
            timerLabel.setForeground(Color.WHITE);
            timerLabel.setOpaque(false);
            add(timerLabel);
        }

        positionTimerLabel(); // Set bounds
        repaint();

        timer = new Timer();
        timer.scheduleAtFixedRate(new java.util.TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    timeRemaining--;
                    if (timeRemaining >= 0) {
                        timerLabel.setText(formatTime(timeRemaining));
                        positionTimerLabel();
                    } else {
                        timer.cancel();
                    }
                });
            }
        }, 0, 1000);
    }

    private String formatTime(int seconds) {
        int mins = seconds / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d", mins, secs);
    }

    private void positionTimerLabel() {
        int x = getWidth() - 60; // Adjust margin as needed
        int y = 10;
        timerLabel.setBounds(x, y, 50, 20);
    }

    @Override
    public void setPlayerActive(boolean updatedPlayerActive, String actionMessage) {
        super.setPlayerActive(updatedPlayerActive, actionMessage);

        if (timer != null){
            if (updatedPlayerActive) {
                startTimer(timeRemaining);
            } else {
                timer.cancel();
            }
        }
    }
}
