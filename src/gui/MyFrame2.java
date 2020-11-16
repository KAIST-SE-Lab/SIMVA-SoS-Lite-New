package gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame2 extends JFrame implements ActionListener, ChangeListener {

    JProgressBar bar;

    public MyFrame2(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());


        bar = new JProgressBar(0, 100);
        bar.setValue(0);
        bar.setBounds(0, 0, 400, 100);
        bar.setPreferredSize(new Dimension(400, 100));
        bar.setStringPainted(true);
        bar.setForeground(Color.RED);

        this.add(bar, BorderLayout.CENTER);


//        this.pack();
        this.setSize(500, 500);
        this.setVisible(true);

        fill();


    }

    public void fill(){
        int counter = 0;

        while (counter <= 100){
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

    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}
