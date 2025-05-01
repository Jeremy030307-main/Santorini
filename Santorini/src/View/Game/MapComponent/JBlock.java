package View.Game.MapComponent;

public enum JBlock {

    BOTTOM("Asset/Image/Cell/Block/bottom.png", 1),
    MIDDLE("Asset/Image/Cell/Block/middle.png", 2),
    TOP("Asset/Image/Cell/Block/top.png", 3),
    DOME("Asset/Image/Cell/Block/dome.png", 4);

    public final String path;
    public final int level;

    /**
     * Constructor of the JCellStatus
     *
     * @param path the path containing the proper image
     */
    JBlock(String path,  int level) {
        this.path = path;
        this.level = level;
    }

    public String getPath() {
        return path;
    }

    public static JBlock from(int level) {
        for (JBlock block : JBlock.values()) {
            if (block.level == level) {
                return block;
            }
        }
        throw new IllegalArgumentException("Invalid level: " + level);
    }
}

