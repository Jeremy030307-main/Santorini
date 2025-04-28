package Model.Board;

public record Position(int x, int y, int z) {

    public Position(int x, int y) {
        this(x, y, 0); // Default z to 0
    }

    public boolean isAdjacent(Position other) {
        return Math.abs(x - other.x) <= 1 &&
                Math.abs(y - other.y) <= 1 &&
                !this.equals(other);
    }
}
