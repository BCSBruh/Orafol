package assignment1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Assignment1 extends JFrame {
    AppPanel panel;

    public Assignment1() {
        super("Jerome Larson");

        Container cp = this.getContentPane();
        cp.setLayout(new BorderLayout());

        panel = new AppPanel();
        cp.add(panel, BorderLayout.CENTER);

        JButton buttonLeft = new JButton("<");
        JButton buttonUp = new JButton("^");
        JButton buttonDown = new JButton("v");
        JButton buttonRight = new JButton(">");

        ButtonGroup arrows = new ButtonGroup();
        arrows.add(buttonDown);
        arrows.add(buttonLeft);
        arrows.add(buttonRight);
        arrows.add(buttonUp);

        JRadioButton red = new JRadioButton("Red");
        JRadioButton green = new JRadioButton("Green");
        JRadioButton blue = new JRadioButton("Blue");
        red.setSelected(true);

        ButtonGroup colors = new ButtonGroup();
        colors.add(red);
        colors.add(green);
        colors.add(blue);

        JPanel buttons = new JPanel();
        BoxLayout arrowBox = new BoxLayout(buttons, BoxLayout.Y_AXIS);
        buttons.setLayout(arrowBox);
        buttons.add(Box.createVerticalGlue());

        buttons.add(red);
        buttons.add(green);
        buttons.add(blue);
        buttons.add(Box.createVerticalGlue());

        buttons.add(buttonLeft);
        buttons.add(buttonRight);
        buttons.add(buttonUp);
        buttons.add(buttonDown);
        buttons.add(Box.createVerticalGlue());

        cp.add(buttons, BorderLayout.EAST);
    }

    public static void main(String[] args) {
        Assignment1 frame = new Assignment1();
        frame.setBounds(100, 100, 500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class AppPanel extends JPanel {
    int x = 100;
    int y = 100;
    int w = 60;

    Color c = Color.RED;

    public AppPanel() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                x = e.getX();
                y = e.getY();
                repaint();
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(c);
        g.fillOval(x-w/3, y-w/3, 2*w/3, 2*w/3);
        int s = 30;
        g.setColor(Color.BLACK);
        g.drawOval(x-w/3, y-w/3, 2*w/3, 2*w/3);
        g.drawLine(x-s, y, x+s, y);
        g.drawLine(x, y-s, x, y+s);
    }
}