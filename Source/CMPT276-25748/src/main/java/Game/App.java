
package Game;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class App extends JFrame {

    public App() {

        initUI();
    }

    private void initUI() {

        add(new GameManager());

        setTitle("App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setSize(380, 420);
        setSize(600, 600);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var ex = new App();
            ex.setVisible(true);
        });
    }
}

