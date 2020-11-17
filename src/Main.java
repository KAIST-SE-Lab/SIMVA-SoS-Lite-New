import gui.LaunchWindow;
import gui.SimEngineFrame;
import kr.ac.kaist.se.controller.sim.SimEngine;
import kr.ac.kaist.se.view.SimInputUI;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;

public class Main {

    public static void main(String[] args) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] Simulation engine is launched.");

        // Launch a GUI for taking input files from a user
        launchSimInputUI();

        initSimModel();
        ToySoS toySoS = new ToySoS();



        SimEngine simEngine = new SimEngine(toySoS);

        timestamp = new Timestamp(System.currentTimeMillis());
        //System.out.println("[" + timestamp + "] Simulation engine is terminated.");

//        try {
////            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
//            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//
//        } catch (UnsupportedLookAndFeelException e) {
//            System.err.println("Error Message: " + e);
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        LaunchWindow launchWindow = new LaunchWindow();

        //SimEngineFrame simEngineFrame = new SimEngineFrame();
        /*
        JFrame simEngineFrame = new JFrame();
        simEngineFrame.setTitle("Simulation Engine Frame");
        simEngineFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        simEngineFrame.setResizable(false);
        simEngineFrame.setSize(420, 420);
        simEngineFrame.setVisible(true);

        ImageIcon image = new ImageIcon("logo.png");
        simEngineFrame.setIconImage(image.getImage());

        simEngineFrame.getContentPane().setBackground(Color.DARK_GRAY);
        simEngineFrame.getContentPane().setBackground(new Color(0,0,0));

         */

    }

    /**
     * Method to launch a GUI for taking input files from a user.
     * This method instantiates SimInputUI (simInputUI),
     * which has menus/buttons for choosing input files.
     */
    private static void launchSimInputUI() {
        // Set Look and Feel using the UIManager for Swing Objects
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Error Message: " + e);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //SimInputUI simInputUI = new SimInputUI();
        SwingUtilities.invokeLater(new SimInputUI());
    }


    /**
     * Initialization of a simulation model
     */
    private static void initSimModel() {

    }


}
