package kr.ac.kaist.se.controller.sim;

import kr.ac.kaist.se.controller.mape.MapeEngine;
import kr.ac.kaist.se.model.abst.cap._SimAction_;
import kr.ac.kaist.se.model.sos.SoS;
import kr.ac.kaist.se.model.sos.cap.CommAction;
import kr.ac.kaist.se.simdata.input.SimConfiguration;
import kr.ac.kaist.se.simdata.input.SimScenario;
import kr.ac.kaist.se.simdata.output.SimLog;
import kr.ac.kaist.se.simdata.output.intermediate.RunResult;
import kr.ac.kaist.se.simdata.output.intermediate.UpdateResult;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Simulation engine for a simulation of a simulation model
 * Discrete-time evnt-based simulation
 *
 * @inputs  Simulation Model (simModel)
 *          MapeMode (isMapeOn)
 *          Simulation Configuration (simConfig)
 *          Simulation Scenario (simScenario)
 *
 * @author ehcho, yjshin, ymbaek
 */
public class SimEngine {

    Timestamp timestamp;

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

        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (SimEngine) SimEngine is constructed.");

        initSimEngine(simModel, isMapeOn, simConfig, simScenario);

    }

    /**
     * Initialization of Simulation Engine
     *
     * @param simModel      Simulation model (SoS)
     * @param isMapeOn      "0":mape-off, "1"/others:mape-on
     * @param simConfig     Simulation configuration object
     * @param simScenario   Simulation scenario object
     */
    private void initSimEngine(SoS simModel, String isMapeOn, SimConfiguration simConfig, SimScenario simScenario){
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
    private void initSimConfig(SimConfiguration simConfig){
        this.simConfig = simConfig;

        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (SimEngine:initSimConfig) SimConfiguration is initialized(" + this.simConfig + ")");
    }

    /**
     * Initialization of simulation scenario
     */
    private void initSimScenario(SimScenario simScenario){
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
    public SimLog startSimulation(){

        SimLog simLog = new SimLog();

        printSimInputInfo();

        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "]  ===================================================================");
        System.out.println("[" + timestamp + "] (SimEngine:startSimulation) Simulation is started.");

        for(int cur_tick = 0; cur_tick < this.simConfig.getSimTotalTime(); cur_tick++){
            timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println("[" + timestamp + "] ===================================================================");
            System.out.println("[" + timestamp + "] (SimEngine:startSimulation) cur_tick: " + cur_tick);

            /* PHASE 01: Collecting RunResults from SimModel*/

            RunResult curTickSimResult = runSimModel();

            timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println("[" + timestamp + "] ===================================================================");
            System.out.println("[" + timestamp + "] (SimEngine:startSimulation) RunResult is returned: (getSelectedActionList().size():" +
                    curTickSimResult.getSelectedActionList().size() + ") | (getSubRunREsults().size():" +
                    curTickSimResult.getSubRunResults().size() + ") | (total#ofActions:" +
                    getSelectedActionsFromRunResult(curTickSimResult).size() +")");

            /* PHASE 02: Resolving conflicts of the RunResult of the current tick */

            curTickSimResult = this.resolveConflict(curTickSimResult);


            /* PHASE 03: Collecting CommActions to process message sending */

            ArrayList<CommAction> selectedCommActions = readCommActions(curTickSimResult);



            System.out.println("[" + timestamp + "] (SimEngine:startSimulation) selectedActions of curTickSimResult: " + getSelectedActionsFromRunResult(curTickSimResult));


            /* PHASE 04: Update SimModel by actually executing the actions, allowed by this SimEngine */

            UpdateResult curTickUpdateResult = this.updateSimModel(curTickSimResult);


        }

        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "]  ===================================================================");
        System.out.println("[" + timestamp + "] (Main) Simulation engine is terminated.");

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


        return simLog;

    }

    private void printIncludedActions(ArrayList<? extends _SimAction_> actionList){
        int index = 0;

        System.out.print("printIncludedActions(): ");

        for (_SimAction_ aAction: actionList){
            if (index + 1 < actionList.size()){
                System.out.print(aAction.getActionId() + "(" + aAction.getActionSubject().getId()+"), ");
            }else {
                System.out.print(aAction.getActionId() + "(" + aAction.getActionSubject().getId() + ")");
            }

            index++;
        }
        System.out.println();
    }

    /**
     * Read CommActions from RunResult
     * @param curTickSimResult
     * @return
     */
    private ArrayList<CommAction> readCommActions(RunResult curTickSimResult) {
        ArrayList<CommAction> selectedCommActions = new ArrayList<>();
//        ArrayList<_SimAction_> selectedActions = getSelectedActionsFromRunResult(curTickSimResult);
        ArrayList<_SimAction_> selectedActions = curTickSimResult.getSelectedActionList();

        for (_SimAction_ aAction: selectedActions){
            if (aAction instanceof CommAction){
                if (aAction.checkPrecondition()) {
                    selectedCommActions.add((CommAction) aAction);
                    curTickSimResult.getSelectedActionList().remove(aAction);
//                System.out.println("***" + aAction.getActionId() + "(" + aAction.getActionSubject().getId() + ")");
                }
            }
        }

        for (RunResult subRunResult : curTickSimResult.getSubRunResults()){
//            System.out.println("---");
            selectedCommActions.addAll(readCommActions(subRunResult));
        }


        return selectedCommActions;
    }

    /**
     *
     * @param curTickSimResult
     * @return
     */
    private RunResult resolveConflict(RunResult curTickSimResult) {
        //TODO: To implement a logic to resolve a conflict
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

    private ArrayList<_SimAction_> getSelectedActionsFromRunResult(RunResult runResult){
        ArrayList<_SimAction_> actionsInRunResult = new ArrayList<>();

        actionsInRunResult.addAll(runResult.getSelectedActionList());

        for (RunResult subRunResult: runResult.getSubRunResults()){
            actionsInRunResult.addAll(getSelectedActionsFromRunResult(subRunResult));
        }

        return actionsInRunResult;
    }


    private void removeActionFromRunResult(RunResult runResult, _SimAction_ aAction){


        for (RunResult subRunResult: runResult.getSubRunResults()){
            if (subRunResult.getSelectedActionList().contains(aAction)){
                subRunResult.getSelectedActionList().remove(aAction);
            }
        }

        for (_SimAction_ selectedAction: runResult.getSelectedActionList()){
            if (selectedAction.getActionId().equals(aAction.getActionId())){
                runResult.getSelectedActionList().remove(selectedAction);
            }
        }
    }


    /**
     * A method to collect selected actions from simModel by running a simulation model.
     * @return
     */
    protected RunResult runSimModel(){
        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (" + this.getClass().getSimpleName() + ":runSimModel)");

        return simModel.run();
        //return null;
    }

    /**
     * A method to execute actions defined in the simulation model.
     * This method should be called after runSimModel() is called.
     * @return
     */
    protected UpdateResult updateSimModel(RunResult curTickRunResult){
        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (" + this.getClass().getSimpleName() + ":updateSimModel)");

        return simModel.update(curTickRunResult);
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

    }


}
