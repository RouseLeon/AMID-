import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class PriorityRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        Component c = super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);

        String priority = (String) value;

        if ("Wysoki".equals(priority)) {
            c.setBackground(Color.RED);
            c.setForeground(Color.WHITE);
        } else {
            c.setBackground(Color.WHITE);
            c.setForeground(Color.BLACK);
        }

        return c;
    }
}
