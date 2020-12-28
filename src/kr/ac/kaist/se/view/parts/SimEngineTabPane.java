package kr.ac.kaist.se.view.parts;

import kr.ac.kaist.se.view.tab.TabInputUIPanel;
import kr.ac.kaist.se.view.tab.TabResultsPanel;
import kr.ac.kaist.se.view.tab.TabSimulationPanel;

import javax.swing.*;
import java.awt.*;

public class SimEngineTabPane extends JPanel {

    JTabbedPane tabbedPane;

//    JPanel panelTabInputUI;
//    JPanel panelTabSimulationUI;
//    JPanel panelTabResultsUI;

    TabInputUIPanel tabInputUIPanel;
    TabSimulationPanel tabSimulationPanel;
    TabResultsPanel tabResultsPanel;

    public SimEngineTabPane() {
        super(new GridLayout(1, 1));
        tabbedPane = new JTabbedPane();

//        panelTabInputUI = new JPanel();
//        panelTabSimulationUI = new JPanel();
//        panelTabResultsUI = new JPanel();

        tabInputUIPanel = new TabInputUIPanel();
        tabSimulationPanel = new TabSimulationPanel();
        tabResultsPanel = new TabResultsPanel();

        tabbedPane.addTab("Simulation Inputs", tabInputUIPanel);
        tabbedPane.addTab("Simulation", tabSimulationPanel);
        tabbedPane.addTab("Simulation Results", tabResultsPanel);

        add(tabbedPane);
    }
}
