package Model.Mode.CoinMode;

import Model.Action.Action;
import Model.Board.Cell;
import Model.Game.GameState;
import Model.Game.TurnPhase;
import Model.Player.Worker;

public class CollectCoinAction extends Action {

    CoinManager coinManager;
    int amount;

    public CollectCoinAction(CoinManager coinManager,int amount, TurnPhase currentPhase, TurnPhase nextPhase, Worker targetWorker, Cell targetCell) {
        super("collect coin", currentPhase, nextPhase, targetWorker, targetCell);
        this.coinManager = coinManager;
        this.amount = amount;
    }


    @Override
    public void execute(GameState gameState) {
        coinManager.collectCoinAt(targetWorker.getOwner(), targetCell.getPosition().x(), targetCell.getPosition().y());
    }
}
