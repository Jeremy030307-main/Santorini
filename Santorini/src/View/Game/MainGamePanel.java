package View.Game;

import View.BackgroundPanel;
import View.SantoriniPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MainGamePanel extends SantoriniPanel {

    private static final String imgPath = "GameBoard.png";

    public MainGamePanel() {
        super(imgPath);

        createMap();
    }

    private void createMap(){
        JBoard board = new JBoard();
        int boardSize = (int)(getScaleWidth() * 0.681); // or whatever fits your grid
        board.setPreferredSize(new Dimension(boardSize, boardSize));
        board.setOpaque(false); // So background image shows through

        GridBagConstraints mapCon = new GridBagConstraints();
        mapCon.gridx = 1;
        mapCon.gridy = 0;
        mapCon.gridwidth = 1;
        mapCon.gridheight = 2;
        mapCon.weightx = 0.075;
        mapCon.weighty = 1;
        mapCon.fill = GridBagConstraints.BOTH;
        mapCon.anchor = GridBagConstraints.CENTER;

        int top = (int) ((70.0 / getScaleHeight()) * getMaxHeight());
        int left = (int) ((311.0 / getScaleWidth()) * getMaxWidth());
        int bottom = (int) ((84.0 / getScaleHeight()) * getMaxHeight());
        int right = (int) ((306.0 / getScaleWidth()) * getMaxWidth());
        System.out.println(top + " " + left + " " + bottom + " " + right);
        mapCon.insets = new Insets(top, left, bottom, right);

        add(board, mapCon);
    }
}

