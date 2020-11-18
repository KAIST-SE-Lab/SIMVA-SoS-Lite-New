package kr.ac.kaist.se.view.tab;

import kr.ac.kaist.se.view.toolbar.ToolBarSimMode;

import javax.swing.*;
import java.awt.*;

public class TabSimulationPanel extends JPanel {

    ToolBarSimMode toolBarSimMode;

    public TabSimulationPanel(){

        setLayout(new BorderLayout());
        initToolBar();

    }

    private void initToolBar() {
        toolBarSimMode = new ToolBarSimMode();
        add(toolBarSimMode, BorderLayout.PAGE_START);
    }
}
