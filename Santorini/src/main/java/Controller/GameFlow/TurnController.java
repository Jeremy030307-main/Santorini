package Controller.GameFlow;

import Model.Action.Action;
import Model.Action.ActionList;
import Model.Action.OptionalAction;
import Model.Board.Position;
import Model.Game.GameState;
import Model.Game.TurnManager.TurnManager;
import Model.Player.Worker;
import View.Game.BasicGameView.GamePanel;
import View.Game.GenericGameView.GenericGamePanel;
import View.Game.MapComponent.JCell;
import View.Game.MapComponent.JCellAction;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class TurnController<T extends TurnManager> {

    protected final TurnManager turnManager;
    protected final GenericGamePanel gamePanel;
    protected final GameController gameController;
    protected final Map<JCell, MouseListener> attachedListeners = new HashMap<>();

    public TurnController(T turnManager, GenericGamePanel gamePanel, GameController gameController) {
        this.turnManager = turnManager;
        this.gamePanel = gamePanel;
        this.gameController = gameController;
    }

    public void processTurn(GameState gameState) {

        if (gameState.isGameOver()) {
            System.out.println("Game Over");
            gameController.gameOver();  // handle over for game controller to control the game over
            return;
        }

        gameController.setCurrentPlayerIndex(turnManager.getCurrentPlayer().getId());
        gameController.updateGamePanel(gameController.getGame().getGameState(), turnManager.getPhase().getPhaseText());
        updateUIForCurrentPhase(gameState);
    }

    private void updateUIForCurrentPhase(GameState gameState) {
        switch (turnManager.getPhase()) {
            case START_TURN:
                startTurn(gameState);
                break;
            case SELECT_WORKER:
                showWorkerSelection(turnManager.getCurrentPlayer().getWorkers(), gameState);
                break;
            case MOVE:
//                showWorkerSelection(turnManager.getUnselectedWorker(), gameState);

                ActionList moveActions = turnManager.getMoveActions(gameState);
                showWorkerAction(moveActions, gameState, JCellAction.MOVE);
                showOptionalButton(moveActions.getOptionalAction(), gameState);
                break;

            case BUILD:
                ActionList buildActions = turnManager.getBuildActions(gameState);
                showWorkerAction(buildActions, gameState, JCellAction.BUILD);
                showOptionalButton(buildActions.getOptionalAction(), gameState);
                break;
            case MOVE_OR_BUILD:
                break;
            case OPTIONAL_ACTION:
                ActionList optionalActions = turnManager.getOptionalActions(gameState);
                if (!optionalActions.isEmpty()) {
                    gameController.updateGamePanel(gameController.getGame().getGameState(), turnManager.getPhase().getPhaseText() + ": " +optionalActions.getFirst().getCurrentPhase().getPhaseText());
                    showWorkerAction(optionalActions, gameState, JCellAction.USE_POWER);
                    showOptionalButton(optionalActions.getOptionalAction(),  gameState);
                } else {
                    turnManager.handleAction(optionalActions.getOptionalAction(), gameState);
                    processTurn(gameState);
                }
                break;
            case END_TURN:
                turnManager.onEndTurn();
                processTurn(gameState);
                break;
        }
    }

    protected void startTurn(GameState gameState) {
        turnManager.onStartTurn();
        processTurn(gameState);
    }

    private void showWorkerSelection(Worker[] workers, GameState gameState) {
        for (Worker worker : workers) {

            Position currPos = worker.getLocatedCell().getPosition();
            JCell displayCell = gamePanel.getGameBoard().getCell(currPos.x(), currPos.y());

            displayCell.setAction(JCellAction.CHOOSE_WORKER);
            addListener(displayCell, new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    turnManager.handleWorkerSelection(worker.getId());
                    clearListeners();
                    processTurn(gameState);
                }
            });
        }
    }

    private void showWorkerAction(ActionList actions, GameState gameState, JCellAction cellAction) {
        for (Action action : actions) {

            Position currPos = action.getTargetCell().getPosition();
            JCell displayCell = gamePanel.getGameBoard().getCell(currPos.x(), currPos.y());

            displayCell.setAction(cellAction);
            addListener(displayCell, new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    turnManager.handleAction(action, gameState);
                    clearListeners();
                    processTurn(gameState);
                }
            });
        }
    }

    private void showOptionalButton(OptionalAction action, GameState gameState) {

        if (action != null) {
            ActionListener optionalButtonListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    turnManager.handleAction(action, gameState);
                    clearListeners();
                    processTurn(gameState);
                    ((JButton) e.getSource()).removeActionListener(this);
                }
            };

            gamePanel.setPlayerOptionalButton(turnManager.getCurrentPlayer().getId(),
                    action.getActionName(),
                    optionalButtonListener);
        }
    }

    private void addListener(JCell cellDisplay,  MouseListener listener){
        cellDisplay.addMouseListener(listener);
        attachedListeners.put(cellDisplay, listener);
    }

    private void removeListener(JCell cellDisplay){
        MouseListener listener = attachedListeners.remove(cellDisplay);
        cellDisplay.removeMouseListener(listener);
    }

    private void clearListeners(){
        for (Map.Entry<JCell, MouseListener> entry : attachedListeners.entrySet()) {
            JCell cell = entry.getKey();
            MouseListener listener = entry.getValue();

            cell.removeMouseListener(listener);
            cell.clearAction();
        }
        attachedListeners.clear();  // Important to clear the map after removal

        // update the board view
        gamePanel.getGameBoard().update();
        attachedListeners.clear();
    }

}
