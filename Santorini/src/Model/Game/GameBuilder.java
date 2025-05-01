package Model.Game;

import Model.Board.Board;
import Model.GameRule.ClassicGameRule;
import Model.GodCard.GodCardFactory;
import Model.Player.Player;
import Model.Player.WorkerColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameBuilder {

    private GameMode mode;
    private final Player[] players;
    private final Board board;
    private final ClassicGameRule gameRule;
    private final BlockPool blockPool;
    private final SetupManager setupManager;
    private final TurnManager turnManager;

    private final int challengerIndex;
    private final List<GodCardFactory> chosenGods;
    private int currentPlayerIndex;
    private GodCardFactory selectedGodCard;

    public GameBuilder(GameMode mode) {
        this.mode = mode;

        switch (mode) {
            case TWO_PLAYER -> {

                players = new Player[2];
                players[0] = new Player(0, "Player 1", null, WorkerColor.ORANGE);
                players[1] = new Player(1, "Player 2", null, WorkerColor.PURPLE);
            }
            case THREE_PLAYER -> {

                players = new Player[3];
                players[0] = new Player(0, "Player 1", null, WorkerColor.ORANGE);
                players[1] = new Player(1, "Player 2", null, WorkerColor.PURPLE);
                players[2] = new Player(2, "Player 3", null, WorkerColor.RED);
            }

            default -> throw new IllegalArgumentException("Unsupported mode: " + mode);
        }
        this.challengerIndex = selectRandomChallenger();
        this.currentPlayerIndex = challengerIndex;
        this.chosenGods = new ArrayList<>();

        this.board = new Board();
        this.gameRule = new ClassicGameRule();
        this.blockPool = new BlockPool();
        this.setupManager = new SetupManager(players, board);
        this.turnManager = new TurnManager(players);
    }

    public Game buildGame() {
        return new Game(board, players, gameRule, blockPool, turnManager, setupManager);
    }

    // challenger choose god
    public boolean isChooseGodsComplete(){
        return chosenGods.size() == players.length;
    }

    public boolean onChallengerSelectGodCard(GodCardFactory godCardFactory){

        if (chosenGods.contains(godCardFactory)){
            chosenGods.remove(godCardFactory);
            return false;
        } else {
            chosenGods.add(godCardFactory);
            return true;
        }
    }

    // player choose god
    public boolean isChooseGodComplete(){
        System.out.println(players);
        for (Player player : players) {
            if (player.getGodCard() == null){
                System.out.println("no god card" + player.getId());
                return false;
            }
        }
        return true;
    }

    public boolean onPlayerSelectGodCard(GodCardFactory godCardFactory){

        if (selectedGodCard == null){
            selectedGodCard = godCardFactory;
            return true;
        }

        if (selectedGodCard == godCardFactory){
            selectedGodCard = null;
            return false;
        } else {
            selectedGodCard = godCardFactory;
            return true;
        }


    }

    public void onPlayerGodSelectionComplete(){
        chosenGods.remove(selectedGodCard);
        getCurrentPlayer().setGodCard(selectedGodCard.getConstructor().get());

        selectedGodCard = null;
        currentPlayerIndex += 1;

        if (currentPlayerIndex >= players.length){
            currentPlayerIndex = 0;
        }
    }

    public GodCardFactory getSelectedGodCard() {
        return selectedGodCard;
    }

    public Player getCurrentPlayer(){
        return players[currentPlayerIndex];
    }

    public Player[] getPlayers() {
        return players;
    }

    public int getChallengerIndex() {
        return challengerIndex;
    }

    public Player getChallenger() {
        return players[challengerIndex];
    }

    public List<GodCardFactory> getChosenGods() {
        return chosenGods;
    }

    private int selectRandomChallenger() {
        Random random = new Random();
        return random.nextInt(players.length);
    }
}

