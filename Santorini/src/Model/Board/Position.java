package Model.Board;

public record Position(int x, int y, int z) {

    public boolean isAdjacent(Position other) {
        return Math.abs(x - other.x) <= 1 &&
                Math.abs(y - other.y) <= 1 &&
                !this.equals(other);
    }
}
