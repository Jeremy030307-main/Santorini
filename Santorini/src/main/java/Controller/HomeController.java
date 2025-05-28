package Controller;

import Model.Game.GameMode;
import View.Home.HomePanel;
import View.SantoriniFrame;

public class HomeController {

    public static final String HOME_VIEW = "homeView";

    private final HomePanel homePanel;
    private final SantoriniFrame mainFrame;

    public HomeController(SantoriniFrame  santoriniFrame) {
        this.homePanel = new HomePanel();
        this.mainFrame = santoriniFrame;
        initListeners();
    }

    private void initListeners() {
        mainFrame.addView(homePanel, HOME_VIEW);
        homePanel.getPlayButton().addActionListener(e -> startNewGame());
        homePanel.getLoadButton().addActionListener(e -> loadGame());
    }

    private void startNewGame() {
//        GameBuilderController gameBuilderController = new GameBuilderController(mainFrame);
//        gameBuilderController.challengerChooseGod();
        SetupController setupController = new SetupController(mainFrame, GameMode.TWO_PLAYER);
        setupController.start();
    }

    private void loadGame() {
        // Add loading logic here
    }
}
