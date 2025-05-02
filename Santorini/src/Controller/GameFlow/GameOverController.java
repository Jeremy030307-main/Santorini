package Controller.GameFlow;

import View.GameOver.WinPanel;
import View.SantoriniFrame;

public class GameOverController {

    public static final String GAME_OVER_VIEW = "gameOverView";

    private final WinPanel winPanel;
    private final SantoriniFrame mainFrame;


    public GameOverController(SantoriniFrame mainFrame) {
        this.winPanel = new WinPanel();
        this.mainFrame = mainFrame;
        mainFrame.addView(winPanel,  GAME_OVER_VIEW);
    }

    public void showWinPanel() {
        mainFrame.showView(GAME_OVER_VIEW);
    }


}
