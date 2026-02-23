import javax.swing.*;
import java.util.concurrent.ExecutionException;

public class GlownyController {

    private GlownyView view;
    private UzytkownikModel model;

    public GlownyController(GlownyView view, UzytkownikModel model) {
        this.view = view;
        this.model = model;

        view.loginButton.addActionListener(e -> logowanie());
    }

    private void logowanie() {

        String user = view.userField.getText();
        String pass = new String(view.passField.getPassword());

        SwingWorker<Boolean, Void> worker = new SwingWorker<>() {

            @Override
            protected Boolean doInBackground() {
                view.loginButton.setEnabled(false);
                view.statusLabel.setText("Trwa weryfikacja danych...");
                return model.walidujLogowanie(user, pass);
            }

            @Override
            protected void done() {
                try {
                    boolean wynik = get();
                    view.loginButton.setEnabled(true);

                    if (wynik) {
                        view.statusLabel.setText("Logowanie pomyślne!");
                    } else {
                        view.statusLabel.setText("Błędny login lub hasło!");
                    }

                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };

        worker.execute();
    }
}