package View.Game.MapComponent;

import java.awt.*;

/**
 * The {@code JCellAction} enum represents the different actions that can be performed on a cell in the game.
 * Each action is associated with an image and a border color for visual representation.
 * <p>
 * The available actions are BUILD, MOVE, CHOOSE_WORKER, and USE_POWER, each representing a different action
 * that the player can trigger on a cell during their turn.
 * </p>
 */
public enum JCellAction {

    BUILD("/Image/Cell/CellAction/build.png", Color.BLUE),
    MOVE("/Image/Cell/CellAction/move.png", Color.MAGENTA),
    CHOOSE_WORKER("/Image/Cell/CellAction/choose_worker.png", Color.BLACK),

    /**
     * Represents the use power action on a cell, associated with an orange border color.
     */
    USE_POWER("/Image/Cell/CellAction/use_power.png", Color.ORANGE);

    public final String path;
    public final Color borderColor;

    /**
     * Constructor of the JCellStatus
     *
     * @param path the path containing the proper image
     */
    JCellAction(String path, Color borderColor1) {
        this.path = path;
        this.borderColor = borderColor1;
    }

    public String getPath() {
        return path;
    }

    public Color getBorderColor() {
        return borderColor;
    }
}
