package Model.Board;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private static final int SIZE = 5;
    private final Cell[][] cells;

    public Board() {
        cells = new Cell[SIZE][SIZE];

        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                cells[x][y] = new Cell(x, y);
            }
        }
    }

    public Cell getCell(Position pos) {

        // check weather the position is within the board
        if (!isInBounds(pos.x(), pos.y())) {
            throw new IndexOutOfBoundsException("Invalid coordinates.");
        }

        return cells[pos.x()][pos.y()];
    }

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
                    Cell target = cells[nx][ny];
                    surroundingCells.add(target);
                }
            }
        }

        return surroundingCells;
    }

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

    public boolean isInBounds(int x, int y) {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }

    public Cell[][] getCells() {
        return cells;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Board:\n");
        for (Cell[] cell : cells) {
            for (Cell value : cell) {
                sb.append(value).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
