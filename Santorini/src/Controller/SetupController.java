package Controller;

import Controller.GameFlow.GameController;
import Model.Board.Cell;
import Model.Board.Position;
import Model.Game.SetupManager;
import Model.Player.Worker;
import View.Game.GamePanel;
import View.Game.MapComponent.JBoard;
import View.Game.MapComponent.JCell;
import View.Game.MapComponent.JCellAction;
import View.Game.MapComponent.JWorker;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

public class SetupController {

    private final SetupManager setupManager;
    private final GamePanel gamePanel;
    private final GameController gameController;
    private final Map<JCell, MouseListener> attachedListeners = new HashMap<>();

    public SetupController(SetupManager setupManager, GamePanel gamePanel, GameController gameController) {
        this.setupManager = setupManager;
        this.gamePanel = gamePanel;
        this.gameController = gameController;
    }

    public void setup(){

        if (setupManager.isSetupComplete()) {
            gameController.startGame();
            return;
        }

        gameController.setCurrentPlayerIndex(setupManager.getCurrentPlayer().getId());
        gameController.updateGamePanel(gameController.getGame().getGameState(), SetupManager.ADD_WORKER_TEXT);
        updateUIForCurrentPhase();
    }

    private void updateUIForCurrentPhase(){
        JBoard boardDisplay = gamePanel.getGameBoard();
        gameController.setCurrentPlayerIndex(setupManager.getCurrentPlayer().getId());

        for  (Cell cell : setupManager.getUnoccupiedCells()) {
            Position pos = cell.getPosition();
            JCell cellDisplay = boardDisplay.getCell(pos.x(), pos.y());

            cellDisplay.setAction(JCellAction.CHOOSE_WORKER);

            addListener(cellDisplay, new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    placeWorker(pos);
                    clearListeners();
                    setup();
                }
            });
        }
    }

    public void placeWorker(Position pos){

        Worker currentWorker = setupManager.getCurrentWorker();
        setupManager.placeWorker(pos);  // place the worker in the core game logic
        JCell cellDisplay = gamePanel.getGameBoard().getCell(pos.x(), pos.y());
        cellDisplay.setWorker(JWorker.from(currentWorker.getWorkerColor().toString(), currentWorker.getGender().toString())); // place the worker in the GUI

        // post action after setting the worker
        cellDisplay.clearAction();
        removeListener(cellDisplay);
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
    }

}
