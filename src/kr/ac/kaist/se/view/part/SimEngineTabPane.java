package kr.ac.kaist.se.view.part;

import javax.swing.*;
import java.awt.*;

public class SimEngineTabPane extends JPanel {

    JTabbedPane tabbedPane;

    JPanel panelTabInputUI;
    JPanel panelTabSimulationUI;
    JPanel panelTabResultsUI;

    public SimEngineTabPane(){
        super(new GridLayout(1, 1));
        tabbedPane = new JTabbedPane();

        panelTabInputUI = new JPanel();
        panelTabSimulationUI = new JPanel();
        panelTabResultsUI = new JPanel();

        tabbedPane.addTab("Simulation Inputs", panelTabInputUI);
        tabbedPane.addTab("Simulation", panelTabSimulationUI);
        tabbedPane.addTab("Simulation Results", panelTabResultsUI);

        add(tabbedPane);
    }
}
