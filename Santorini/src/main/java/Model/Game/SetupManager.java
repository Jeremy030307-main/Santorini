package Model.Game;

import Model.Board.Board;
import Model.Board.Cell;
import Model.Board.Position;
import Model.Player.Player;
import Model.Player.Worker;

import java.util.List;

public class SetupManager {

    public final static String ADD_WORKER_TEXT = "Add A Worker";


    private final Player[] players;
    private final Board board;

    private int currentPlayerIndex = 0;
    private int currentWorkerIndex = 0;

    public SetupManager(Player[] players, Board board) {
        this.players = players;
        this.board = board;
    }

    public List<Cell> getUnoccupiedCells() {
        return board.getUnoccupiedCells();
    };

    public void placeWorker(Position position){
        Cell targetCell = board.getCell(position);
        getCurrentPlayer().placeWorker(getCurrentWorker(), targetCell);

        // Advanced to the next worker
        currentWorkerIndex++;
        if (currentWorkerIndex >= 2){
            currentPlayerIndex++;
            currentWorkerIndex = 0;
        }
    }

    public boolean isSetupComplete() {
        return currentPlayerIndex >= players.length;
    }

    public Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    public Worker getCurrentWorker() {
        return players[currentPlayerIndex].getWorkers()[currentWorkerIndex];
    }
}
