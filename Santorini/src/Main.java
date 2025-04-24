import Model.Board.Board;
import Model.Board.Cell;
import Model.Board.Position;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();

        List<Cell> movable = board.getBuildableCell(new Position(2,2,1));
        System.out.println(board);
        System.out.println(movable);
    }
}