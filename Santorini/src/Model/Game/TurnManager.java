package Model.Game;

import Model.Action.Action;
import Model.Board.Board;
import Model.Board.Cell;
import Model.Board.Position;
import Model.Player.Player;
import Model.Player.Worker;

import java.util.List;
import java.util.Scanner;

public class TurnManager {

    private final Player[] players;
    private int currentPlayerIndex;
    private Integer playerSelectedWorkerID;
    private TurnPhase phase;

    public TurnManager(Player[] players) {
        this.players = players;
        this.currentPlayerIndex = 0;
        this.phase = TurnPhase.START_TURN;
        this.playerSelectedWorkerID = null;
    }

    public void playTurn(GameState gameState) {

        Player currentPlayer = players[currentPlayerIndex];

        switch (phase) {
            case START_TURN:
                onStartTurn();
                break;
            case SELECT_WORKER:
                onSelectWorker();
                break;
            case MOVE:
                onMove(gameState);
                break;
            case BUILD:
                onBuild(gameState);
                break;
            case MOVE_OR_BUILD:
                onMoveOrBuild(gameState);
                break;
            case OPTIONAL_ACTION:
                onOptionalAction(gameState);
                break;
            case END_TURN:
                onEndTurn();
                gameState.process();  //FIXME: remove on production, this is just for testing
                break;
        }

//        gameState.process(); // uncomment this on live
    }

    private void onStartTurn(){

        System.out.println("Start Turn");
        this.phase = TurnPhase.SELECT_WORKER; // no additional proces, direct pass the turn to select worker phase
        return;
    }

    private void onSelectWorker(){

        Scanner scanner = new Scanner(System.in);

        while (playerSelectedWorkerID == null) {
            // Show the available workers
            System.out.println("Player " + getCurrentPlayer().getName() + ", please select a worker:");
            for (Worker worker : getCurrentPlayer().getWorkers()) {
                System.out.println(worker.getId() + ". Worker " + worker.getId());
            }

            // Prompt the player to choose a worker
            int choice = scanner.nextInt();

            // Validate input (1 or 2)
            if (choice == 1 || choice == 2) {
                Worker selectedWorker = getCurrentPlayer().getWorkerByID(choice);
                playerSelectedWorkerID = selectedWorker.getId();
                System.out.println("Player (" + getCurrentPlayer().getName() + ") selected worker " + selectedWorker.getId());

                // Move to the next phase
                phase = TurnPhase.MOVE;
            } else {
                System.out.println("Invalid choice, please select a valid worker.");
                onSelectWorker();  // Retry if input is invalid
            }
        }

        this.phase = TurnPhase.MOVE; // once worker selected, process to move phase
    }

    private void onMove(GameState gameState) {
        List<Action> moveActions = gameState.getMovesAction(getCurrentPlayer().getWorkerByID(playerSelectedWorkerID));

        System.out.println("Available Move Actions:");
        for (int i = 0; i < moveActions.size(); i++) {
            System.out.println((i + 1) + ": " + moveActions.get(i));  // Display move options to the player
        }

        // Prompt the player to choose a move action
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        // Validate input (check if the choice is within the range)
        if (choice >= 1 && choice <= moveActions.size()) {
            Action selectedAction = moveActions.get(choice - 1);
            selectedAction.execute(gameState); // Now using the execute method from MoveAction
            System.out.println("Move executed: " + selectedAction);
            phase = selectedAction.getNextPhase(); // Move to the next phase based on the action
        } else {
            System.out.println("Invalid choice, please select a valid move action.");
            onMove(gameState);  // Retry if input is invalid
        }
    }

    private void onBuild(GameState gameState) {
        System.out.println("Build Stage");

        List<Action> buildActions = gameState.getBuildAction(getCurrentPlayer().getWorkerByID(playerSelectedWorkerID));

        System.out.println("Available Build Actions:");
        for (int i = 0; i < buildActions.size(); i++) {
            System.out.println((i + 1) + ": " + buildActions.get(i));  // Display build options to the player
        }

        // Prompt the player to choose a build action
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        // Validate input (check if the choice is within the range)
        if (choice >= 1 && choice <= buildActions.size()) {
            Action selectedAction = buildActions.get(choice - 1);
            selectedAction.execute(gameState); // Execute the build action using its execute method
            System.out.println("Build executed: " + selectedAction);
            phase = selectedAction.getNextPhase(); // Move to the next phase based on the action
        } else {
            System.out.println("Invalid choice, please select a valid build action.");
            onBuild(gameState);  // Retry if input is invalid
        }
    }

    private void onMoveOrBuild(GameState gameState) {
        List<Action> moveActions = gameState.getMovesAction(getCurrentPlayer().getWorkerByID(playerSelectedWorkerID));
        List<Action> buildActions = gameState.getBuildAction(getCurrentPlayer().getWorkerByID(playerSelectedWorkerID));

        System.out.println("Available Move Actions:");
        for (int i = 0; i < moveActions.size(); i++) {
            System.out.println((i + 1) + ": Move - " + moveActions.get(i));  // Display move options to the player
        }

        System.out.println("Available Build Actions:");
        for (int i = 0; i < buildActions.size(); i++) {
            System.out.println((i + 1 + moveActions.size()) + ": Build - " + buildActions.get(i));  // Display build options to the player
        }

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice >= 1 && choice <= moveActions.size()) {
            Action selectedMoveAction = moveActions.get(choice - 1);
            selectedMoveAction.execute(gameState); // Execute the selected move action
            System.out.println("Move executed: " + selectedMoveAction);
            phase = selectedMoveAction.getNextPhase(); // Move to the next phase based on the action
        } else if (choice > moveActions.size() && choice <= (moveActions.size() + buildActions.size())) {
            Action selectedBuildAction = buildActions.get(choice - 1 - moveActions.size());
            selectedBuildAction.execute(gameState); // Execute the selected build action
            System.out.println("Build executed: " + selectedBuildAction);
            phase = selectedBuildAction.getNextPhase(); // Move to the next phase based on the action
        } else {
            System.out.println("Invalid choice, please select a valid action.");
            onMoveOrBuild(gameState);  // Retry if input is invalid
        }
    }


    private void onOptionalAction(GameState gameState) {

    }

    private void onEndTurn(){
        System.out.println("End Turn");
        currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
        playerSelectedWorkerID = null;
        phase = TurnPhase.START_TURN;
    }

    public TurnPhase getPhase() {
        return phase;
    }

    public Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    public Player getOpponent(Player player) {
        for (Player p : players) {
            if (!p.equals(player)) {
                return p;
            }
        }
        return null;
    }
}
