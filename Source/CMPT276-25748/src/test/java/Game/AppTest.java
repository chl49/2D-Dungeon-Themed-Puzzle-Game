
package Game;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;

public class AppTest extends JFrame {


    public AppTest() {

        initUI();
    }

    public void initUI() {
        add(GameManagerTest.instance());

        setTitle("AppTest");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450, 450);
        setLocationRelativeTo(null);
    }
    public static void main(String[] args) throws IllegalStateException, IOException {

        EventQueue.invokeLater(() -> {

            var ex = new AppTest();
            ex.setVisible(true);
        });
    }
}