package Model.Board;

import Model.Player.Worker;

import java.util.ArrayList;
import java.util.List;

public class Cell {

    private final int x;
    private final int y;

    private final List<Block> tower;
    private Worker occupant; // this line might be changed to just a hashmap store Map<Worker: Cell> in GameState

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.tower = new ArrayList<Block>();
        this.occupant = null;
    }

    public void buildBlock(Block newBlock){
        // check is the block is valid block
        if (isComplete()) {
            throw new IllegalStateException("Tower already complete.");
        }

        if (!tower.isEmpty()) {
            if (!tower.getLast().canStack(newBlock)) {
                throw new IllegalArgumentException("Cannot stack this block on top of the current one.");
            }
        } else {
            if (newBlock.getLevel() != 1) {
                throw new IllegalArgumentException("First block must be level 1.");
            }
        }

        tower.add(newBlock);
    };

    public boolean isComplete(){
        return tower.getLast().isDome();
    };

    public void clearOccupant() {
        this.occupant = null;
    }

    // Check if the cell is occupied by a worker
    public boolean isOccupied() {
        return occupant != null;
    }

    public Position getPosition(){
        return new Position(x ,y ,tower.getLast().getLevel());
    };

    public Worker getOccupant(){
        return  occupant;
    }

    public void setOccupant(Worker occupant){
        this.occupant = occupant;
    }
}
