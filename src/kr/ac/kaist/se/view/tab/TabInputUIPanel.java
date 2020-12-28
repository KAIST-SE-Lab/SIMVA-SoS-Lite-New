package kr.ac.kaist.se.view.tab;

import kr.ac.kaist.se.view.parts.FileChooserPanel;
import kr.ac.kaist.se.view.toolbar.ToolBarInputMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

public class TabInputUIPanel extends JPanel implements ActionListener {

    ToolBarInputMode toolBarInputMode;

    JButton openSimModelButton;
    JButton openSimConfigButton;
    JButton openSimScenarioButton;

    public TabInputUIPanel() {

        setLayout(new BorderLayout());

        initToolBar();
        initInputController();

    }

    private void initToolBar() {
        toolBarInputMode = new ToolBarInputMode();
        add(toolBarInputMode, BorderLayout.PAGE_START);
    }

    private void initInputController() {

        JPanel centerPanel = new JPanel();
        //centerPanel.setLayout(new GridLayout(2, 1));
        centerPanel.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();


        JPanel inputControllerPanel = new JPanel();
        inputControllerPanel.setLayout(new GridLayout(3, 1));
        inputControllerPanel.setPreferredSize(new Dimension(1200, 100));
        //inputControllerPanel.setMinimumSize(new Dimension(500, 120));

        JPanel inputDisplayPanel = new JPanel();
        inputDisplayPanel.setPreferredSize(new Dimension(1200, 700));

        FileChooserPanel simModelChooserPanel = new FileChooserPanel();
        simModelChooserPanel.setPreferredSize(new Dimension(800, 25));
        FileChooserPanel simConfigChooserPanel = new FileChooserPanel("Name", "Btn", false);
        simConfigChooserPanel.setPreferredSize(new Dimension(800, 25));
        FileChooserPanel simScenarioChooserPanel = new FileChooserPanel();
        simScenarioChooserPanel.setPreferredSize(new Dimension(800, 25));

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

//        inputControllerPanel.add(openSimModelButton);
//        inputControllerPanel.add(openSimConfigButton);
//        inputControllerPanel.add(openSimScenarioButton);

//        inputControllerPanel.add(fileChooserPanel);

        inputControllerPanel.add(simModelChooserPanel);
        inputControllerPanel.add(simConfigChooserPanel);
        inputControllerPanel.add(simScenarioChooserPanel);

        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridheight = 1;
        gc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(inputControllerPanel, gc);


        inputDisplayPanel.setBackground(Color.white);

        gc.gridx = 0;
        gc.gridy = 1;
        gc.gridheight = 4;
        gc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(inputDisplayPanel, gc);

//        centerPanel.add(inputControllerPanel);
//        centerPanel.add(inputDisplayPanel);

        add(centerPanel, BorderLayout.CENTER);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object sourceObj = e.getSource();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        if (sourceObj == openSimModelButton) {
            System.out.println("[" + timestamp + "] openSimModelButton is selected.");
        } else if (sourceObj == openSimConfigButton) {
            System.out.println("[" + timestamp + "] openSimConfigButton is selected.");
        } else if (sourceObj == openSimScenarioButton) {
            System.out.println("[" + timestamp + "] openSimScenarioButton is selected.");
        }
    }
}
