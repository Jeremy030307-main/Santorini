package View.Game;

public enum JCellAction {

    BUILD("Asset/Image/Cell/CellAction/build.png"),
    MOVE("Asset/Image/Cell/CellAction/move.png"),
    CHOOSE_WORKER("Asset/Image/Cell/CellAction/chooseWorker.png"),
    USE_POWER("Asset/Image/Cell/CellAction/usePower.png");

    public final String path;

    /**
     * Constructor of the JCellStatus
     *
     * @param path the path containing the proper image
     */
    JCellAction(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
