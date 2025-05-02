package Model.Game;

import Model.Board.Block;
import Model.Board.BlockType;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a centralized pool of building blocks used during a game of Santorini.
 * <p>
 * Blocks are grouped and managed by their {@link BlockType}. This pool ensures that block placement
 * follows limited availability rules, which reflect physical constraints of a real-world board game.
 * </p>
 * 
 * <p>Block counts initialized per type:</p>
 * <ul>
 *   <li>LEVEL1: 22 blocks</li>
 *   <li>LEVEL2: 18 blocks</li>
 *   <li>LEVEL3: 14 blocks</li>
 *   <li>DOME: 18 blocks</li>
 * </ul>
 */
public class BlockPool {

    private final Map<BlockType, List<Block>> pool;

    /**
     * Constructs a new block pool with the default quantities of each block type.
     */
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

    /**
     * Removes and returns the last available block of the specified type.
     *
     * @param type the type of block to take from the pool
     * @return the removed {@link Block} instance
     * @throws IllegalStateException if no blocks of the requested type are available
     */
    public Block takeBlock(BlockType type) {
        List<Block> list = pool.get(type);
        if (list == null || list.isEmpty()) {
            throw new IllegalStateException("No blocks of type " + type + " left.");
        }
        return list.removeLast();
    }

    /**
     * Checks whether there are any remaining blocks of a specific type.
     *
     * @param type the block type to check
     * @return {@code true} if at least one block of the specified type remains, {@code false} otherwise
     */
    public boolean hasBlock(BlockType type) {
        List<Block> list = pool.get(type);
        return list != null && !list.isEmpty();
    }

    /**
     * Returns the number of remaining blocks for the given type.
     *
     * @param type the block type to query
     * @return the number of blocks remaining
     */
    public int getRemaining(BlockType type) {
        return pool.get(type).size();
    }
}
