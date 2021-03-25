
package Game;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;

public class App extends JFrame {

    public App() {

        initUI();
    }

    private void initUI() {
        add(GameManager.instance());

        setTitle("App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(475, 500);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) throws IllegalStateException, IOException {

        EventQueue.invokeLater(() -> {

            var ex = new App();
            ex.setVisible(true);
        });
    }
}
