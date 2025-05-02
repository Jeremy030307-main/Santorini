package Controller.GameFlow;

import Controller.HomeController;
import Model.GodCard.GodCardFactory;
import Model.Player.Player;
import View.Game.MapComponent.JPlayer;
import View.GameOver.WinPanel;
import View.SantoriniFrame;

public class GameOverController {

    public static final String GAME_OVER_VIEW = "gameOverView";

    private WinPanel winPanel;
    private final SantoriniFrame mainFrame;


    public GameOverController(SantoriniFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void showWinPanel(Player player) {

        this.winPanel = new WinPanel(JPlayer.from(player.getWorkerColor().toString()), player.getName(), matchCardImage(player.getGodCard().getName()));
        mainFrame.addView(winPanel,  GAME_OVER_VIEW);
        mainFrame.showView(GAME_OVER_VIEW);

        winPanel.getBackButton().addActionListener(e -> mainFrame.showView(HomeController.HOME_VIEW));
    }

    private String matchCardImage(GodCardFactory god){

        switch (god){
            case ARTEMIS -> {
                return "Asset/Image/GodCard/Artemis/podium.png";
            }
            case DEMETER -> {
                return "Asset/Image/GodCard/Demeter/podium.png";
            }
            default -> throw new IllegalStateException("Unexpected value: " + god);
        }
    }


}
