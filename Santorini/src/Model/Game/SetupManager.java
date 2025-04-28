package Model.Game;

import Model.Board.Board;
import Model.Board.Cell;
import Model.Board.Position;
import Model.Player.Player;

public class SetupManager {

    private final Player[] players;
    private final Board board;

    public SetupManager(Player[] players, Board board) {
        this.players = players;
        this.board = board;
    }

    // Setup: Assign two workers for each player manually
    public void setup(Position[] startingPositions) {
        if (startingPositions.length != players.length * 2) {
            throw new IllegalArgumentException("Invalid number of starting positions provided.");
        }

        int posIndex = 0;
        for (Player player : players) {
            for (int i = 0; i < 2; i++) {
                Position pos = startingPositions[posIndex++];
                Cell cell = board.getCell(pos);

                if (cell.isOccupied() || cell.isComplete()) {
                    throw new IllegalArgumentException("Starting position " + pos + " is invalid (occupied or dome).");
                }

                player.placeWorker(i, cell);
            }
        }
    }
}
