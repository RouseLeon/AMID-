import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TaskTableModel extends AbstractTableModel {

    private final String[] columns = {"Nazwa", "Status", "Priorytet"};
    private final List<Task> tasks = new ArrayList<>();

    @Override
    public int getRowCount() {
        return tasks.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 1) return Boolean.class; // Status → checkbox
        return String.class;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Task task = tasks.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> task.getName();
            case 1 -> task.isCompleted();
            case 2 -> task.getPriority();
            default -> null;
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 1; // tylko status edytowalny
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 1) {
            tasks.get(rowIndex).setCompleted((Boolean) aValue);
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
        fireTableDataChanged();
    }

    public void removeTask(int row) {
        tasks.remove(row);
        fireTableDataChanged();
    }
}
