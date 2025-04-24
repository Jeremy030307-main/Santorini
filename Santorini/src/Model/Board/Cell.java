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
        this.tower = new ArrayList<>();
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
    }

    public boolean canMoveTo(Cell otherCell){

        Position currPos = getPosition();
        Position otherPos = otherCell.getPosition();

        return currPos.isAdjacent(otherPos) &&
                !otherCell.isOccupied() &&
                !otherCell.isComplete() &&
                currPos.z() - otherPos.z() > -1;
    }

    public boolean canBuildOn(Cell otherCell){

        Position currPos = getPosition();
        Position otherPos = otherCell.getPosition();

        return currPos.isAdjacent(otherPos) &&
                !otherCell.isOccupied() &&
                !otherCell.isComplete();
    }

    public boolean isComplete(){

        if (tower.isEmpty()){
            return false;
        }

        return tower.getLast().isDome();
    }

    public boolean isOccupied() {
        return occupant != null;
    }

    public void setOccupant(Worker occupant){
        this.occupant = occupant;
    }

    public void clearOccupant() {
        this.occupant = null;
    }

    public Position getPosition(){
        int level = tower.isEmpty() ? 0 : tower.getLast().getLevel();

        return new Position(x ,y ,level);
    }

    public Worker getOccupant(){
        return  occupant;
    }

    @Override
    public String toString() {
        return getPosition().toString();
    }
}
