package kr.ac.kaist.se.view;

import kr.ac.kaist.se.view.part.SimEngineTabPane;
import kr.ac.kaist.se.view.part.SimStatusBarPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SimInputUI implements ActionListener, Runnable {

    protected TimerThread timerThread;

    ImageIcon simEngineIcon;

    JFrame inputUIframe;

    JMenuBar menuBar;
    JMenu fileMenu;
    JMenu simulationMenu;
    JMenu resultsMenu;
    JMenu helpMenu;
    JMenuItem openSimModelMenuItem;
    JMenuItem openSimConfigMenuItem;
    JMenuItem openSimScenarioMenuItem;
    JMenuItem exitMenuItem;
    JMenuItem configSimulationMenuItem;
    JMenuItem startSimulationMenuItem;
    JMenuItem stopSimulationMenuItem;
    JMenuItem exportResultsMenuItem;
    JMenuItem exportLogsMenuItem;
    JMenuItem helpMenuItem;
    JMenuItem aboutMenuItem;


    SimEngineTabPane simEngineTabPane;



    SimStatusBarPanel statusBar;

    JLabel simStatusLabel;
    JLabel simDataLabel;
    JLabel simTimeLabel;

    public SimInputUI(){
    }

    @Override
    public void run() {
        simEngineIcon = new ImageIcon("logo.png");

        inputUIframe = new JFrame();
        inputUIframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inputUIframe.setTitle("Simulation Engine: Simulation Input");
        inputUIframe.setIconImage(simEngineIcon.getImage());
        inputUIframe.setSize(800, 800);
        //inputUIframe.setLayout(new GridLayout(3,1));
        inputUIframe.setLayout(new BorderLayout());

        initMenu();

        initStatusBar();

        initTabbedPanes();

        /* Add components */
        inputUIframe.setJMenuBar(menuBar);
        //inputUIframe.add(toolBarSimMode, BorderLayout.PAGE_START);


        //inputUIframe.add(toolbar, BorderLayout.PAGE_START);

        //inputUIframe.add(openSimModelButton, "Center");
        //inputUIframe.add(openSimConfigButton, "Center");
        //inputUIframe.add(openSimScenarioButton, "Center");

        Container contentPane = inputUIframe.getContentPane();
        contentPane.setLayout(new BorderLayout());

        inputUIframe.add(statusBar, BorderLayout.SOUTH);
        inputUIframe.add(simEngineTabPane, BorderLayout.CENTER);

        //inputUIframe.pack();


        inputUIframe.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
            }

            @Override
            public void windowClosing(WindowEvent e) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                System.out.println("[" + timestamp + "] Simulation engine is terminated.");
                exitProcedure();
                super.windowClosing(e);
            }
        });

        timerThread = new TimerThread(simDataLabel, simTimeLabel);
        timerThread.start();

        inputUIframe.pack();
        inputUIframe.setVisible(true);

        //System.out.println(simDataLabel.getTopLevelAncestor());
    }

    private void exitProcedure() {
        timerThread.setRunning(false);
        System.exit(0);
    }

    private void initStatusBar() {
        statusBar = new SimStatusBarPanel();

        simStatusLabel = new JLabel("Status Information");
        simDataLabel = new JLabel("Data");
        simTimeLabel = new JLabel("Time");

        simDataLabel.setHorizontalAlignment(JLabel.CENTER);
        simTimeLabel.setHorizontalAlignment(JLabel.CENTER);

        statusBar.setLeftComponent(simStatusLabel);
        statusBar.addRightComponent(simDataLabel);
        statusBar.addRightComponent(simTimeLabel);
    }


    private void initMenu() {
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        simulationMenu = new JMenu("Simulation");
        resultsMenu = new JMenu("Results");
        helpMenu = new JMenu("Help");

        openSimModelMenuItem = new JMenuItem("Open Simulation Model");
        openSimModelMenuItem.setMnemonic(KeyEvent.VK_M);
        openSimModelMenuItem.addActionListener(this);
        openSimConfigMenuItem = new JMenuItem("Open Simulation Configuration");
        openSimConfigMenuItem.setMnemonic(KeyEvent.VK_C);
        openSimConfigMenuItem.addActionListener(this);
        openSimScenarioMenuItem = new JMenuItem("Open Simulation Scenario");
        openSimScenarioMenuItem.setMnemonic(KeyEvent.VK_S);
        openSimScenarioMenuItem.addActionListener(this);
        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setMnemonic(KeyEvent.VK_X);
        exitMenuItem.addActionListener(this);

        configSimulationMenuItem = new JMenuItem("Config Simulation");
        configSimulationMenuItem.setMnemonic(KeyEvent.VK_G);
        configSimulationMenuItem.addActionListener(this);
        startSimulationMenuItem = new JMenuItem("Start Simulation");
        startSimulationMenuItem.setMnemonic(KeyEvent.VK_S);
        startSimulationMenuItem.addActionListener(this);
        stopSimulationMenuItem = new JMenuItem("Stop Simulation");
        stopSimulationMenuItem.setMnemonic(KeyEvent.VK_T);
        stopSimulationMenuItem.addActionListener(this);

        exportResultsMenuItem = new JMenuItem("Export Simulation Results");
        exportResultsMenuItem.setMnemonic(KeyEvent.VK_G);
        exportResultsMenuItem.addActionListener(this);
        exportLogsMenuItem = new JMenuItem("Export Logs");
        exportLogsMenuItem.setMnemonic(KeyEvent.VK_G);
        exportLogsMenuItem.addActionListener(this);


        helpMenuItem = new JMenuItem("Help");
        helpMenuItem.setMnemonic(KeyEvent.VK_H);
        helpMenuItem.addActionListener(this);
        aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.setMnemonic(KeyEvent.VK_A);
        aboutMenuItem.addActionListener(this);

        fileMenu.add(openSimModelMenuItem);
        fileMenu.add(openSimConfigMenuItem);
        fileMenu.add(openSimScenarioMenuItem);
        fileMenu.add(new JSeparator());
        fileMenu.add(exitMenuItem);

        simulationMenu.add(configSimulationMenuItem);
        simulationMenu.add(new JSeparator());
        simulationMenu.add(startSimulationMenuItem);
        simulationMenu.add(stopSimulationMenuItem);

        resultsMenu.add(exportResultsMenuItem);
        resultsMenu.add(exportLogsMenuItem);

        helpMenu.add(helpMenuItem);
        helpMenu.add(aboutMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(simulationMenu);
        menuBar.add(resultsMenu);
        menuBar.add(helpMenu);
    }




    private void initTabbedPanes() {
        simEngineTabPane = new SimEngineTabPane();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object sourceObj = e.getSource();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        // Menu items
        if (sourceObj == openSimModelMenuItem){
            System.out.println("[" + timestamp + "] OpenSimModel MenuItem is selected.");
        } else if (sourceObj == openSimConfigMenuItem){
            System.out.println("[" + timestamp + "] OpenSimConfig MenuItem is selected.");
        } else if (sourceObj == openSimScenarioMenuItem){
            System.out.println("[" + timestamp + "] OpenSimScenario MenuItem is selected.");
        } else if (sourceObj == exitMenuItem){
            System.out.println("[" + timestamp + "] ExitMenuItem is selected.");
            timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println("[" + timestamp + "] Simulation engine is terminated.");
            System.exit(0);
        } else if (sourceObj == configSimulationMenuItem){
            System.out.println("[" + timestamp + "] ConfigSim MenuItem is selected.");
        } else if (sourceObj == startSimulationMenuItem){
            System.out.println("[" + timestamp + "] StartSim MenuItem is selected.");
        } else if (sourceObj == stopSimulationMenuItem){
            System.out.println("[" + timestamp + "] StopSim MenuItem is selected.");
        } else if (sourceObj == exportResultsMenuItem){
            System.out.println("[" + timestamp + "] ExportResults MenuItem is selected.");
        } else if (sourceObj == exportLogsMenuItem){
            System.out.println("[" + timestamp + "] ExportLogs MenuItem is selected.");
        } else if (sourceObj == helpMenuItem){
            System.out.println("[" + timestamp + "] Help MenuItem is selected.");
        } else if (sourceObj == aboutMenuItem){
            System.out.println("[" + timestamp + "] About MenuItem is selected.");
        }
    }






    private class TimerThread extends Thread{

        protected boolean isRunning;

        protected JLabel simDateLabel;
        protected JLabel simTimeLabel;

        protected SimpleDateFormat dateFormat =
                new SimpleDateFormat("MMM dd (EEE) yyyy");
        protected SimpleDateFormat timeFormat =
                new SimpleDateFormat("h:mm a");

        public TimerThread(JLabel simDateLabel, JLabel simTimeLabel) {
            this.simDateLabel = simDateLabel;
            this.simTimeLabel = simTimeLabel;
            this.isRunning = true;
        }

        @Override
        public void run() {
            while (isRunning){
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        Calendar currentCalendar = Calendar.getInstance();
                        Date currentTime = currentCalendar.getTime();
                        simDateLabel.setText(dateFormat.format(currentTime));
                        simTimeLabel.setText(timeFormat.format(currentTime));
                    }
                });

                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //super.run();
        }

        public void setRunning(boolean isRunning) {
            this.isRunning = isRunning;
        }
    }
}
