import kr.ac.kaist.se.controller.sim.SimEngine;
import kr.ac.kaist.se.simdata.input.SimConfiguration;
import kr.ac.kaist.se.simdata.input.SimScenario;
import kr.ac.kaist.se.view.MainUI;

import javax.swing.*;
import java.sql.Timestamp;

public class Main {

    /**
     * @param args
     * args[0]: isGuiMode (0/others)
     * args[1]: isOnMape (0/others)
     * args[2]: is
     */
    public static void main(String[] args) {

        Timestamp timestamp;

        //A user can select a mode for launching SimEngine
        //Non-GUI Mode
        if (args[0].equals("0")){
            timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println("[" + timestamp + "] (Main) Simulation engine is launched (Non-GUI Mode).");

            /* Input SimModel */
            ToySoS toySoS = new ToySoS();

            /* Input SimConfiguration */
            //TODO: remove this section after debugging
            SimConfiguration exampleConfig = new SimConfiguration();
            exampleConfig.setSimTotalTime(100);

            /* Input SimScenario */
            //TODO: remove this section after debugging
            SimScenario exampleScenario = new SimScenario("Scenario01", false);

            /*
            //INSTANT (50, 70, 30, -1)
            SimScenarioEvent event01 = new SimScenarioEvent("event01",
                    "event01",
                    EnumEventType.VALUE_UPDATE,
                    exampleConfig.getSimTotalTime(),
                    null,
                    null,
                    EnumEventTargetScope.SINGLE_OBJECT,
                    EnumEventPredefBehavior.NOT_DETERMINED,
                    EnumEventOccPattern.INSTANT,
                    50, 70,
                    30,
                    -1,
                    false,
                    EnumEventProbDist.NOT_PROBABILISTIC,
                    null);

            //CONSTANT (60, 90, -1, -1)
            SimScenarioEvent event02 = new SimScenarioEvent("event02",
                    "event02",
                    EnumEventType.VALUE_UPDATE,
                    exampleConfig.getSimTotalTime(),
                    null,
                    null,
                    EnumEventTargetScope.SINGLE_OBJECT,
                    EnumEventPredefBehavior.NOT_DETERMINED,
                    EnumEventOccPattern.CONSTANT,
                    60, 90,
                    -1,
                    -1,
                    false,
                    EnumEventProbDist.NOT_PROBABILISTIC,
                    null);

            //PERIODIC (78, 89, 3, 5)
            SimScenarioEvent event03 = new SimScenarioEvent("event03",
                    "event03",
                    EnumEventType.VALUE_UPDATE,
                    exampleConfig.getSimTotalTime(),
                    null,
                    null,
                    EnumEventTargetScope.SINGLE_OBJECT,
                    EnumEventPredefBehavior.NOT_DETERMINED,
                    EnumEventOccPattern.PERIODIC,
                    78,89,
                    3,
                    5,
                    false,
                    EnumEventProbDist.NOT_PROBABILISTIC,
                    null);

            exampleScenario.addSimScenarioEvent(event01);
            exampleScenario.addSimScenarioEvent(event02);
            exampleScenario.addSimScenarioEvent(event03);

             */

            /* Declaration and initialization of SimEngine */
            SimEngine simEngine = new SimEngine(toySoS, args[1], exampleConfig, exampleScenario);

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
     * This method instantiates MainUI (simInputUI),
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
        //MainUI simInputUI = new MainUI();
        SwingUtilities.invokeLater(new MainUI());
    }



}
