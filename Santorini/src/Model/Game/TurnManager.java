package Model.Game;

import Model.Board.Board;
import Model.Player.Player;

public class TurnManager {

    private final Player[] players;
    private int currentPlayerIndex;
    private TurnPhase phase = TurnPhase.START_TURN;
    private boolean gameOver;
    private Player winner;

    public TurnManager(Player[] players) {
        this.players = players;
        this.currentPlayerIndex = 0;
    }

    //TODO: implement the flow for a play turn in a game
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
                onMove();
                break;
            case BUILD:
                onBuild();
                break;
            case MOVE_OR_BUILD:
                onMoveOrBuild();
                break;
            case CHECK_WIN:
                onCheckWin();
                break;
            case OPTIONAL_ACTION:
                onOptionalAction();
            case END_TURN:
                onEndTurn();
                break;
        }
    }

    private void onStartTurn(){

    }

    private void onSelectWorker(){

    }

    private void onMove(){

    }

    private void onBuild(){

    }

    private void onMoveOrBuild(){
    }

    private void onCheckWin(){

    }

    private void onOptionalAction(){

    }

    private void onEndTurn(){
        currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
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

    public boolean isGameOver() {
        return gameOver;
    }

    public Player getWinner() {
        return winner;
    }

}
