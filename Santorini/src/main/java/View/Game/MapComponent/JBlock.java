package View.Game.MapComponent;

/**
 * The {@code JBlock} enum represents the different types of blocks in the game board,
 * each corresponding to a specific level and associated image path.
 * <p>
 * The available block types are BOTTOM, MIDDLE, TOP, and DOME, each representing a block
 * at different levels of the board. Each block also has a corresponding image used for rendering.
 * </p>
 */
public enum JBlock {

    /**
     * Represents the bottom level block, associated with the image at the specified path.
     */
    BOTTOM("Cell/Block/bottom.png", 1),

    /**
     * Represents the middle level block, associated with the image at the specified path.
     */
    MIDDLE("Cell/Block/middle.png", 2),

    /**
     * Represents the top level block, associated with the image at the specified path.
     */
    TOP("Cell/Block/top.png", 3),

    /**
     * Represents the dome block, associated with the image at the specified path.
     */
    DOME("Cell/Block/dome.png", 4),

    COIN("Cell/coin.png", -1),;

    public final String path;
    public final int level;

    /**
     * Constructs a new {@code JBlock} with the specified image path and level.
     *
     * @param path The path to the image associated with the block
     * @param level The level corresponding to the block
     */
    JBlock(String path,  int level) {
        this.path = path;
        this.level = level;
    }

    /**
     * Retrieves the image path associated with the block.
     *
     * @return The image path of the block
     */
    public String getPath() {
        return path;
    }

    /**
     * Retrieves the corresponding {@code JBlock} for the specified level.
     *
     * @param level The level of the block
     * @return The {@code JBlock} corresponding to the specified level
     * @throws IllegalArgumentException if the level does not match any block
     */
    public static JBlock from(int level) {
        for (JBlock block : JBlock.values()) {
            if (block.level == level) {
                return block;
            }
        }
        throw new IllegalArgumentException("Invalid level: " + level);
    }
}
