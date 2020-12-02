package kr.ac.kaist.se.controller.sim;

import kr.ac.kaist.se.controller.mape.MapeEngine;
import kr.ac.kaist.se.model.sos.SoS;
import kr.ac.kaist.se.simdata.input.SimConfiguration;
import kr.ac.kaist.se.simdata.input.SimScenario;
import kr.ac.kaist.se.simdata.output.SimLog;
import kr.ac.kaist.se.simdata.output.intermediate.RunResult;

import java.sql.Timestamp;

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
        System.out.println("[" + timestamp + "] (SimEngine:initSimModel) SimModel is initialized.");
    }

    /**
     * Initialization of simulation configuration
     */
    private void initSimConfig(SimConfiguration simConfig){
        this.simConfig = simConfig;

        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (SimEngine:initSimConfig) SimConfiguration is initialized.");
    }

    /**
     * Initialization of simulation scenario
     */
    private void initSimScenario(SimScenario simScenario){
        this.simScenario = simScenario;

        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (SimEngine:initSimScenario) SimScenario is initialized.");
    }

    /**
     * Method to start a simulation of simModel.
     * (Deprecated: executeSimulation(...))
     */
    public SimLog startSimulation(){

        SimLog simLog = new SimLog();

        printSimInputInfo();

        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (SimEngine:startSimulation) Simulation is started.");

        for(int cur_tick = 0; cur_tick < this.simConfig.getSimTotalTime(); cur_tick++){
            timestamp = new Timestamp(System.currentTimeMillis());
            //System.out.println("[" + timestamp + "] (SimEngine:startSimulation) cur_tick: " + cur_tick);

            RunResult curTickSimResult = runSimModel();
        }

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

    protected RunResult runSimModel(){
//        return SoS.run();
        return null;
    }
}
