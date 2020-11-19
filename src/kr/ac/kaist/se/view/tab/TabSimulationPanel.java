package kr.ac.kaist.se.view.tab;

import kr.ac.kaist.se.view.toolbar.ToolBarSimMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

public class TabSimulationPanel extends JPanel implements ActionListener {

    ToolBarSimMode toolBarSimMode;

    public TabSimulationPanel(){

        setLayout(new BorderLayout());
        initToolBar();

    }

    private void initToolBar() {
        toolBarSimMode = new ToolBarSimMode();
        add(toolBarSimMode, BorderLayout.PAGE_START);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object sourceObj = e.getSource();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    }
}
