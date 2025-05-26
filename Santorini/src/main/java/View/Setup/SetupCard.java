package View.Setup;

import javax.swing.*;

public abstract class SetupCard extends JPanel {

    protected int maxHeight;
    protected int maxWidth;

    abstract void setup();

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }
}
