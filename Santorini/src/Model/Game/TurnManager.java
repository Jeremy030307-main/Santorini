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
                onBuild();
                break;
            case MOVE_OR_BUILD:
                onMoveOrBuild();
                break;
            case OPTIONAL_ACTION:
                onOptionalAction();
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

        // retrieve the possible move action for the selected worker
        List<Action> moveActions = gameState.getMovesAction(getCurrentPlayer().getWorkerByID(playerSelectedWorkerID));

        // Display the available move actions
        System.out.println("Available Move Actions:");
        for (int i = 0; i < moveActions.size(); i++) {
            Action action = moveActions.get(i);
            System.out.println((i + 1) + ": " + action);  // Display move options to the player
        }

        // TODO: selection of move actions

        this.phase = TurnPhase.BUILD;
    }

    private void onBuild(){
        System.out.println("Build Stage");

        // TODO: selection of build actions
        this.phase = TurnPhase.END_TURN;
    }

    private void onMoveOrBuild(){

        // TODO: same as onMove and onBuild, but this phase has both move and build action
    }

    private void onOptionalAction(){

        // TODO: implement after on phase 2 production where god card introduced
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
