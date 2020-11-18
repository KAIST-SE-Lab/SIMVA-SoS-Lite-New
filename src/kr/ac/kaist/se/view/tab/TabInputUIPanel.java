package kr.ac.kaist.se.view.tab;

import kr.ac.kaist.se.view.SimInputUI;
import kr.ac.kaist.se.view.toolbar.ToolBarInputMode;
import kr.ac.kaist.se.view.toolbar.ToolBarSimMode;

import javax.swing.*;
import java.awt.*;

public class TabInputUIPanel extends JPanel {

    ToolBarInputMode toolBarInputMode;

    public TabInputUIPanel(){

        System.out.println(getParent());
        setLayout(new BorderLayout());

        initToolBar();

    }

    private void initToolBar() {
        toolBarInputMode = new ToolBarInputMode();
        add(toolBarInputMode, BorderLayout.PAGE_START);
        System.out.println(toolBarInputMode.getParent());
        System.out.println(toolBarInputMode.getTopLevelAncestor());
    }
}
