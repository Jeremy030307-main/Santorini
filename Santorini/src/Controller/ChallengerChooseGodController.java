package Controller;

import Model.GodCard.GodCardFactory;
import View.SantoriniFrame;
import View.Setup.ChallengerChooseGodsPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChallengerChooseGodController {

    public static final String CHALLENGER_CHOOSE_GODS_VIEW = "challengerChooseGodsView";

    private final ChallengerChooseGodsPanel menuPanel;
    private final SantoriniFrame mainFrame;
    private final GameBuilderController gameBuilderController;

    private final List<GodCardFactory> allGods;

    public ChallengerChooseGodController(SantoriniFrame mainFrame, GameBuilderController gameBuilderController) {
        this.allGods = GodCardFactory.getAllGods();

        List<String> godCardImagePath = new ArrayList<>();
        for (GodCardFactory god: allGods) {
            godCardImagePath.add(matchImage(god));
        }
        this.gameBuilderController = gameBuilderController;
        this.menuPanel = new ChallengerChooseGodsPanel(godCardImagePath);
        this.mainFrame = mainFrame;
        this.mainFrame.addView(menuPanel, CHALLENGER_CHOOSE_GODS_VIEW);
    }

    public void start() {
        mainFrame.showView(CHALLENGER_CHOOSE_GODS_VIEW);
        addChooseGodsListener();
        addNextButtonListener();
    }

    private void addChooseGodsListener(){
        for (int i = 0; i < allGods.size(); i++) {

            GodCardFactory god = allGods.get(i);
            JButton godButton = menuPanel.getGodCardButtons().get(i);
            godButton.addActionListener(e -> {
                handleGodCardSelected(godButton, god);
            });
        }
    }

    private void handleGodCardSelected(JButton godButton, GodCardFactory god) {
        if (gameBuilderController.getGameBuilder().onChallengerSelectGodCard(god)) {
            // Selection successful - apply selected visual effect
            godButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true)); // Gold border
        } else {
            // Selection failed - reset to normal appearance
            resetGodButtonAppearance(godButton);
        }

        menuPanel.getNextButton().setEnabled(gameBuilderController.getGameBuilder().isChooseGodsComplete());

        godButton.repaint();
        godButton.revalidate();
    }

    private void addNextButtonListener(){

        menuPanel.getNextButton().addActionListener(e -> {
            handleNextAction();
        });
    }

    private void handleNextAction(){
        if (gameBuilderController.getGameBuilder().isChooseGodsComplete()){
            gameBuilderController.playerChooseGod();
        }
    }

    // Helper method to reset button appearance
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
