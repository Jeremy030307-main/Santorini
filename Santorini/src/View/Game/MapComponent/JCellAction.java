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

    /**
     * Represents the build action on a cell, associated with a blue border color.
     */
    BUILD("Asset/Image/Cell/CellAction/build.png", Color.BLUE),

    /**
     * Represents the move action on a cell, associated with a magenta border color.
     */
    MOVE("Asset/Image/Cell/CellAction/move.png", Color.MAGENTA),

    /**
     * Represents the choose worker action on a cell, associated with a black border color.
     */
    CHOOSE_WORKER("Asset/Image/Cell/CellAction/choose_worker.png", Color.BLACK),

    /**
     * Represents the use power action on a cell, associated with an orange border color.
     */
    USE_POWER("Asset/Image/Cell/CellAction/usePower.png", Color.ORANGE);

    public final String path;
    public final Color borderColor;

    /**
     * Constructs a new {@code JCellAction} with the specified image path and border color.
     *
     * @param path The path to the image associated with the action
     * @param borderColor1 The border color associated with the action
     */
    JCellAction(String path, Color borderColor1) {
        this.path = path;
        this.borderColor = borderColor1;
    }

    /**
     * Retrieves the image path associated with the action.
     *
     * @return The image path of the action
     */
    public String getPath() {
        return path;
    }

    /**
     * Retrieves the border color associated with the action.
     *
     * @return The border color of the action
     */
    public Color getBorderColor() {
        return borderColor;
    }
}
