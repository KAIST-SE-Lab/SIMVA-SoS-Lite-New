package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPanel2 extends JPanel implements ActionListener {

    final int PANEL_WIDTH = 300;
    final int PANEL_HEIGHT = 300;

    Image image;

    Timer timer;

    int xVelocity = 1;
    int yVelocity = 1;

    int x = 0;
    int y = 0;

    public MyPanel2() {
        image = new ImageIcon("logo.png").getImage();

        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.black);

        timer = new Timer(30, this);
        timer.start();
    }

    public void paint(Graphics g) {

        super.paint(g); //paint background

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(image, x, y, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (x >= PANEL_WIDTH) {
            xVelocity = xVelocity * -1;
        }

        x = x + xVelocity;
//        y = y + yVelocity;
        repaint();
    }
}
