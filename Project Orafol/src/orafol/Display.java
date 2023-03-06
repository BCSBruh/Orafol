package orafol;

import orafol.graphics.Render;
import orafol.graphics.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Display extends Canvas implements Runnable {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public static final String TITLE = "Orafol 3D View";

    private int[] pixels;

    private Thread thread;
    private boolean running = false;

    private Render render;
    private Screen screen;

    private BufferedImage img;

    public Display() {
        screen = new Screen(WIDTH, HEIGHT);
        img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
    }

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
        while (running) {
            tick();
            render();
        }

    }

    //tick() handles frames/time
    private void tick() {

    }

    //render() renders the scene
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.render();

        System.arraycopy(screen.pixels, 0, pixels, 0, WIDTH * HEIGHT);

        Graphics g = bs.getDrawGraphics();
        g.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
        g.dispose();
        bs.show();
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
