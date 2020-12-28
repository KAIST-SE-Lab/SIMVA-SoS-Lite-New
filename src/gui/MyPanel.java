package gui;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {

    Image image;

    public MyPanel() {

        image = new ImageIcon("logo.png").getImage();

        this.setPreferredSize(new Dimension(300, 300));

    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setPaint(Color.blue);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawLine(0, 0, 300, 300);

        g2d.drawRect(0, 0, 100, 100);
        g2d.fillRect(100, 100, 100, 100);

        g2d.setPaint(Color.orange);
        g2d.drawOval(200, 200, 100, 100);
        g2d.fillOval(300, 300, 100, 100);

        int[] xPoints = {150, 250, 350};
        int[] yPoints = {300, 150, 300};
        g2d.drawPolygon(xPoints, yPoints, 3); //fillPolygon

        g2d.drawImage(image, 0, 0, null);

    }
}
