import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class hihi {
    public static void main(String[] args) {
        // Create a new frame
        JFrame frame = new JFrame("Swing Example");

        // Create a label
        JLabel label = new JLabel("Welcome to Java Swing!");
        label.setBounds(160, 100, 200, 30);

        // Create a button
        JButton button = new JButton("Press For More");
        button.setBounds(180, 180, 150, 40);

        // Add action listener to the button
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Show a dialog box with a message
                JOptionPane.showMessageDialog(frame, "It's Just a Prank!", "Prank Alert", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Add components to frame
        frame.add(label);
        frame.add(button);

        // Set frame properties
        frame.setSize(500, 400);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
