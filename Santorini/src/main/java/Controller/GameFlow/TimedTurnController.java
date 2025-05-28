package Controller.GameFlow;

import Model.Game.GameState;
import Model.Mode.TimedMode.TimedTurnManager;
import View.Game.BasicGameView.GamePanel;
import View.Game.TimedGameView.TimedGamePanel;
import View.Game.TimedGameView.TimedPlayerPanel;

public class TimedTurnController extends TurnController<TimedTurnManager> {

    private final TimedGamePanel timedGamePanel;
    private final TimedTurnManager turnManager;

    public TimedTurnController(TimedTurnManager turnManager, TimedGamePanel gamePanel, GameController gameController) {
        super(turnManager, gamePanel, gameController);
        this.timedGamePanel = gamePanel;
        this.turnManager = turnManager;
    }

    @Override
    protected void startTurn(GameState gameState) {
        super.startTurn(gameState);

        System.out.println("set timer");
        timedGamePanel.getPlayersPanel()
                .getPlayerPanels()
                .get(turnManager.getCurrentPlayer().getId())
                .startTimer((int) turnManager.getRemainingSeconds(turnManager.getCurrentPlayer()));

    }


}
