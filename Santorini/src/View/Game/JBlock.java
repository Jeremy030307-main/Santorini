package View.Game;

public enum JBlock {

    BOTTOM("Asset/Image/Cell/Block/bottom.png"),
    MIDDLE("Asset/Image/Cell/Block/middle.png"),
    TOP("Asset/Image/Cell/Block/top.png"),
    DOME("Asset/Image/Cell/Block/dome.png");

    public final String path;

    /**
     * Constructor of the JCellStatus
     *
     * @param path the path containing the proper image
     */
    JBlock(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}

