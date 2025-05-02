import View.SantoriniFrame;

import javax.swing.*;

/**
 * The {@code Main} class serves as the entry point for the Santorini game.
 * It is responsible for starting the application and initializing the main game frame.
 * <p>
 * The {@code main} method sets up the user interface by invoking {@link SantoriniFrame}
 * and making it visible to the user. This class is the starting point for the game when the program is run.
 * </p>
 */
public class Main {
    /**
     * The {@code main} method initializes the user interface by launching the {@code SantoriniFrame}.
     * This method is executed when the program is run and makes the main game window visible to the user.
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> SantoriniFrame.getInstance().setVisible(true));

    }
}