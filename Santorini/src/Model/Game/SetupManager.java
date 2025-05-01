package Model.Game;

import Model.Board.Board;
import Model.Board.Cell;
import Model.Board.Position;
import Model.Player.Player;
import Model.Player.Worker;

import java.util.List;
import java.util.Scanner;

/**
 * The {@code SetupManager} class is responsible for managing the setup of the game.
 * It handles placing workers for each player on the board and keeping track of the 
 * current player and worker being placed.
 * <p>
 * This class ensures that workers are placed in valid positions, and the setup 
 * continues until all workers for all players are placed.
 * </p>
 */
public class SetupManager {

    private final Player[] players;
    private final Board board;

    private int currentPlayerIndex = 0;
    private int currentWorkerIndex = 0;

    public SetupManager(Player[] players, Board board) {
        this.players = players;
        this.board = board;
    }

    /**
     * Starts the setup process by placing workers for each player.
     * Currently, the worker placement logic is commented out and needs to be
     * implemented in the {@link #placeWorkers()} method.
     */
    public void setup(){
//        placeWorkers();
    }

//    private void placeWorkers() {
//
//        for (Player player : players) {
//            System.out.println("Setting up workers for " + player.getName());
//
//            for(Worker worker : player.getWorkers()){
//                boolean placed = false;
//
//                while (!placed) {
//                    System.out.println("Place worker " + (worker.getId()) +
//                            " for " + player.getName() +
//                            " (format: x y)");
//
//                    try {
//                        String input = scanner.nextLine();
//                        String[] parts = input.split(" ");
//
//                        if (parts.length != 2) {
//                            System.out.println("Invalid input format. Please enter two numbers.");
//                            continue;
//                        }
//
//                        int x = Integer.parseInt(parts[0]);
//                        int y = Integer.parseInt(parts[1]);
//
//                        if (!board.isInBounds(x, y)) {
//                            System.out.println("Invalid position. Out of bounds");
//                            continue;
//                        }
//
//                        if (board.getCell(new Position(x, y)).isOccupied()) {
//                            System.out.println("Invalid position. The cell is occupied.");
//                            continue;
//                        }
//
//                        Cell targetCell = board.getCell(new Position(x, y));
//                        player.placeWorker(worker, targetCell);
//                        placed = true;
//
//                    } catch (NumberFormatException e) {
//                        System.out.println("Please enter numbers only.");
//                    }
//                }
//            }
//        }
//    }

    /**
     * Retrieves a list of all unoccupied cells on the board.
     *
     * @return A list of unoccupied cells
     */
    public List<Cell> getUnoccupiedCells() {
        return board.getUnoccupiedCells();
    };

    /**
     * Places a worker for the current player at the specified position on the board.
     *
     * @param position The position where the worker should be placed
     */
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

    /**
     * Checks if the setup process is complete, i.e., all players have placed all their workers.
     *
     * @return {@code true} if setup is complete, {@code false} otherwise
     */
    public boolean isSetupComplete() {
        return currentPlayerIndex >= players.length;
    }

    /**
     * Retrieves the current player who is placing a worker.
     *
     * @return The current player
     */
    public Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    /**
     * Retrieves the current worker being placed for the current player.
     *
     * @return The current worker
     */
    public Worker getCurrentWorker() {
        System.out.println("Setup: " + currentPlayerIndex + " " + currentWorkerIndex);
        return players[currentPlayerIndex].getWorkers()[currentWorkerIndex];
    }
}
