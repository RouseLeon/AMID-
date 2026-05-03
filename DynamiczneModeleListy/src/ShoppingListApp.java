import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ShoppingListApp {

    private JFrame frame;
    private JPanel mainPanel;
    private JTextField itemInput;
    private JButton addButton;
    private JButton deleteButton;
    private JList<String> itemsList;
    private DefaultListModel<String> listModel;

    public ShoppingListApp() {
        // 🔥 Frame
        frame = new JFrame("Lista zakupów");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // 🔥 Panel główny
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 🔥 Model listy
        listModel = new DefaultListModel<>();
        itemsList = new JList<>(listModel);
        itemsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(itemsList);

        // 🔥 Górny panel (input + przycisk)
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        itemInput = new JTextField();
        addButton = new JButton("Dodaj");

        topPanel.add(itemInput, BorderLayout.CENTER);
        topPanel.add(addButton, BorderLayout.EAST);

        // 🔥 Dolny panel (delete)
        JPanel bottomPanel = new JPanel();
        deleteButton = new JButton("Usuń zaznaczony");
        bottomPanel.add(deleteButton);

        // 🔥 Dodanie do mainPanel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.setContentPane(mainPanel);

        // =========================
        // 🔹 AKCJE
        // =========================

        // ➕ Dodawanie (button)
        addButton.addActionListener(e -> addItem());

        // ➕ Dodawanie (ENTER)
        itemInput.addActionListener(e -> addItem());

        // ❌ Usuwanie (button)
        deleteButton.addActionListener(e -> deleteItem());

        // ❌ Usuwanie (double click)
        itemsList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    deleteItem();
                }
            }
        });

        frame.setVisible(true);
    }

    // =========================
    // 🔹 METODY
    // =========================

    private void addItem() {
        String text = itemInput.getText().trim();

        if (!text.isEmpty()) {
            listModel.addElement(text);
            itemInput.setText("");
        } else {
            JOptionPane.showMessageDialog(frame, "Pole nie może być puste!");
        }
    }

    private void deleteItem() {
        int index = itemsList.getSelectedIndex();

        if (index != -1) {
            listModel.remove(index);
        } else {
            JOptionPane.showMessageDialog(frame, "Wybierz element do usunięcia!");
        }
    }

    // =========================
    // 🔹 MAIN
    // =========================

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ShoppingListApp::new);
    }
}