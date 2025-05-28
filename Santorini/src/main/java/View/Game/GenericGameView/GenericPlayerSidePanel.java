package View.Game.GenericGameView;

import View.Game.BasicGameView.ActivePlayerPanel;
import View.Game.MapComponent.JPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public abstract class GenericPlayerSidePanel<T extends ActivePlayerPanel> extends JPanel {

    private List<T> playerPanels;
    private int activePlayerIndex;

    public GenericPlayerSidePanel(List<JPlayer> players, List<String> playerNames, List<String> godNames, int width) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        playerPanels =setPlayerPanels(players, playerNames,godNames,width);
    }

    public void updateActivePlayer(int playerIndex, String actionMessage) {

        activePlayerIndex = playerIndex;

        removeAll();
        add(Box.createVerticalStrut(40));

        int startPoint = (activePlayerIndex + 1) % playerPanels.size();

        for (int i = 0; i < playerPanels.size(); i++) {

            int truePoint = (startPoint + i) % playerPanels.size();
            ActivePlayerPanel playerPanel = playerPanels.get(truePoint);
            if (truePoint == activePlayerIndex) {
                playerPanel.setPlayerActive(true, actionMessage);
                playerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            } else {
                playerPanel.setPlayerActive(false, actionMessage);
                playerPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
            }

            add(playerPanel);
        }

        add(Box.createVerticalGlue()); // 🔥 Push everything up

    }

    public void setOptionalButton(int id, String buttonText, ActionListener actionListener) {
        playerPanels.get(id).setOptionalButton(buttonText, actionListener);
    }

    public abstract List<T> setPlayerPanels(List<JPlayer> players, List<String> playerNames, List<String> godNames, int width);

    public List<T> getPlayerPanels() {
        return playerPanels;
    }
}
