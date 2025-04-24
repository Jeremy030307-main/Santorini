package Model.Game;

import Model.Board.Block;
import Model.Board.BlockType;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class BlockPool {

    private final Map<BlockType, List<Block>> pool;

    public BlockPool() {
        pool = new EnumMap<>(BlockType.class);

        pool.put(BlockType.LEVEL1, new ArrayList<>());
        pool.put(BlockType.LEVEL2, new ArrayList<>());
        pool.put(BlockType.LEVEL3, new ArrayList<>());
        pool.put(BlockType.DOME, new ArrayList<>());

        for (int i = 0; i < 22; i++) {
            pool.get(BlockType.LEVEL1).add(new Block(BlockType.LEVEL1));
        }
        for (int i = 0; i < 18; i++) {
            pool.get(BlockType.LEVEL2).add(new Block(BlockType.LEVEL2));
        }
        for (int i = 0; i < 14; i++) {
            pool.get(BlockType.LEVEL3).add(new Block(BlockType.LEVEL3));
        }
        for (int i = 0; i < 18; i++) {
            pool.get(BlockType.DOME).add(new Block(BlockType.DOME));
        }
    }

    public Block takeBlock(BlockType type) {
        List<Block> list = pool.get(type);
        if (list == null || list.isEmpty()) {
            throw new IllegalStateException("No blocks of type " + type + " left.");
        }
        return list.remove(list.size() - 1);
    }

    public boolean hasBlock(BlockType type) {
        List<Block> list = pool.get(type);
        return list != null && !list.isEmpty();
    }

    public int getRemaining(BlockType type) {
        return pool.get(type).size();
    }
}
