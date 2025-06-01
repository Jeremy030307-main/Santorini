package Model.Board;

import java.util.Arrays;

/**
 * The {@code BlockType} enum defines the types of building blocks
 * used in the Santorini game: LEVEL1, LEVEL2, LEVEL3, and DOME.
 * <p>
 * Each block type corresponds to a building level (1–4),
 * where DOME is the final block that completes a tower.
 * </p>
 */
public enum BlockType {
    /** First level of a building. */
    LEVEL1(1),

    /** Second level of a building. */
    LEVEL2(2),

    /** Third level of a building. */
    LEVEL3(3),

    /** Dome, which is placed on top of a Level 3 to complete a tower. */
    DOME(4);

    /** The numerical level associated with the block type. */
    private final int level;

    /**
     * Constructs a {@code BlockType} with the specified level.
     *
     * @param level The building level (1–4)
     */
    BlockType(int level) {
        this.level = level;
    }

    /**
     * Returns the level of the block.
     *
     * @return An integer representing the level (1–4)
     */
    public int getLevel() {
        return level;
    }

    /**
     * Determines if this block is a dome.
     *
     * @return {@code true} if the block is a dome, {@code false} otherwise
     */
    public boolean isDome() {
        return this == DOME;
    }

    /**
     * Converts an integer level (1–4) to the corresponding {@code BlockType}.
     *
     * @param level The level of the block
     * @return The corresponding {@code BlockType}
     * @throws IllegalArgumentException if the level is not in range 1–4
     */
    public static BlockType fromLevel(int level) {
        return switch (level) {
            case 1 -> LEVEL1;
            case 2 -> LEVEL2;
            case 3 -> LEVEL3;
            case 4 -> DOME;
            default -> throw new IllegalArgumentException("Invalid block level: " + level);
        };
    }

    /**
     * Returns the next level block type that can be stacked on top of this one.
     * <p>
     * For example, LEVEL1 → LEVEL2, LEVEL2 → LEVEL3, LEVEL3 → DOME.
     * DOME has no next level and will throw an exception.
     * </p>
     *
     * @return The {@code BlockType} of the next level
     * @throws IllegalStateException if called on DOME
     */
    public BlockType getNextLevel() {
        return switch (this) {
            case LEVEL1 -> LEVEL2;
            case LEVEL2 -> LEVEL3;
            case LEVEL3 -> DOME;
            case DOME -> throw new IllegalStateException("Dome cannot be stacked upon");
        };
    }

    public static BlockType[] getValuesInAscendingOrder() {
        return Arrays.stream(values())
                .sorted((a, b) -> Integer.compare(a.level, b.level))
                .toArray(BlockType[]::new);
    }
}
