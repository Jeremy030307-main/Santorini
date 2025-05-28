package Controller;

import Controller.GameFlow.GameController;
import Controller.GameFlow.GameDirector;
import Model.Game.*;
import Model.GodCard.GodCardFactory;
import Model.Player.Player;
import Model.Player.WorkerColor;
import View.SantoriniFrame;
import View.Setup.ChooseGodPanel;
import View.Setup.MiniGodCardButton;
import View.Setup.SetupLobby;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

public class SetupController {

    public static final String SETUP_VIEW = "setupView";

    private final SantoriniFrame santoriniFrame;
    private final SetupLobby setupLobby;
    private final ChooseGodPanel chooseGodPanel;

    private Player[] players;
    private int[] playerSelectedGods;
    private boolean[] playerSelecting;
    private int currentPlayerIndex;

    private boolean[][] layout;

    private final List<GodCardFactory> allGods;

    public SetupController(SantoriniFrame santoriniFrame, GameMode gameMode) {

        // creation of model object
        switch (gameMode) {
            case TWO_PLAYER -> {

                players = new Player[2];
                playerSelecting = new boolean[2]; playerSelecting[0] = false;playerSelecting[1] = false;

                playerSelectedGods = new int[2];playerSelectedGods[0] = -1;playerSelectedGods[1] = -1;
                players[0] = new Player(0, "Player 1", null, WorkerColor.ORANGE);
                players[1] = new Player(1, "Player 2", null, WorkerColor.PURPLE);
            }
            case THREE_PLAYER -> {

                players = new Player[3];
                playerSelecting = new boolean[3]; playerSelecting[0] = false;playerSelecting[1] = false;playerSelecting[2] = false;
                playerSelectedGods = new int[3];playerSelectedGods[0] = -1;playerSelectedGods[1] = -1;playerSelectedGods[2] = -1;
                players[0] = new Player(0, "Player 1", null, WorkerColor.ORANGE);
                players[1] = new Player(1, "Player 2", null, WorkerColor.PURPLE);
                players[2] = new Player(2, "Player 3", null, WorkerColor.RED);
            }

            default -> throw new IllegalArgumentException("Unsupported mode: " + gameMode);
        }
        currentPlayerIndex = 0;

        this.allGods = GodCardFactory.getAllGods();
        this.layout = new boolean[][]{
                {true, true, true, true, true},
                {true, true, true, true, true},
                {true, true, true, true, true},
                {true, true, true, true, true},
                {true, true, true, true, true}
        };

        setupLobby = new SetupLobby();
        chooseGodPanel = new ChooseGodPanel(allGods.stream().map(GodCardFactory::getName).collect(Collectors.toList()));
        this.santoriniFrame = santoriniFrame;

        santoriniFrame.addView(setupLobby, SETUP_VIEW);
        setup();
    }

    public void setup(){

        setupLobby.addCard(chooseGodPanel, "chooseGodCard");

        // First phase of setup: God Selection
        setupLobby.showView("chooseGodCard");

        // player 1 should be the first to select the god card
        chooseGodPanel.getPlayerButtons().getFirst().setVisible(true);
        playerSelecting[0] = true;

        // set up the action listener for each button first
        setupChooseGodCardButton();
        setupPlayerButton();
        setupConfirmButton();

    }

    public void start(){
        // first
        santoriniFrame.showView(SETUP_VIEW);
    }

    private void setupPlayerButton(){

        List<JButton> buttons = chooseGodPanel.getPlayerButtons();
        for (int i = 0; i < buttons.size(); i++) {
            JButton playerButton = buttons.get(i);

            int finalI = i;
            playerButton.addActionListener(e -> {
                if (playerSelecting[finalI]){
                    nextPlayer(null);
                    buttons.get((finalI +1)%(buttons.size())).setVisible(true);
                    playerButton.setText("Edit");
                } else {
                    nextPlayer(finalI);
                    playerButton.setText("Confirm");
                }
            });
        }
    }

    private void setupChooseGodCardButton(){
        List<MiniGodCardButton> buttons = chooseGodPanel.getGodCardButtons();

        for (int i = 0; i < buttons.size(); i++) {
            int godIndex = i;
            buttons.get(godIndex).addActionListener(e-> {

                // check if the player select a god card previously
                if (playerSelectedGods[currentPlayerIndex] >= 0){
                    // if yes, remove the selected filter on the button
                    buttons.get(playerSelectedGods[currentPlayerIndex]).setSelected(MiniGodCardButton.SelectionColor.NONE);
                }
                playerSelectedGods[currentPlayerIndex] = godIndex;

                chooseGodPanel.setPlayerPanel(currentPlayerIndex, allGods.get(godIndex).getName(), allGods.get(godIndex).getDescription());

                // set the selected filter on button based on different player
                chooseGodPanel.getPlayerButtons().get(currentPlayerIndex).setEnabled(true);
                if (currentPlayerIndex==0){
                    buttons.get(godIndex).setSelected(MiniGodCardButton.SelectionColor.BLUE);
                } else if (currentPlayerIndex==1){
                    buttons.get(godIndex).setSelected(MiniGodCardButton.SelectionColor.RED);
                }
            });
        }
    }

    private void setupConfirmButton(){
        chooseGodPanel.getConfirmButton().addActionListener(e -> {
            for (int i = 0; i < players.length; i++) {
                players[i].setGodCard(allGods.get(playerSelectedGods[i]).getConstructor().get());
            }

            GameController gameController = GameDirector.constructTimedGame(santoriniFrame, players, layout, new BlockPool());
            gameController.setupGame();
        });
    }

    private void nextPlayer(Integer playerIndex){

        List<JButton> playerButtons = chooseGodPanel.getPlayerButtons();

        if (playerIndex == null){
            // first set the current player to not selecting
            playerSelecting[currentPlayerIndex] = false;

            currentPlayerIndex = (currentPlayerIndex + 1) % (players.length);

            for (int i = 0; i < players.length; i++) {
                if (playerSelectedGods[i] < 0){ // this means player has selected a god before
                    playerSelecting[currentPlayerIndex] = true;
                    return;
                }
                playerButtons.get(i).setEnabled(true);
            }

            // if the program meet here, means all player already selected a god, so set the current player index to -1
            currentPlayerIndex = -1;
            chooseGodPanel.getConfirmButton().setEnabled(true);
        } else {
            currentPlayerIndex = playerIndex;
            playerSelecting[currentPlayerIndex] = true;
            chooseGodPanel.getConfirmButton().setEnabled(false);

            for (int i = 0; i < players.length; i++) {
                if (i != currentPlayerIndex){
                    playerButtons.get(i).setEnabled(false);
                } else {
                    playerButtons.get(i).setEnabled(true);
                }
            }
        }
    }
}
