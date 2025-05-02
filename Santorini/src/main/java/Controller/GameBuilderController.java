package Controller;

import Controller.GameFlow.GameController;
import Model.Game.GameBuilder;
import Model.Game.GameMode;
import View.SantoriniFrame;

public class GameBuilderController {

    private static final String BUILD_GAME_VIEW = "buildGameView";

    private final GameBuilder gameBuilder;
    private final SantoriniFrame santoriniFrame;

    private final ChallengerChooseGodController challengerChooseController;
    private final PlayerChooseGodController playerChooseController;

    boolean[][] layout;

    public GameBuilderController(SantoriniFrame santoriniFrame){
        this.santoriniFrame = santoriniFrame;
        this.gameBuilder = new GameBuilder(GameMode.TWO_PLAYER);
        this.challengerChooseController = new ChallengerChooseGodController(santoriniFrame, this);
        this.playerChooseController = new PlayerChooseGodController(santoriniFrame, this);

        this.layout = new boolean[][]{
                {true, true, true, true, true},
                {true, true, true, true, true},
                {true, true, true, true, true},
                {true, true, true, true, true},
                {true, true, true, true, true}
        };
        this.gameBuilder.setBoardLayout(layout);
    }

    public void challengerChooseGod(){
        challengerChooseController.start();
    }

    public void playerChooseGod(){
        playerChooseController.start();
    }

    public void creatGame(){
        GameController gameController = new GameController(santoriniFrame, gameBuilder.buildGame(), layout);
        gameController.setupGame();
    }

    public GameBuilder getGameBuilder() {
        return gameBuilder;
    }
}
