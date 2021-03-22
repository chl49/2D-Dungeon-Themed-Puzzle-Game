
package Game;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class App extends JFrame {

    public App() {

        initUI();
    }

    private void initUI() {
        add(new GameManager());

        setTitle("App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450, 450);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) throws IllegalStateException, IOException {

        EventQueue.invokeLater(() -> {

            var ex = new App();
            ex.setVisible(true);
        });
    }
}

