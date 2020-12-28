package gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class MyFrame2 extends JFrame implements ActionListener, ChangeListener, KeyListener, MouseListener {

    JProgressBar bar;

    JMenuBar menuBar;
    JMenu menu1;
    JMenu menu2;
    JMenu menu3;
    JMenuItem item1A;
    JMenuItem item1B;
    JMenuItem item1C;
    JMenuItem item2A;
    JMenuItem item2B;
    JMenuItem item3A;

    ImageIcon gmailIcon;

    MyPanel myPanel;
    MyPanel2 myPanel2;


    public MyFrame2() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());


        bar = new JProgressBar(0, 100);
        bar.setValue(0);
        bar.setBounds(0, 0, 400, 100);
        bar.setPreferredSize(new Dimension(400, 100));
        bar.setStringPainted(true);
        bar.setForeground(Color.RED);

        menuBar = new JMenuBar();

        menu1 = new JMenu("Menu1");
        menu2 = new JMenu("Menu2");
        menu3 = new JMenu("Menu3");

        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);

        item1A = new JMenuItem("Load");
        item1B = new JMenuItem("Save");
        item1C = new JMenuItem("Exit");
        item2A = new JMenuItem("2A");
        item2B = new JMenuItem("2B");
        item3A = new JMenuItem("3A");

        menu1.add(item1A);
        menu1.add(item1B);
        menu1.add(item1C);
        menu2.add(item2A);
        menu2.add(item2B);
        menu3.add(item3A);

        item1A.addActionListener(this);
        item1B.addActionListener(this);
        item1C.addActionListener(this);

        item1A.setMnemonic(KeyEvent.VK_L);
        item1B.setMnemonic(KeyEvent.VK_S);
        item1C.setMnemonic(KeyEvent.VK_E);

        item2A.addActionListener(this);

        gmailIcon = new ImageIcon("logo.png");
        item1A.setIcon(gmailIcon);

        myPanel = new MyPanel();
        myPanel2 = new MyPanel2();


        this.setJMenuBar(menuBar);
//        this.add(myPanel);
        this.add(myPanel2);
        //this.add(bar, BorderLayout.CENTER);


//        this.pack();
        this.setSize(500, 500);
        this.setVisible(true);
        this.addKeyListener(this);

//        fill();


    }


    public void fill() {
        int counter = 0;

        while (counter <= 100) {
            bar.setValue(counter);
            bar.setVisible(true);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter++;
        }

        bar.setString("Done");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == item1A) {
            System.out.println("item1A is selected");
        } else if (e.getSource() == item1B) {
            System.out.println("item1B is selected");
        } else if (e.getSource() == item1C) {
            System.out.println("item1C is selected");
            System.exit(0);
        } else if (e.getSource() == item2A) {
            JFileChooser fileChooser = new JFileChooser();
//            System.out.println(fileChooser.showOpenDialog(null));

            fileChooser.setCurrentDirectory(new File("C:\\Users\\ymbae\\")); // "."

//            int response = fileChooser.showOpenDialog(null); //to open
            int response = fileChooser.showSaveDialog(null); //to save

            if (response == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                System.out.println(file);
            }
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("You typed: " + e.getKeyChar() + " " + e.getKeyCode());
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }



    /* Drag & Drop */

    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        //other contents
    }

    private class ClickListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            //prevPt = e.getPoint();
        }
    }

    private class DragListener extends MouseMotionAdapter {
        public void mouseDragger(MouseEvent e) {
//            Point currentPt = e.getPoint();
//
//            imageCorner.translate(
//                (int)(currentPt.getX() - prevPt.getX()),
//                (int)(currentPt.getY() - prevPt.getY()),
//                    );
//            prevPt = currentPt;
//            repaint();
        }
    }
}
