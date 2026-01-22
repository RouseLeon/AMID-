import javax.swing.*;

public class Zdarzenia {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Zliczanie kliknięć");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        final int[] counter = {0};
        JLabel label = new JLabel("Liczba kliknięć: 0", SwingConstants.CENTER);
        JButton button = new JButton("Kliknij mnie!");
        button.addActionListener(e -> {
            counter[0]++;
            label.setText("Liczba kliknięć: " + counter[0]);
        });
        frame.setLayout(new java.awt.BorderLayout());
        frame.add(label, java.awt.BorderLayout.CENTER);
        frame.add(button, java.awt.BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
