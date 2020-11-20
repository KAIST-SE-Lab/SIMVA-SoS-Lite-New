import gui.LaunchWindow;
import gui.SimEngineFrame;
import kr.ac.kaist.se.controller.sim.SimEngine;
import kr.ac.kaist.se.view.SimInputUI;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;

public class Main {

    public static Timestamp timestamp;

    /**
     * @param args
     * args[0]: isGuiMode (0/others)
     * args[1]: isOnMape (0/others)
     * args[2]: is
     */
    public static void main(String[] args) {


        //A user can select a mode for launching SimEngine
        //Non-GUI Mode
        if (args[0].equals("0")){
            timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println("[" + timestamp + "] (Main) Simulation engine is launched (Non-GUI Mode).");

            ToySoS toySoS = new ToySoS();
            SimEngine simEngine = new SimEngine(toySoS, args[1], null, null);

            simEngine.startSimulation();

        }
        //GUI Mode
        else{
            timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println("[" + timestamp + "] (Main) Simulation engine is launched (GUI Mode).");

            // Launch a GUI for taking input files from a user
            launchSimInputUI();
        }


        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (Main) Simulation engine is terminated.");


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



}
