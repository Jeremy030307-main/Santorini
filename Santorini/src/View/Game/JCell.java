package View.Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JCell extends JButton {

    private int row;
    private int col;

    private List<JBlock> blocks;
    private JWorker worker;
    private JCellAction action;

    private Image backgroundImage; // Background image

    public JCell(int row, int col) {
        this.row = row;
        this.col = col;
        this.blocks = new ArrayList<>();
        this.action = JCellAction.BUILD;

        setFocusPainted(false);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setLayout(new GridBagLayout());
        setName("cel");
    }

    @Override
    protected void paintComponent(Graphics g) {

        for (JBlock block : blocks) {
            Image img = new ImageIcon(Objects.requireNonNull(getClass().getResource("../../" + block.getPath()))).getImage();
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }

        if (action != null) {
            Image img1 = new ImageIcon(Objects.requireNonNull(getClass().getResource("../../" + action.getPath()))).getImage();
            g.drawImage(img1, 0, 0, getWidth(), getHeight(), this);
        }

        super.paintComponent(g);

    }

    public void addBlock(JBlock block) {
        blocks.add(block);
    }

    public void setBlocks(List<JBlock> blocks) {
        this.blocks = blocks;
    }

    public JBlock removeTopBlock(){
        return blocks.removeLast();
    }

    public void setWorker(JWorker worker) {
        this.worker = worker;
    }

    public void setAction(JCellAction action) {
        this.action = action;
    }

}

