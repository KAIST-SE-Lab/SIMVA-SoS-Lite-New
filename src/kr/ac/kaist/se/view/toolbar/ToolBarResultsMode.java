package kr.ac.kaist.se.view.toolbar;

import java.awt.event.ActionEvent;

public class ToolBarResultsMode extends BaseToolBar {

    public ToolBarResultsMode() {
        super();

        add(makeNavigationButton("icon_01_label", "ACTION01", "ToolTipText", "AltText"));
        add(makeNavigationButton("icon_02_target", "ACTION02", "ToolTipText", "AltText"));
        add(makeNavigationButton("icon_03_behavior", "ACTION03", "ToolTipText", "AltText"));
        add(makeNavigationButton("icon_04_time", "ACTION04", "ToolTipText", "AltText"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        if (actionCommand == "ACTION01") {
            System.out.println("Action01 is selected");
        }
    }
}
