package Controller;

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
        GameController gameController = new GameController(mainFrame);
        mainFrame.showView(GameController.GAME_VIEW);
        gameController.startGame();
    }

    private void loadGame() {
        System.out.println("Loading game...");
        // Add loading logic here
    }
}
