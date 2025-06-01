package Model.Mode.CoinMode;

import Model.Action.Action;
import Model.Board.Cell;
import Model.Game.GameState;
import Model.Game.TurnPhase;
import Model.Player.Worker;

public class SpendCoinAction extends Action {

    CoinManager coinManager;
    int amount;

    public SpendCoinAction(CoinManager coinManager, int amount, TurnPhase currentPhase, TurnPhase nextPhase, Worker targetWorker, Cell targetCell) {
        super("spend coin", currentPhase, nextPhase, targetWorker, targetCell);
        this.coinManager = coinManager;
        this.amount = amount;
    }

    @Override
    public void execute(GameState gameState) {
        coinManager.spendCoins(targetWorker.getOwner(), amount);
    }
}
