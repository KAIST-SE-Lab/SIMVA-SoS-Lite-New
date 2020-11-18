package kr.ac.kaist.se.view.tab;

import kr.ac.kaist.se.view.toolbar.ToolBarResultsMode;
import kr.ac.kaist.se.view.toolbar.ToolBarSimMode;

import javax.swing.*;
import java.awt.*;

public class TabResultsPanel extends JPanel {

    ToolBarResultsMode toolBarResultsMode;

    public TabResultsPanel(){

        setLayout(new BorderLayout());

        initToolBar();

    }

    private void initToolBar() {
        toolBarResultsMode = new ToolBarResultsMode();
        add(toolBarResultsMode, BorderLayout.PAGE_START);
    }
}
