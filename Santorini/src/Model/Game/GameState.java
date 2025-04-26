package Model.Game;

import Model.Action.Action;
import Model.Board.Board;
import Model.GameRule.ClassicGameRule;
import Model.Player.Player;

import java.util.List;

public class GameState {

    private final Board board;
    private final ClassicGameRule gameRule;
    private final Player[] players;

    private final BlockPool blockPool;
    private final TurnManager turnManager;
    private final SetupManager setupManager;

    public GameState(Board board, Player[] players, ClassicGameRule rule) {
        this.board = board;
        this.gameRule = rule;
        this.players = players;

        this.blockPool = new BlockPool();
        this.turnManager = new TurnManager(players);
        this.setupManager = new SetupManager(players, board);
    }

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

    public List<Action> getLegalActions(Player player){
        return gameRule.getLegalActions(player, this);

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

    public ClassicGameRule getGameRule() {
        return gameRule;
    }
}
