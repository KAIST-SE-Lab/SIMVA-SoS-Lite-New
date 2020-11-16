package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchWindow implements ActionListener {

    JFrame frame = new JFrame();
    JButton startButton = new JButton("Start");
    JButton dialogButton = new JButton("Dialog");

    public LaunchWindow(){

        startButton.setBounds(100, 100, 200, 100);
        startButton.setFocusable(false);
        startButton.addActionListener(this);

        dialogButton.setBounds(100, 220, 200, 100);
        dialogButton.setFocusable(false);
        dialogButton.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLayout(null);
        frame.setVisible(true);

        frame.add(startButton);
        frame.add(dialogButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton){
            frame.dispose();
            SimEngineFrame simEngineFrame = new SimEngineFrame();
        } else if(e.getSource() == dialogButton) {
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
