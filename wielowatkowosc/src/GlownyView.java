import javax.swing.*;

public class GlownyView extends JFrame {

    JTextField userField = new JTextField();
    JPasswordField passField = new JPasswordField();
    JButton loginButton = new JButton("Zaloguj");
    JLabel statusLabel = new JLabel(" ");

    public GlownyView() {
        setTitle("System Logowania");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        userField.setBounds(100, 20, 150, 25);
        passField.setBounds(100, 60, 150, 25);
        loginButton.setBounds(100, 100, 150, 30);
        statusLabel.setBounds(50, 140, 250, 25);

        add(new JLabel("Login:")).setBounds(30, 20, 60, 25);
        add(new JLabel("Hasło:")).setBounds(30, 60, 60, 25);

        add(userField);
        add(passField);
        add(loginButton);
        add(statusLabel);
    }
}