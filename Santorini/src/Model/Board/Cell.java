package Model.Board;

import Model.Player.Worker;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single cell (tile) on the Santorini board.
 * <p>
 * Each cell tracks its X and Y coordinates, the tower of blocks built on it,
 * and a reference to the {@link Worker} (if any) currently occupying it.
 * </p>
 */
public class Cell {

    /** X-coordinate of the cell on the board. */
    private final int x;

    /** Y-coordinate of the cell on the board. */
    private final int y;

    /** Stack of blocks forming the tower at this cell. */
    private final List<Block> tower;

    /** Worker currently occupying this cell (if any). */
    private Worker occupant;

    /**
     * Constructs an empty cell at the given (x, y) position.
     *
     * @param x The x-coordinate
     * @param y The y-coordinate
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.tower = new ArrayList<>();
        this.occupant = null;
    }

    /**
     * Adds a new block on top of the tower at this cell.
     *
     * @param newBlock The {@link Block} to place
     * @throws IllegalStateException    If the tower is already complete (i.e., has a dome)
     * @throws IllegalArgumentException If the block cannot be stacked based on level rules
     */
    public void buildBlock(Block newBlock) {
        if (isComplete()) {
            throw new IllegalStateException("Tower already complete.");
        }

        if (!tower.isEmpty()) {
            if (!tower.getLast().canStack(newBlock)) {
                throw new IllegalArgumentException("Cannot stack this block on top of the current one.");
            }
        } else {
            if (newBlock.getLevel() != 1) {
                throw new IllegalArgumentException("First block must be level 1.");
            }
        }

        tower.add(newBlock);
    }

    /**
     * Returns whether this cell is adjacent to another cell (including diagonals).
     *
     * @param otherCell The cell to compare with
     * @return {@code true} if adjacent, {@code false} otherwise
     */
    public boolean isAdjacentTo(Cell otherCell) {
        return getPosition().isAdjacent(otherCell.getPosition());
    }

    /**
     * Checks if the tower at this cell is complete (i.e., topped with a dome).
     *
     * @return {@code true} if the top block is a dome, {@code false} otherwise
     */
    public boolean isComplete() {
        return !tower.isEmpty() && tower.getLast().isDome();
    }

    /**
     * Checks if the cell is currently occupied by a worker.
     *
     * @return {@code true} if occupied, {@code false} otherwise
     */
    public boolean isOccupied() {
        return occupant != null;
    }

    /**
     * Sets the occupant of the cell.
     *
     * @param occupant The {@link Worker} to place on the cell
     * @throws IllegalStateException If the cell is already occupied
     */
    public void setOccupant(Worker occupant) {
        if (this.occupant != null) {
            throw new IllegalStateException("Cell is already occupied by Worker " + this.occupant.getId());
        }
        this.occupant = occupant;
    }

    /**
     * Clears the current occupant of the cell.
     */
    public void clearOccupant() {
        this.occupant = null;
    }

    /**
     * Returns the {@link Position} of the cell including X, Y, and current tower height (Z).
     *
     * @return A new {@code Position} object with current coordinates and level
     */
    public Position getPosition() {
        int level = tower.isEmpty() ? 0 : tower.getLast().getLevel();
        return new Position(x, y, level);
    }

    /**
     * Returns the current {@link Worker} occupying this cell, if any.
     *
     * @return The occupant worker, or {@code null} if unoccupied
     */
    public Worker getOccupant() {
        return occupant;
    }

    /**
     * Returns the list of blocks forming the tower at this cell.
     *
     * @return A mutable list of {@link Block} objects
     */
    public List<Block> getTower() {
        return tower;
    }

    /**
     * Returns a string representation of the cell's position and level.
     *
     * @return A string showing the cell's position (x, y, z)
     */
    @Override
    public String toString() {
        return getPosition().toString();
    }
}
