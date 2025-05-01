package Model.Board;

/**
 * Represents a 3D coordinate on the Santorini board.
 * <p>
 * This immutable data structure stores the x (column), y (row), and z (level/height) components
 * of a cell's position. The default z-level is 0 for base-level cells.
 * </p>
 *
 * @param x The X-coordinate (horizontal axis)
 * @param y The Y-coordinate (vertical axis)
 * @param z The Z-coordinate (building level)
 */
public record Position(int x, int y, int z) {

    /**
     * Constructs a new Position with default level z = 0.
     *
     * @param x The x-coordinate
     * @param y The y-coordinate
     */
    public Position(int x, int y) {
        this(x, y, 0);
    }

    /**
     * Determines if another position is adjacent to this one.
     * <p>
     * Adjacency includes diagonals and requires that the other position is not the same as this one.
     * </p>
     *
     * @param other The position to compare with
     * @return {@code true} if the other position is directly adjacent (horizontally, vertically, or diagonally),
     *         {@code false} otherwise
     */
    public boolean isAdjacent(Position other) {
        return Math.abs(x - other.x) <= 1 &&
               Math.abs(y - other.y) <= 1 &&
               !this.equals(other);
    }
}
