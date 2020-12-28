import kr.ac.kaist.se.controller.sim.SimEngine;
import kr.ac.kaist.se.model.sos.SoS;
import kr.ac.kaist.se.simdata.input.SimConfiguration;
import kr.ac.kaist.se.simdata.input.SimScenario;
import kr.ac.kaist.se.view.MainUI;

import javax.swing.*;
import java.sql.Timestamp;

public class Execution {

    /**
     * @param args args[0]: isGuiMode (0/others)
     *             args[1]: isOnMape (0/others)
     *             args[2]: is
     */
    public static void main(String[] args, SoS sos, SimConfiguration simConfiguration, SimScenario simScenario) {

        System.out.println("2020-12-18 18:03");

        Timestamp timestamp;

        //A user can select a mode for launching SimEngine
        //Non-GUI Mode
        if (args[0].equals("0")) {

            timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println("[" + timestamp + "] (Main) Simulation engine is launched (Non-GUI Mode).");


            /* Declaration and initialization of SimEngine */
            //args[1]: isMapeOn
            SimEngine simEngine = new SimEngine(sos, args[1], simConfiguration, simScenario);
            simEngine.startSimulation();

        }
        //GUI Mode
        else {
            timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println("[" + timestamp + "] (Main) Simulation engine is launched (GUI Mode).");

            // Launch a GUI for taking input files from a user
            launchMainUI(sos, args[1], simConfiguration, simScenario);

            //simEngine.startSimulation();
        }

//        timestamp = new Timestamp(System.currentTimeMillis());
//        System.out.println("[" + timestamp + "] (Main) Simulation engine is terminated.");

    }


    /**
     * Method to launch a GUI for taking input files from a user.
     * This method instantiates MainUI (simInputUI),
     * which has menus/buttons for choosing input files.
     */
    private static void launchMainUI(SoS simModel, String isMapeOn, SimConfiguration simConfig, SimScenario simScenario) {
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
        //MainUI simInputUI = new MainUI();
        SwingUtilities.invokeLater(new MainUI(simModel, isMapeOn, simConfig, simScenario));
    }

}
