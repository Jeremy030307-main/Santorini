package Controller;

import Model.GodCard.GodCardFactory;
import View.SantoriniFrame;
import View.Setup.PlayerChooseGodsPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerChooseGodController {

    public static final String PLAYER_CHOOSE_GOD_VIEW = "playerChooseGodView";

    private PlayerChooseGodsPanel playerChooseGodsPanel;
    private final SantoriniFrame mainFrame;
    private final GameBuilderController gameBuilderController;

    private final List<GodCardFactory> selectedGods;

    public PlayerChooseGodController(SantoriniFrame mainFrame, GameBuilderController gameBuilderController) {
        this.selectedGods = gameBuilderController.getGameBuilder().getChosenGods();
        this.mainFrame = mainFrame;
        this.gameBuilderController = gameBuilderController;
    }

    public void start() {

        List<String> godCardImagePath = new ArrayList<>();
        for (GodCardFactory god: selectedGods) {
            godCardImagePath.add(matchImage(god));
        }
        this.playerChooseGodsPanel = new PlayerChooseGodsPanel(godCardImagePath);
        this.mainFrame.addView(this.playerChooseGodsPanel, PLAYER_CHOOSE_GOD_VIEW);
        addChooseGodsListener();
        addNextButtonListener();

        mainFrame.showView(PLAYER_CHOOSE_GOD_VIEW);
    }

    private void addChooseGodsListener(){
        for (int i = 0; i < selectedGods.size(); i++) {

            GodCardFactory god = selectedGods.get(i);
            JButton godButton = playerChooseGodsPanel.getGodCardButtons().get(i);
            godButton.addActionListener(e -> {
                handleGodCardSelected(godButton, god);
            });
        }
    }

    private void handleGodCardSelected(JButton godButton, GodCardFactory god) {
        if (gameBuilderController.getGameBuilder().onPlayerSelectGodCard(god)) {
            // Selection successful - apply selected visual effect
            System.out.println("Selected");
            for (JButton button : playerChooseGodsPanel.getGodCardButtons()) {
                if (button.equals(godButton)) {
                    button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
                } else {
                    resetGodButtonAppearance(button);  // Reset all other buttons
                }
                button.repaint();  // Repaint each button
            }
        } else {
            // Selection failed - reset only this button
            resetGodButtonAppearance(godButton);
            godButton.repaint();
        }

        playerChooseGodsPanel.getNextButton().setEnabled(
                gameBuilderController.getGameBuilder().getSelectedGodCard() != null
        );
    }

    private void addNextButtonListener(){

        playerChooseGodsPanel.getNextButton().addActionListener(e -> {
            handleNextAction();
        });
    }

    private void handleNextAction(){
        gameBuilderController.getGameBuilder().onPlayerGodSelectionComplete();

        if (!gameBuilderController.getGameBuilder().isChooseGodComplete()){
            System.out.println("Continue");
            start();
        } else {
            gameBuilderController.creatGame();
        }
    }

    private void resetGodButtonAppearance(JButton button) {
        button.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10)); // Original border
        button.setContentAreaFilled(false); // Transparent
        button.setBackground(null); // No background
    }

    private String matchImage(GodCardFactory god){

        switch (god){
            case ARTEMIS -> {
                return "Asset/Image/GodCard/Artemis/card.png";
            }
            case DEMETER -> {
                return "Asset/Image/GodCard/Demeter/card.png";
            }
            default -> throw new IllegalStateException("Unexpected value: " + god);
        }
    }








}
