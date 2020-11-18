package kr.ac.kaist.se.view.toolbar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBarSimMode extends BaseToolBar {

    public ToolBarSimMode(){
        super();

        add(makeNavigationButton("icon_01_label", "ACTION01", "ToolTipText", "AltText"));
        add(makeNavigationButton("icon_02_target", "ACTION02", "ToolTipText", "AltText"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        if (actionCommand == "ACTION01"){
            System.out.println("Action01 is selected");
        }
    }
}
