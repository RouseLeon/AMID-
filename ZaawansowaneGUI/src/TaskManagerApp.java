import javax.swing.*;
import java.awt.*;

public class TaskManagerApp extends JFrame {

    public TaskManagerApp() {
        setTitle("Menedżer Zadań");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        TaskTableModel model = new TaskTableModel();
        JTable table = new JTable(model);

        table.getColumnModel()
                .getColumn(2)
                .setCellRenderer(new PriorityRenderer());

        JScrollPane tableScroll = new JScrollPane(table);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2, 1, 5, 5));

        JButton addButton = new JButton("Dodaj Zadanie");
        JButton removeButton = new JButton("Usuń Wybrane");

        controlPanel.add(addButton);
        controlPanel.add(removeButton);

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                controlPanel,
                tableScroll
        );

        splitPane.setDividerLocation(200);
        add(splitPane);

        addButton.addActionListener(e ->
                model.addTask(new Task(
                        "Nowe zadanie",
                        false,
                        "Wysoki"
                ))
        );

        removeButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                model.removeTask(row);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new TaskManagerApp().setVisible(true)
        );
    }
}
