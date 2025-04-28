package Model.Board;

import Model.Game.BlockPool;

public class Block {

    private final BlockType type;

    public Block(BlockType type) {

        if (type == null) {
            throw new IllegalArgumentException("BlockType cannot be null");
        }
        this.type = type;
    }

    public BlockType getType() {
        return type;
    }

    public int getLevel() {
        return type.getLevel();
    }

    public boolean isDome() {
        return type.isDome();
    }

    public boolean canStack(Block topBlock) {
        return !isDome() && topBlock.getType() == this.type.getNextLevel();
    }
}