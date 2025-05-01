package Model.Board;

/**
 * The {@code Block} class represents a single building block in the Santorini game.
 * Blocks are stackable components used to build up towers, ranging from Level 1 to Level 3 and Domes.
 * <p>
 * Each block has a {@link BlockType} indicating its level and whether it is a dome.
 * </p>
 */
public class Block {

    /** The type of this block, which determines its level and whether it is a dome. */
    private final BlockType type;

    /**
     * Constructs a {@code Block} with a specified type.
     *
     * @param type The type of block (Level 1–3 or Dome)
     * @throws IllegalArgumentException if the provided {@code BlockType} is {@code null}
     */
    public Block(BlockType type) {
        if (type == null) {
            throw new IllegalArgumentException("BlockType cannot be null");
        }
        this.type = type;
    }

    /**
     * Returns the type of this block.
     *
     * @return The {@link BlockType} of this block
     */
    public BlockType getType() {
        return type;
    }

    /**
     * Returns the level of the block (1 to 3 for regular blocks, 4 for dome).
     *
     * @return An integer representing the block's level
     */
    public int getLevel() {
        return type.getLevel();
    }

    /**
     * Determines whether this block is a dome.
     *
     * @return {@code true} if the block is a dome, {@code false} otherwise
     */
    public boolean isDome() {
        return type.isDome();
    }

    /**
     * Determines whether another block can be stacked on top of this one.
     * <p>
     * Stacking is only allowed if:
     * <ul>
     *     <li>This block is not a dome</li>
     *     <li>The top block is the next level after this block</li>
     * </ul>
     *
     * @param topBlock The block to be stacked on top
     * @return {@code true} if stacking is valid, {@code false} otherwise
     */
    public boolean canStack(Block topBlock) {
        return !isDome() && topBlock.getType() == this.type.getNextLevel();
    }
}
