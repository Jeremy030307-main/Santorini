package View.Game.MapComponent;

import java.awt.*;

public enum JCellAction {

    BUILD("Asset/Image/Cell/CellAction/build.png", Color.BLUE),
    MOVE("Asset/Image/Cell/CellAction/move.png", Color.MAGENTA),
    CHOOSE_WORKER("Asset/Image/Cell/CellAction/choose_worker.png", Color.BLACK),
    USE_POWER("Asset/Image/Cell/CellAction/use_power.png", Color.ORANGE);

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
