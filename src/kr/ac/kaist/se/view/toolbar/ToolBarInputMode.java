package kr.ac.kaist.se.view.toolbar;

import kr.ac.kaist.se.view.SimInputUI;

import java.awt.event.ActionEvent;

public class ToolBarInputMode extends BaseToolBar {

    public ToolBarInputMode(){
        super();

        add(makeNavigationButton("icon_01_label", "ACTION01", "ToolTipText", "AltText"));
        add(makeNavigationButton("icon_02_target", "ACTION02", "ToolTipText", "AltText"));
        add(makeNavigationButton("icon_03_behavior", "ACTION03", "ToolTipText", "AltText"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        if (actionCommand == "ACTION01"){
            System.out.println("Action01 is selected");
            //SimInputUI.simStatusLabel.setText("Action01 is selected.");
        }
    }
}
