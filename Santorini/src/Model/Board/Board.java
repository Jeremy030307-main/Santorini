package Model.Board;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Board} class represents the 5x5 grid used in the Santorini game.
 * <p>
 * It manages all {@link Cell} objects that make up the playable area,
 * provides utility methods to retrieve neighboring and unoccupied cells,
 * and enforces board boundaries.
 * </p>
 */
public class Board {

    /** The fixed size of the board (5x5). */
    private static final int SIZE = 5;

    /** Two-dimensional array of cells on the board. */
    private final Cell[][] cells;

    /**
     * Constructs a new 5x5 Santorini board, initializing all cells.
     */
    public Board() {
        cells = new Cell[SIZE][SIZE];

        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                cells[x][y] = new Cell(x, y);
            }
        }
    }

    /**
     * Returns the cell located at the given position.
     *
     * @param pos The {@link Position} of the cell to retrieve
     * @return The {@link Cell} at the specified position
     * @throws IndexOutOfBoundsException if the position is out of bounds
     */
    public Cell getCell(Position pos) {
        if (!isInBounds(pos.x(), pos.y())) {
            throw new IndexOutOfBoundsException("Invalid coordinates.");
        }
        return cells[pos.x()][pos.y()];
    }

    /**
     * Returns a list of all valid surrounding cells (max 8) around a given position.
     * <p>
     * Diagonal, vertical, and horizontal neighbors are included,
     * excluding out-of-bound cells and the center cell itself.
     * </p>
     *
     * @param pos The position to get neighbors for
     * @return A list of adjacent {@link Cell} objects
     */
    public List<Cell> getSurroundingCell(Position pos) {
        List<Cell> surroundingCells = new ArrayList<>();
        int cx = pos.x();
        int cy = pos.y();

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;

                int nx = cx + dx;
                int ny = cy + dy;

                if (isInBounds(nx, ny)) {
                    surroundingCells.add(cells[nx][ny]);
                }
            }
        }

        return surroundingCells;
    }

    /**
     * Returns a list of all cells that are currently unoccupied by any worker.
     *
     * @return A list of unoccupied {@link Cell} objects
     */
    public List<Cell> getUnoccupiedCells() {
        List<Cell> unOccupiedCells = new ArrayList<>();

        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                Cell cell = cells[row][col];
                if (!cell.isOccupied()) {
                    unOccupiedCells.add(cell);
                }
            }
        }

        return unOccupiedCells;
    }

    /**
     * Checks whether the given (x, y) coordinate is within board bounds.
     *
     * @param x The x-coordinate
     * @param y The y-coordinate
     * @return {@code true} if the coordinates are valid, {@code false} otherwise
     */
    public boolean isInBounds(int x, int y) {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }

    /**
     * Returns the internal 2D array of cells on the board.
     *
     * @return A 5x5 {@code Cell[][]} array
     */
    public Cell[][] getCells() {
        return cells;
    }

    /**
     * Returns a string representation of the board for debugging or visualization.
     *
     * @return A string showing the board state
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Board:\n");
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                sb.append(cell).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
