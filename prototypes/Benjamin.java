import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
   
public class Benjamin {
    
    // Create JFrame for the main window
    private JFrame frame;
    
    // Create a text field for user input
    private JTextField textField;
    
    // Create a label to display the text
    private JLabel label;

    // Constructor to set up the GUI components
    public Benjamin() {
        // Initialize the frame
        frame = new JFrame("Simple Java Swing Application");
        
        // Set the frame size and default close operation
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create a panel to hold the components
        JPanel panel = new JPanel();
        frame.add(panel);
        
        // Set layout for the panel
        panel.setLayout(new FlowLayout());
        
        // Create a label with default text
        label = new JLabel("Enter text and press the button.");
        panel.add(label);
        
        // Create a text field with default text
        textField = new JTextField(20);
        panel.add(textField);
        
        // Create a button and add an ActionListener
        JButton button = new JButton("Display Text");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // When button is clicked, update the label with the text from the text field
                label.setText("You entered: " + textField.getText());
            }
        });
        panel.add(button);
        
        // Make the frame visible
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Create an instance of the Benjamin class to run the application
        new Benjamin();
    }
}