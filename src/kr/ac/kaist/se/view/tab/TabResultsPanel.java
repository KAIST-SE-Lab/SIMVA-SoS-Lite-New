package kr.ac.kaist.se.view.tab;

import kr.ac.kaist.se.view.toolbar.ToolBarResultsMode;
import kr.ac.kaist.se.view.toolbar.ToolBarSimMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

public class TabResultsPanel extends JPanel implements ActionListener {

    ToolBarResultsMode toolBarResultsMode;

    public TabResultsPanel(){

        setLayout(new BorderLayout());

        initToolBar();

    }

    private void initToolBar() {
        toolBarResultsMode = new ToolBarResultsMode();
        add(toolBarResultsMode, BorderLayout.PAGE_START);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object sourceObj = e.getSource();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    }
}
