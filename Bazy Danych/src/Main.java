import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

 class KsiegarniaApp extends JFrame {

    private static final String URL = "jdbc:mysql://localhost:3306/ksiegarnia";
    private static final String USER = "root";
    private static final String PASSWORD = "haslo"; // zmień na swoje

    private JTable table;
    private DefaultTableModel model;

    private JTextField tytulField;
    private JTextField autorField;
    private JTextField rokField;

    public KsiegarniaApp() {

        setTitle("Księgozbiór");
        setSize(600,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Tytuł");
        model.addColumn("Autor");
        model.addColumn("Rok");

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel panel = new JPanel(new GridLayout(4,2));

        panel.add(new JLabel("Tytuł"));
        tytulField = new JTextField();
        panel.add(tytulField);

        panel.add(new JLabel("Autor"));
        autorField = new JTextField();
        panel.add(autorField);

        panel.add(new JLabel("Rok"));
        rokField = new JTextField();
        panel.add(rokField);

        add(panel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();

        JButton addBtn = new JButton("Dodaj");
        JButton deleteBtn = new JButton("Usuń");
        JButton updateBtn = new JButton("Aktualizuj");

        buttonPanel.add(addBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(updateBtn);

        add(buttonPanel, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> addBook());
        deleteBtn.addActionListener(e -> deleteBook());
        updateBtn.addActionListener(e -> updateBook());

        loadBooks();
    }

    private void addBook() {

        String tytul = tytulField.getText();
        String autor = autorField.getText();
        int rok = Integer.parseInt(rokField.getText());

        String sql = "INSERT INTO ksiazki (tytul, autor, rok_wydania) VALUES (?, ?, ?)";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tytul);
            ps.setString(2, autor);
            ps.setInt(3, rok);

            ps.executeUpdate();

            loadBooks();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteBook() {

        int selectedRow = table.getSelectedRow();

        if(selectedRow == -1){
            JOptionPane.showMessageDialog(this,"Wybierz książkę");
            return;
        }

        int id = (int) model.getValueAt(selectedRow,0);

        String sql = "DELETE FROM ksiazki WHERE id=?";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1,id);
            ps.executeUpdate();

            loadBooks();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private void updateBook(){

        int selectedRow = table.getSelectedRow();

        if(selectedRow == -1){
            JOptionPane.showMessageDialog(this,"Wybierz książkę");
            return;
        }

        int id = (int) model.getValueAt(selectedRow,0);

        String tytul = tytulField.getText();
        String autor = autorField.getText();
        int rok = Integer.parseInt(rokField.getText());

        String sql = "UPDATE ksiazki SET tytul=?, autor=?, rok_wydania=? WHERE id=?";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1,tytul);
            ps.setString(2,autor);
            ps.setInt(3,rok);
            ps.setInt(4,id);

            ps.executeUpdate();

            loadBooks();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private void loadBooks(){

        model.setRowCount(0);

        String sql = "SELECT * FROM ksiazki";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while(rs.next()){

                int id = rs.getInt("id");
                String tytul = rs.getString("tytul");
                String autor = rs.getString("autor");
                int rok = rs.getInt("rok_wydania");

                model.addRow(new Object[]{id, tytul, autor, rok});
            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new KsiegarniaApp().setVisible(true);
        });

    }
}