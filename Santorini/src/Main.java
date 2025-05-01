import View.SantoriniFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> SantoriniFrame.getInstance().setVisible(true));

    }
}