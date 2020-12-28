package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchWindow implements ActionListener {

    JFrame frame = new JFrame();

    JButton startButton1 = new JButton("Start1");
    JButton startButton2 = new JButton("Start2");
    JButton startButton3 = new JButton("Start3");

    JButton dialogButton = new JButton("Dialog");

    public LaunchWindow() {

        startButton1.setBounds(100, 100, 200, 100);
        startButton1.setFocusable(false);
        startButton1.addActionListener(this);

        startButton2.setBounds(100, 210, 200, 100);
        startButton2.setFocusable(false);
        startButton2.addActionListener(this);

        startButton3.setBounds(100, 320, 200, 100);
        startButton3.setFocusable(false);
        startButton3.addActionListener(this);

        dialogButton.setBounds(100, 430, 200, 100);
        dialogButton.setFocusable(false);
        dialogButton.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLayout(null);
        frame.setVisible(true);

        frame.add(startButton1);
        frame.add(startButton2);
        frame.add(startButton3);
        frame.add(dialogButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton1) {
            frame.dispose();
//            SimEngineFrame simEngineFrame = new SimEngineFrame();
            MyFrame myFrame = new MyFrame();
        } else if (e.getSource() == startButton2) {
            frame.dispose();
            MyFrame2 myFrame2 = new MyFrame2();
        } else if (e.getSource() == startButton3) {
            frame.dispose();
            GameFrame gameFrame = new GameFrame();
        } else if (e.getSource() == dialogButton) {
            //PLAIN, INFORMATION, QUESTION, WARNING, ERROR
            //JOptionPane.showMessageDialog(null, "A Dialog is shown", "Dialog", JOptionPane.PLAIN_MESSAGE);

            //int answer = JOptionPane.showConfirmDialog(null, "Really?", "Title", JOptionPane.YES_NO_CANCEL_OPTION);

            //String name = JOptionPane.showInputDialog("What is your name?: ");

            String[] responses = {"Response01", "Response02", "Response03"};
            JOptionPane.showOptionDialog(null,
                    "Message",
                    "Title",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    responses,
                    0);
        }
    }
}
