package Model.Board;

public class Block {
    private final int level;

    public Block(int level) {
        if (level < 1 || level > 4) {
            throw new IllegalArgumentException("Block level must be between 1 and 4.");
        }
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public boolean isDome() {
        return level == 4;
    }

    public boolean canStack(Block topBlock) {
        return !isDome() && topBlock.level == this.level + 1;
    }
}
/* TODO: Make a enum class for GUI display purpose instead of integer 1,2,3*/

