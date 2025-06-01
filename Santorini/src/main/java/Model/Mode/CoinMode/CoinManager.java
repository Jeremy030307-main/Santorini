package Model.Mode.CoinMode;

import Model.Board.BlockType;
import Model.Board.Board;
import Model.Board.Position;
import Model.Player.Player;

import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class CoinManager {

    private final HashMap<BlockType, Integer> blockPrice;
    private final HashMap<Player, Integer> coins;
    private int coinsOnBoard = 0;       // Active coins on board
    private final boolean[][] coinGrid;
    private final Board board;
    private final int rows;
    private final int cols;

    public CoinManager(Board board, Player[] players) {

        blockPrice = new HashMap<>();
        blockPrice.put(BlockType.LEVEL1, 1);
        blockPrice.put(BlockType.LEVEL2, 2);
        blockPrice.put(BlockType.LEVEL3, 3);
        blockPrice.put(BlockType.DOME, 1);

        coins = new HashMap<>();
        for (Player player : players) {
            coins.put(player, 6);
        }

        rows = board.getCells().length;
        cols = board.getCells()[0].length;
        coinGrid = new boolean[rows][cols];

        // Initialize all coin positions to false
        for (int i = 0; i < coinGrid.length; i++) {
            for (int j = 0; j < coinGrid[0].length; j++) {
                coinGrid[i][j] = false;
            }
        }
        this.board = board;
    }

    public boolean trySpawnCoinWithCap() {
        int totalCells = rows * cols;

        // If at least half of the board already has coins, do not spawn
        if (coinsOnBoard >= totalCells / 2) return false;

        Random rand = new Random();
        int maxAttempts = 20;

        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            int x = rand.nextInt(rows);
            int y = rand.nextInt(cols);

            if (!coinGrid[x][y] && !board.getCell(new Position(x,y)).isOccupied()) {
                coinGrid[x][y] = true;
                coinsOnBoard++;
                return true;
            }
        }

        return false; // Could not spawn in empty cell
    }

    public boolean canAffordBlock(Player player, BlockType blockType) {
        if (player == null || blockType == null) return false;
        int price = blockPrice.getOrDefault(blockType, Integer.MAX_VALUE);
        int playerCoins = coins.getOrDefault(player, 0);
        return playerCoins >= price;
    }

    // Check if a coin is present at (x, y)
    public boolean hasCoinAt(int x, int y) {
        if (x < 0 || x >= rows || y < 0 || y >= cols) return false;
        return coinGrid[x][y];
    }

    public void collectCoinAt(Player player, int x, int y) {

        coins.put(player, coins.getOrDefault(player, 0) + 1);
        if (coinGrid[x][y]){
            coinGrid[x][y] = false;
            coinsOnBoard--;
        };
    }

    // Spend coins from a player, returns true if successful
    public void spendCoins(Player player, int amount) {
        int current = coins.getOrDefault(player, 0);
        if (amount <= 0 || current < amount) return;
        coins.put(player, current - amount);
    }

    // get price of a block
    public int getPrice(BlockType blockType) {
        return blockPrice.getOrDefault(blockType, Integer.MAX_VALUE);
    };

    // Get current coin count of a player
    public int getCoinCount(Player player) {
        return coins.getOrDefault(player, 0);
    }

    public int getBlockPrice(BlockType blockType) {
        return blockPrice.getOrDefault(blockType, Integer.MAX_VALUE);
    }
}
