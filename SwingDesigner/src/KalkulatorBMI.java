import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KalkulatorBMI extends JFrame {

    private JTextField wieghtField, heightField;
    private JLabel lblWynik;
    private JButton obliczBMIButton;
    private JPanel mainPanel;


    public KalkulatorBMI() {
        // Ustawienia okna
        setTitle("Kalkulator BMI");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(mainPanel);


        add(new JLabel("Waga (kg):"));
        wieghtField = new JTextField();
        add(wieghtField);

        add(new JLabel("Wzrost (cm):"));
        heightField = new JTextField();
        add(heightField);

        JButton btnOblicz = new JButton("Oblicz");
        add(btnOblicz);

        lblWynik = new JLabel("Wynik: --", SwingConstants.CENTER);
        add(lblWynik);


        btnOblicz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obliczBMI();
            }
        });
    }

    private void obliczBMI() {
        try {

            double waga = Double.parseDouble(wieghtField.getText());
            double wzrostCm = Double.parseDouble(heightField.getText());


            double wzrostM = wzrostCm / 100;
            double bmi = waga / (wzrostM * wzrostM);


            lblWynik.setText(String.format("BMI: %.2f", bmi));
            lblWynik.setForeground(Color.BLACK);

        } catch (NumberFormatException ex) {

            lblWynik.setText("Błąd: Wpisz liczby!");
            lblWynik.setForeground(Color.RED);
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new KalkulatorBMI().setVisible(true);
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}