public class Main {
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {
            GlownyView view = new GlownyView();
            UzytkownikModel model = new UzytkownikModel();
            new GlownyController(view, model);
            view.setVisible(true);
        });
    }
}