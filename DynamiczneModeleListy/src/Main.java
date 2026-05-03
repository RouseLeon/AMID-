import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {
  private JFrame frame;
  private JTextField itemInput;
  private JButton addButton;
  private JButton deleteButton;
  private JList<String> itemsList;
  private JLabel counterLabel;

  private DefaultListModel<String> listModel;

  public Main() {

    frame = new JFrame("Menedżer Zadań");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 400);
    frame.setLayout(new BorderLayout());


    listModel = new DefaultListModel<>();

    JPanel topPanel = new JPanel(new BorderLayout());
    itemInput = new JTextField();
    addButton = new JButton("Dodaj");

    topPanel.add(itemInput, BorderLayout.CENTER);
    topPanel.add(addButton, BorderLayout.EAST);

    itemsList = new JList<>(listModel);
    JScrollPane scrollPane = new JScrollPane(itemsList);

    JPanel bottomPanel = new JPanel(new BorderLayout());
    deleteButton = new JButton("Usuń zaznaczone");
    counterLabel = new JLabel();

    bottomPanel.add(deleteButton, BorderLayout.WEST);
    bottomPanel.add(counterLabel, BorderLayout.EAST);

    frame.add(topPanel, BorderLayout.NORTH);
    frame.add(scrollPane, BorderLayout.CENTER);
    frame.add(bottomPanel, BorderLayout.SOUTH);

    updateCounter();

    addButton.addActionListener(e -> {
      String text = itemInput.getText().trim();

      if (text.isEmpty()) {
        JOptionPane.showMessageDialog(frame, "Zadanie nie może być puste!");
        return;
      }

      listModel.addElement(text);
      itemInput.setText("");
      updateCounter();
    });

    deleteButton.addActionListener(e -> {
      int index = itemsList.getSelectedIndex();

      if (index != -1) {
        listModel.remove(index);
        updateCounter();
      } else {
        JOptionPane.showMessageDialog(frame, "Wybierz element!");
      }
    });

    itemsList.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
          int index = itemsList.locationToIndex(e.getPoint());
          if (index != -1) {
            listModel.remove(index);
            updateCounter();
          }
        }
      }
    });

    frame.setVisible(true);
  }

  private void updateCounter() {
    counterLabel.setText("Liczba zadań: " + listModel.getSize());
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(Main::new);
  }
}