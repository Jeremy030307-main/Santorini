package Model.Game;

import Model.Board.Board;
import Model.Board.Cell;
import Model.Board.Position;
import Model.Player.Player;
import Model.Player.Worker;

import java.util.List;
import java.util.Scanner;

public class SetupManager {

    private final Player[] players;
    private final Board board;

    private int currentPlayerIndex = 0;
    private int currentWorkerIndex = 0;

    public SetupManager(Player[] players, Board board) {
        this.players = players;
        this.board = board;
    }

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
        System.out.println("Setup: " + currentPlayerIndex + " " + currentWorkerIndex);
        return players[currentPlayerIndex].getWorkers()[currentWorkerIndex];
    }
}
