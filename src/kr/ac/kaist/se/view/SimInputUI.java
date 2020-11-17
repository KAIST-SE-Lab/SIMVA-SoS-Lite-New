package kr.ac.kaist.se.view;

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
    JMenu helpMenu;
    JMenuItem openSimModelMenuItem;
    JMenuItem openSimConfigMenuItem;
    JMenuItem openSimScenarioMenuItem;
    JMenuItem exitMenuItem;
    JMenuItem helpMenuItem;
    JMenuItem aboutMenuItem;


    JButton openSimModelButton;
    JButton openSimConfigButton;
    JButton openSimScenarioButton;

    SimStatusBarPanel statusBar;

    JLabel simStatusLabel;
    JLabel simDataLabel;
    JLabel simTimeLabel;

    public SimInputUI(){
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
        helpMenu = new JMenu("Help");

        openSimModelMenuItem = new JMenuItem("Open a Simulation Model");
        openSimModelMenuItem.setMnemonic(KeyEvent.VK_M);
        openSimModelMenuItem.addActionListener(this);
        openSimConfigMenuItem = new JMenuItem("Open a Simulation Configuration");
        openSimConfigMenuItem.setMnemonic(KeyEvent.VK_C);
        openSimConfigMenuItem.addActionListener(this);
        openSimScenarioMenuItem = new JMenuItem("Open a Simulation Scenario");
        openSimScenarioMenuItem.setMnemonic(KeyEvent.VK_S);
        openSimScenarioMenuItem.addActionListener(this);
        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setMnemonic(KeyEvent.VK_X);
        exitMenuItem.addActionListener(this);
        helpMenuItem = new JMenuItem("Help");
        helpMenuItem.setMnemonic(KeyEvent.VK_H);
        helpMenuItem.addActionListener(this);
        aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.setMnemonic(KeyEvent.VK_A);
        aboutMenuItem.addActionListener(this);

        fileMenu.add(openSimModelMenuItem);
        fileMenu.add(openSimConfigMenuItem);
        fileMenu.add(openSimScenarioMenuItem);
        fileMenu.add(exitMenuItem);

        helpMenu.add(helpMenuItem);
        helpMenu.add(aboutMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
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
        Object sourceObj = e.getSource();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        // Menu items
        if (sourceObj == openSimModelMenuItem || sourceObj == openSimModelButton){
            System.out.println("[" + timestamp + "] Open Sim Model MenuItem is selected.");
        } else if (sourceObj == openSimConfigMenuItem || sourceObj == openSimConfigButton){
            System.out.println("[" + timestamp + "] Open Sim Config MenuItem is selected.");
        } else if (sourceObj == openSimScenarioMenuItem || sourceObj == openSimScenarioButton){
            System.out.println("[" + timestamp + "] Open Sim Scenario MenuItem is selected.");
        } else if (sourceObj == exitMenuItem){
            System.out.println("[" + timestamp + "] Exit MenuItem is selected.");
            timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println("[" + timestamp + "] Simulation engine is terminated.");
            System.exit(0);
        } else if (sourceObj == helpMenuItem){
            System.out.println("[" + timestamp + "] Help MenuItem is selected.");
        } else if (sourceObj == aboutMenuItem){
            System.out.println("[" + timestamp + "] About MenuItem is selected.");
        }
    }

    @Override
    public void run() {
        simEngineIcon = new ImageIcon("logo.png");

        inputUIframe = new JFrame();
        inputUIframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inputUIframe.setTitle("Simulation Engine: Simulation Input");
        inputUIframe.setIconImage(simEngineIcon.getImage());
        inputUIframe.setSize(400, 200);
        inputUIframe.setLayout(new GridLayout(3,1));

        initMenu();
        initButtons();
        initStatusBar();

        /* Add components */
        inputUIframe.setJMenuBar(menuBar);

        //inputUIframe.add(openSimModelButton, "Center");
        //inputUIframe.add(openSimConfigButton, "Center");
        //inputUIframe.add(openSimScenarioButton, "Center");

        Container contentPane = inputUIframe.getContentPane();
        contentPane.setLayout(new BorderLayout());

        inputUIframe.add(statusBar, BorderLayout.SOUTH);

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

        inputUIframe.setVisible(true);
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
