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

    // this two might move to worker class
    public List<Cell> getMovableCell(Position currentPosition) {

        Cell currentCell = getCell(currentPosition);

        return getSurroundingCell(currentPosition).stream()
                .filter(currentCell::canMoveTo)
                .toList();
    }


    // this might move to worker class
    public List<Cell> getBuildableCell(Position currentPosition) {

        Cell currentCell = getCell(currentPosition);

        return getSurroundingCell(currentPosition).stream()
                .filter(currentCell::canBuildOn)
                .toList();
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

    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
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
