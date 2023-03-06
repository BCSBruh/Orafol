package orafol;

import javax.swing.*;
import java.awt.*;

public class Display extends Canvas implements Runnable {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public static final String TITLE = "Orafol 3D View";

    private Thread thread;
    private boolean running = false;

    private void start() {
        //Want this to only run on initialization
        if (running)
            return;

        thread = new Thread(this);
        thread.start();
    }

    private void stop() {
        if (!running)
            return;

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    @Override
    public void run() {

    }

    public static void main(String[] args) {
        Display model = new Display();
        JFrame frame = new JFrame();

        frame.add(model);
        frame.pack();
        frame.setSize(WIDTH, HEIGHT);
        frame.setTitle(TITLE);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        model.start();
    }
}
