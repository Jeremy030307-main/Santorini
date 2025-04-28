package View.Game;

import javax.swing.*;
import java.awt.*;

public class CellPanel extends JPanel {
    private int row;
    private int col;
    private int buildingLevel = 0;
    private boolean hasWorker = false;
    private Color workerColor = null; // Color of the worker (null if no worker)

    public CellPanel(int row, int col) {
        this.row = row;
        this.col = col;
        setPreferredSize(new Dimension(80, 80)); // Size of each cell
        setBackground(Color.lightGray);

        // (Optional) Add mouse listener here for clicks
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw building levels
        g.setColor(Color.GRAY);
        g.drawString("Lvl " + buildingLevel, 10, 20);

        // Draw worker if exists
        if (hasWorker) {
            g.setColor(workerColor != null ? workerColor : Color.BLACK);
            g.fillOval(25, 25, 30, 30); // Draw a circle for worker
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(150, 150); // Fixed size for each cell
    }

    // Helper methods to modify cell state
    public void setBuildingLevel(int level) {
        this.buildingLevel = level;
        repaint();
    }

    public void setWorker(boolean hasWorker, Color workerColor) {
        this.hasWorker = hasWorker;
        this.workerColor = workerColor;
        repaint();
    }
}

