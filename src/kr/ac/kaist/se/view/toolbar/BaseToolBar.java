package kr.ac.kaist.se.view.toolbar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class BaseToolBar extends JToolBar implements ActionListener {

    public BaseToolBar(){
        setFloatable(true);
        setRollover(true);
        setBounds(0, 0, getWidth(), 30);
        setVisible(true);
        //setBackground(Color.black);
    }

    protected JButton makeNavigationButton(String imageName,
                                           String actionCommand,
                                           String toolTipText,
                                           String altText){
        JButton button = new JButton();

        String imgLocation = imageName + ".png";
        //URL imageURL = BaseToolBar.class.getResource(imgLocation);

        ImageIcon imageIcon = new ImageIcon(imageName + ".png", altText);
        Image originalImg = imageIcon.getImage();
        Image scaledImg = originalImg.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        button.setActionCommand(actionCommand);
        button.setToolTipText(toolTipText);
        button.addActionListener(this);

        if (imageIcon != null) {                      //image found
            button.setIcon(scaledIcon);
        } else {                                     //no image found
            button.setText(altText);
            System.err.println("Resource not found: " + imgLocation);
        }

        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
    }
}
