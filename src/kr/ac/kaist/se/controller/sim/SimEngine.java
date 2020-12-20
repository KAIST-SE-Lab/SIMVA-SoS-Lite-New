package kr.ac.kaist.se.controller.sim;

import kr.ac.kaist.se.controller.mape.MapeEngine;
import kr.ac.kaist.se.model.abst.cap._SimAction_;
import kr.ac.kaist.se.model.sos.SoS;
import kr.ac.kaist.se.model.sos.cap.CommAction;
import kr.ac.kaist.se.simdata.evnt.SimLogEvent;
import kr.ac.kaist.se.simdata.input.SimConfiguration;
import kr.ac.kaist.se.simdata.input.SimScenario;
import kr.ac.kaist.se.simdata.output.SimLog;
import kr.ac.kaist.se.simdata.output.intermediate.RunResult;
import kr.ac.kaist.se.simdata.output.intermediate.UpdateResult;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Simulation engine for a simulation of a simulation model
 * Discrete-time evnt-based simulation
 *
 * @author ehcho, yjshin, ymbaek
 * @inputs Simulation Model (simModel)
 * MapeMode (isMapeOn)
 * Simulation Configuration (simConfig)
 * Simulation Scenario (simScenario)
 */
public class SimEngine {

    /* Logger for SimEngine */
    Logger logger = Logger.getLogger("Simulation Engine Logger");
    private static final String format = "[%1$tF %1$tT] [%2$-7s] %3$s %n";
    FileHandler fileHandler;

    /* Record of SimLogEvents */
    BufferedWriter outputWriter = null;
    String lineSeparator = System.getProperty("line.separator");
    File logFile = new File("SimModelLog.log");


    Timestamp timestamp;
    int simTick = 0;

//    private int cur_tick = 0;

    protected SoS simModel;
    protected SimConfiguration simConfig = new SimConfiguration();
    protected SimScenario simScenario = new SimScenario();
    protected boolean isMapeOn;


    protected MapeEngine mapeEngine;


//    public SimEngine(SoS simModel, String isMapeOn) {
//
//        timestamp = new Timestamp(System.currentTimeMillis());
//        System.out.println("[" + timestamp + "] (SimEngine) SimEngine is constructed.");
//
//
//        // Initialize simulation model
//        initSimModel(simModel);
//    }


    public SimEngine(SoS simModel, String isMapeOn, SimConfiguration simConfig, SimScenario simScenario) {


//        System.setProperty("java.util.logging.SimpleFormatter.format",
//                "%1$tF %1$tT %4$s %2$s %5$s%6$s%n");

        System.setProperty("java.util.logging.SimpleFormatter.format",
                format);

        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (SimEngine) SimEngine is constructed.");

        try {
            fileHandler = new FileHandler("SimEngineLog.log");
            logger.addHandler(fileHandler);

            SimpleFormatter formatter = new SimpleFormatter();
//            fileHandler.setFormatter(formatter);
            fileHandler.setFormatter(new SimpleFormatter(){
                @Override
                public synchronized String format(LogRecord lr) {
                    return String.format(format,
                            new Date(lr.getMillis()),
                            lr.getLevel().getLocalizedName(),
                            lr.getMessage()
                            );
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }



        try {

            if (!logFile.exists()){
//                System.out.println("no file");
            }else{
                timestamp = new Timestamp(System.currentTimeMillis());

                outputWriter = new BufferedWriter(new FileWriter(logFile));
                outputWriter.write("SimEngine constructed at" + timestamp + lineSeparator);
                outputWriter.write("> SimModel: " + simModel + lineSeparator);
                outputWriter.write("> isMapeOn: " + isMapeOn + lineSeparator);
                outputWriter.write("> simConfig: " + simConfig + lineSeparator);
                outputWriter.write("> simScenario: " + simScenario + lineSeparator);
                outputWriter.write("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + lineSeparator);
                outputWriter.flush();
//                System.out.println("have file");
            }



        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info("(pre-simulation) A SimEngine object is constructed and initialized.");
        initSimEngine(simModel, isMapeOn, simConfig, simScenario);
        logger.info("(pre-simulation) SimulationEngine is initialized: " + simModel + ", " + isMapeOn + ", " + simConfig + ", " + simScenario);

    }

    /**
     * Initialization of Simulation Engine
     *
     * @param simModel    Simulation model (SoS)
     * @param isMapeOn    "0":mape-off, "1"/others:mape-on
     * @param simConfig   Simulation configuration object
     * @param simScenario Simulation scenario object
     */
    private void initSimEngine(SoS simModel, String isMapeOn, SimConfiguration simConfig, SimScenario simScenario) {
        if (simModel != null) {
            // Initialize simulation model
            initSimModel(simModel);
        } else {
            timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println("[" + timestamp + "] (SimEngine:initSimEngine) simModel: null");
        }
        if (simConfig != null) {
            // Initialize simulation configuration
            initSimConfig(simConfig);
        } else {
            timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println("[" + timestamp + "] (SimEngine:initSimEngine) simConfig: null");
        }
        if (simScenario != null) {
            // Initialize simulation scenario
            initSimScenario(simScenario);
        } else {
            timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println("[" + timestamp + "] (SimEngine:initSimEngine) simScenario: null");
        }
    }

    /**
     * Initialization of a simulation model
     */
    private void initSimModel(SoS simModel) {
        this.simModel = simModel;

        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (SimEngine:initSimModel) SimModel is initialized (" + this.simModel + ")");
    }

    /**
     * Initialization of simulation configuration
     */
    private void initSimConfig(SimConfiguration simConfig) {
        this.simConfig = simConfig;

        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (SimEngine:initSimConfig) SimConfiguration is initialized(" + this.simConfig + ")");
    }

    /**
     * Initialization of simulation scenario
     */
    private void initSimScenario(SimScenario simScenario) {
        this.simScenario = simScenario;

        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (SimEngine:initSimScenario) SimScenario is initialized(" + this.simScenario + ")");
    }

    /**
     * Method to start a simulation of simModel.
     * (Deprecated: executeSimulation(...))
     *
     * @return SimLog
     */
    public SimLog startSimulation() {

        SimLog simLog = new SimLog();

        printSimInputInfo();

        logger.info("(pre-simulation) ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println();
        System.out.println("[" + timestamp + "] ===================================================================");
        System.out.println("[" + timestamp + "] (SimEngine:startSimulation) Simulation is started.");

        for (int cur_tick = 0; cur_tick < this.simConfig.getSimTotalTime(); cur_tick++) {

            simTick = cur_tick;

            System.out.println();
            timestamp = new Timestamp(System.currentTimeMillis());
//            System.out.println("[" + timestamp + "] ===================================================================");
            System.out.println("[" + timestamp + "] ┌──────────────────────────────────────────────────────────────────┐");
            System.out.println("[" + timestamp + "] (SimEngine:startSimulation) cur_tick: " + cur_tick);
            System.out.println("[" + timestamp + "] └──────────────────────────────────────────────────────────────────┘");

            /* PHASE 01: Collecting RunResults from SimModel*/

            RunResult curTickSimResult = runSimModel();
            logger.info("(cur_tick:" + simTick + ") RunResult of current tick is returned: # of selectedActions(" + getSelectedActionsFromRunResult(curTickSimResult).size() + ")");

            timestamp = new Timestamp(System.currentTimeMillis());
//            System.out.println("[" + timestamp + "] ===================================================================");
            System.out.println();
            System.out.println("[" + timestamp + "] ┌──────────────────────────────────────────────────────────────────┐");
            System.out.println("[" + timestamp + "] (SimEngine:startSimulation) RunResult is returned: (getSelectedActionList().size():" +
                    curTickSimResult.getSelectedActionList().size() + ") | (getSubRunREsults().size():" +
                    curTickSimResult.getSubRunResults().size() + ") | (total#ofActions:" +
                    getSelectedActionsFromRunResult(curTickSimResult).size() + ")");
            System.out.println("[" + timestamp + "] └──────────────────────────────────────────────────────────────────┘");

            /* PHASE 02: Resolving conflicts of the RunResult of the current tick */

            curTickSimResult = this.resolveConflict(curTickSimResult);
            logger.info("(cur_tick:" + simTick + ") Conflicts are resolved: filtered # of selectedActions(" + getSelectedActionsFromRunResult(curTickSimResult).size() + ")");


            /* PHASE 03: Collecting CommActions to process message sending */

            ArrayList<CommAction> selectedCommActions = readCommActions(curTickSimResult);
            logger.info("(cur_tick:" + simTick + ") Communication actions are read: # of commActions(" + selectedCommActions.size() + ")");

            timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println("[" + timestamp + "] (" + this.getClass().getSimpleName() + ":readCommActions) selectedCommActions.size(): " + selectedCommActions.size());



            System.out.println("[" + timestamp + "] (SimEngine:startSimulation) selectedActions of curTickSimResult: " + getSelectedActionsFromRunResult(curTickSimResult));


            /* PHASE 04: Update SimModel by actually executing the actions, allowed by this SimEngine */

            UpdateResult curTickUpdateResult = this.updateSimModel(curTickSimResult, cur_tick);
            logger.info("(cur_tick:" + simTick + ") RunResult of current tick is returned: # of logs(" + curTickUpdateResult.getLogEventList().size() + ")");

            System.out.println("[" + timestamp + "] ┌──────────────────────────────────────────────────────────────────┐");
            System.out.println("[" + timestamp + "] (SimEngine:startSimulation) size of log: " + curTickUpdateResult.getLogEventList().size());
            System.out.println("[" + timestamp + "] └──────────────────────────────────────────────────────────────────┘");

            for (SimLogEvent aLogEvent: curTickUpdateResult.getLogEventList()){

                try {
                    outputWriter.write(aLogEvent.getStringLogEvent() + lineSeparator);
                    outputWriter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


//            logger.info("(cur_tick:" + cur_tick + ") Info");
//            logger.warning("(cur_tick:" + cur_tick + ") Warning");

            /* PHASE 05: MAPE */

            /*
            //MAPE-loop is off
            if (isMapeOn.equals("0")){
                timestamp = new Timestamp(System.currentTimeMillis());
                System.out.println("[" + timestamp + "] (SimEngine:StartSimulation) MAPE Engine: OFF.");
            }
            //MAPE-loop is on
            else{
                timestamp = new Timestamp(System.currentTimeMillis());
                System.out.println("[" + timestamp + "] (SimEngine:StartSimulation) MAPE Engine: ON.");
            }
             */

            logger.info("(cur_tick:" + simTick + ") ===================================================================");

        }

        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "]  ===================================================================");
        System.out.println("[" + timestamp + "] (Main) Simulation engine is terminated.");

        logger.info("(post-simulation) ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        logger.info("(post-simulation) Simulation engine is terminated.");


        //Close of outputWriter
        try {
            outputWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return simLog;

    }

    private void printIncludedActions(ArrayList<? extends _SimAction_> actionList) {
        int index = 0;

        System.out.print("printIncludedActions(): ");

        for (_SimAction_ aAction : actionList) {
            if (index + 1 < actionList.size()) {
                System.out.print(aAction.getActionId() + "(" + aAction.getActionSubject().getId() + "), ");
            } else {
                System.out.print(aAction.getActionId() + "(" + aAction.getActionSubject().getId() + ")");
            }

            index++;
        }
        System.out.println();
    }

    /**
     * Read CommActions from RunResult
     *
     * @param curTickSimResult
     * @return
     */
    private ArrayList<CommAction> readCommActions(RunResult curTickSimResult) {
        ArrayList<CommAction> selectedCommActions = new ArrayList<>();
//        ArrayList<_SimAction_> selectedActions = getSelectedActionsFromRunResult(curTickSimResult);
        ArrayList<_SimAction_> selectedActions = curTickSimResult.getSelectedActionList();

        for (_SimAction_ aAction : selectedActions) {
            if (aAction instanceof CommAction) {
                if (aAction.checkPrecondition()) {
                    selectedCommActions.add((CommAction) aAction);
                    curTickSimResult.getSelectedActionList().remove(aAction);
//                System.out.println("***" + aAction.getActionId() + "(" + aAction.getActionSubject().getId() + ")");
                }
            }
        }

        for (RunResult subRunResult : curTickSimResult.getSubRunResults()) {
//            System.out.println("---");
            selectedCommActions.addAll(readCommActions(subRunResult));
        }



        return selectedCommActions;
    }

    /**
     * @param curTickSimResult
     * @return
     */
    private RunResult resolveConflict(RunResult curTickSimResult) {
        //TODO: To implement a logic to resolve a conflict
        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (" + this.getClass().getSimpleName() + ":resolveConflict)");

        return curTickSimResult;
    }

//    private void printAllRunResults(RunResult runResult){
//        for (_SimAction_ action : runResult.getSelectedActionList()){
//            System.out.print("(" + action.getActionSubject().getId() + ":" + action.getActionName() + ")|");
//        }
//        for (RunResult subRunResult: runResult.getSubRunResults()){
//            printAllRunResults(subRunResult);
//        }
//        //System.out.println();
//    }

    private ArrayList<_SimAction_> getSelectedActionsFromRunResult(RunResult runResult) {
        ArrayList<_SimAction_> actionsInRunResult = new ArrayList<>();

        actionsInRunResult.addAll(runResult.getSelectedActionList());

        for (RunResult subRunResult : runResult.getSubRunResults()) {
            actionsInRunResult.addAll(getSelectedActionsFromRunResult(subRunResult));
        }

        return actionsInRunResult;
    }

//    private ArrayList<SimLogEvent> getLogEventsFromUpdateResult(UpdateResult updateResult) {
//        ArrayList<SimLogEvent> logEventsInRunResult = new ArrayList<>();
//
//        logEventsInRunResult.addAll(updateResult.get());
//
//        for (RunResult subRunResult : updateResult.getSubRunResults()) {
//            logEventsInRunResult.addAll(getSelectedActionsFromRunResult(subRunResult));
//        }
//
//        return logEventsInRunResult;
//    }


    private void removeActionFromRunResult(RunResult runResult, _SimAction_ aAction) {


        for (RunResult subRunResult : runResult.getSubRunResults()) {
            if (subRunResult.getSelectedActionList().contains(aAction)) {
                subRunResult.getSelectedActionList().remove(aAction);
            }
        }

        for (_SimAction_ selectedAction : runResult.getSelectedActionList()) {
            if (selectedAction.getActionId().equals(aAction.getActionId())) {
                runResult.getSelectedActionList().remove(selectedAction);
            }
        }
    }


    /**
     * A method to collect selected actions from simModel by running a simulation model.
     *
     * @return
     */
    protected RunResult runSimModel() {
//        timestamp = new Timestamp(System.currentTimeMillis());
//        System.out.println("[" + timestamp + "] (" + this.getClass().getSimpleName() + ":runSimModel)");

        return simModel.run();
    }

    /**
     * A method to execute actions defined in the simulation model.
     * This method should be called after runSimModel() is called.
     *
     * @return
     */
    protected UpdateResult updateSimModel(RunResult curTickRunResult, int tick) {
//        timestamp = new Timestamp(System.currentTimeMillis());
//        System.out.println("[" + timestamp + "] (" + this.getClass().getSimpleName() + ":updateSimModel)");

        return simModel.update(curTickRunResult, tick);
    }


    private void printSimInputInfo() {
        timestamp = new Timestamp(System.currentTimeMillis());

        System.out.println("[" + timestamp + "] ┌─ Simulation Inputs ───────────────────────────────────────────────┐");
        System.out.println("[" + timestamp + "]  - Simulation Configuration ");
        System.out.println("[" + timestamp + "]    > [simTotalTime: " + simConfig.getSimTotalTime() + "]");
        System.out.println("[" + timestamp + "]    > [SimMapeMode: " + simConfig.isSimMapeMode() + "]");
        System.out.println("[" + timestamp + "]    > [SimHasScenario: " + simConfig.isSimHasScenario() + "]");
        System.out.println("[" + timestamp + "]  - Simulation Scenario ");
        System.out.println("[" + timestamp + "]    > [scenarioName: " + simScenario.getScenarioName() + "]");
        System.out.println("[" + timestamp + "]    > [numOfEvents (length): " + simScenario.getNumOfEvents() + "]");
        System.out.println("[" + timestamp + "]    > [numOfUnitEvents (length of unit events): " + simScenario.getNumOfUnitEvents() + "]");
        System.out.println("[" + timestamp + "] └───────────────────────────────────────────────────────────────────┘");

        logger.info("(pre-simulation) ┌─ Simulation Inputs ───────────────────────────────────────────────┐");
        logger.info("(pre-simulation)  - Simulation Configuration ");
        logger.info("(pre-simulation)    > [simTotalTime: " + simConfig.getSimTotalTime() + "]");
        logger.info("(pre-simulation)    > [SimMapeMode: " + simConfig.isSimMapeMode() + "]");
        logger.info("(pre-simulation)    > [SimHasScenario: " + simConfig.isSimHasScenario() + "]");
        logger.info("(pre-simulation)   - Simulation Scenario ");
        logger.info("(pre-simulation)    > [scenarioName: " + simScenario.getScenarioName() + "]");
        logger.info("(pre-simulation)    > [numOfEvents (length): " + simScenario.getNumOfEvents() + "]");
        logger.info("(pre-simulation)    > [numOfUnitEvents (length of unit events): " + simScenario.getNumOfUnitEvents() + "]");
        logger.info("(pre-simulation) └───────────────────────────────────────────────────────────────────┘");


    }



//
//    public int getCurTick(){
//        return cur_tick;
//    }


    public int getSimTick() {
        return simTick;
    }

    public void setSimTick(int simTick) {
        this.simTick = simTick;
    }

    public SoS getSimModel() {
        return simModel;
    }

    public void setSimModel(SoS simModel) {
        this.simModel = simModel;
    }

    public SimConfiguration getSimConfig() {
        return simConfig;
    }

    public void setSimConfig(SimConfiguration simConfig) {
        this.simConfig = simConfig;
    }

    public SimScenario getSimScenario() {
        return simScenario;
    }

    public void setSimScenario(SimScenario simScenario) {
        this.simScenario = simScenario;
    }

    public boolean isMapeOn() {
        return isMapeOn;
    }

    public void setMapeOn(boolean mapeOn) {
        isMapeOn = mapeOn;
    }

    public MapeEngine getMapeEngine() {
        return mapeEngine;
    }

    public void setMapeEngine(MapeEngine mapeEngine) {
        this.mapeEngine = mapeEngine;
    }
}
