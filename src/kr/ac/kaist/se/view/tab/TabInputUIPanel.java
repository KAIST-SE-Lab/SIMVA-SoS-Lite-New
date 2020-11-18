package kr.ac.kaist.se.view.tab;

import kr.ac.kaist.se.view.SimInputUI;
import kr.ac.kaist.se.view.toolbar.ToolBarInputMode;
import kr.ac.kaist.se.view.toolbar.ToolBarSimMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TabInputUIPanel extends JPanel implements ActionListener {

    ToolBarInputMode toolBarInputMode;

    JButton openSimModelButton;
    JButton openSimConfigButton;
    JButton openSimScenarioButton;

    public TabInputUIPanel(){

        System.out.println(getParent());
        setLayout(new BorderLayout());

        initToolBar();
        initButtons();

    }

    private void initToolBar() {
        toolBarInputMode = new ToolBarInputMode();
        add(toolBarInputMode, BorderLayout.PAGE_START);
        System.out.println(toolBarInputMode.getParent());
        System.out.println(toolBarInputMode.getTopLevelAncestor());
    }

    private void initButtons() {
        openSimModelButton = new JButton("Open a simulation model");
        //openSimModelButton.setBounds(30, 30, 340, 30);
        openSimModelButton.setPreferredSize(new Dimension(340, 30));
        openSimConfigButton = new JButton("Open a simulation configuration");
        openSimConfigButton.setPreferredSize(new Dimension(340, 30));
        openSimScenarioButton = new JButton("Open a simulation scenario");
        openSimScenarioButton.setPreferredSize(new Dimension(340, 30));

        openSimModelButton.addActionListener(this);
        openSimConfigButton.addActionListener(this);
        openSimScenarioButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
