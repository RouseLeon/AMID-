import javax.swing.*;
import java.awt.*;

public class Fah extends JFrame {

    public Fah() {
        super("Login");

        // Set BorderLayout for the frame
        setLayout(new BorderLayout());

        // ------------------ CENTER PANEL ------------------
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 2, 10, 10)); // 2 rows, 2 columns, 10px gaps

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);

        mainPanel.add(userLabel);
        mainPanel.add(userField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);

        add(mainPanel, BorderLayout.CENTER);

        // ------------------ SOUTH PANEL ------------------
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton loginButton = new JButton("Login");
        JButton cancelButton = new JButton("Cancel");

        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Automatically adjust window size
        pack();

        // Final settings
        setLocationRelativeTo(null); // center window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Ensure UI is created in the Event Dispatch Thread
        SwingUtilities.invokeLater(Fah::new);
    }
}
