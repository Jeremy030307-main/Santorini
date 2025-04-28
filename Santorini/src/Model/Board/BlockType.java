package Model.Board;

public enum BlockType {
    LEVEL1(1),
    LEVEL2(2),
    LEVEL3(3),
    DOME(4);

    private final int level;

    BlockType(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public boolean isDome() {
        return this == DOME;
    }

    public static BlockType fromLevel(int level) {
        return switch (level) {
            case 1 -> LEVEL1;
            case 2 -> LEVEL2;
            case 3 -> LEVEL3;
            case 4 -> DOME;
            default -> throw new IllegalArgumentException("Invalid block level: " + level);
        };
    }

    public BlockType getNextLevel() {
        return switch (this) {
            case LEVEL1 -> LEVEL2;
            case LEVEL2 -> LEVEL3;
            case LEVEL3 -> DOME;
            case DOME -> throw new IllegalStateException("Dome cannot be stacked upon");
        };
    }
}

