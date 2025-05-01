package Model.Game;

import Model.Action.Action;
import Model.Board.Board;
import Model.GameRule.ClassicGameRule;
import Model.Player.Player;
import Model.Player.Worker;

import java.util.List;

public class GameState {

    private final Board board;
    private final ClassicGameRule gameRule;
    private final Player[] players;
    private final BlockPool blockPool;
    private final TurnManager turnManager;
    private final SetupManager setupManager;

    private boolean gameOver;

    public GameState(Board board, Player[] players, ClassicGameRule rule, BlockPool blockPool, TurnManager turnManager, SetupManager setupManager) {
        this.board = board;
        this.gameRule = rule;
        this.players = players;
        this.blockPool = blockPool;
        this.turnManager = turnManager;
        this.setupManager = setupManager;
        this.gameOver = false;
    }

    public List<Action> getMovesAction(Worker worker) {
        return gameRule.moveActions(board, worker);
    }

    public List<Action> getBuildAction(Worker worker) {
        return gameRule.buildActions(board, worker);
    }

    public void process(){
        if (gameRule.checkWin(this)){
            gameOver = true;
        };

        gameRule.checkLose(this);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    // Getter and Setter
    public Board getBoard() {
        return board;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Player getPlayerById(int id) {
        for (Player player : players) {
            if (player.getId() == id) {
                return player;
            }
        }
        return null;
    }

    public SetupManager getSetupManager() {
        return setupManager;
    }

    public BlockPool getBlockPool() {
        return blockPool;
    }

    public TurnManager getTurnManager() {
        return turnManager;
    }
}
