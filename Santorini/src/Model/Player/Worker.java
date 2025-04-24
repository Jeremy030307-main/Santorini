package Model.Player;

import Model.Board.Cell;

public class Worker {

    private final int id;
    private final Player owner;
    private Cell position;

    public Worker(int id, Player owner){
        this.id = id;
        this.owner = owner;
    }
//    public List<Action> allowableAction(GameState gameState) {
//
//    }

    public int getId() {
        return id;
    }

    public Player getOwner() {
        return owner;
    }

    public Cell getPosition() {
        return position;
    }

    public void setPosition(Cell position) {
        this.position = position;
    }
}
