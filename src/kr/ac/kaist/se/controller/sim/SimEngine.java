package kr.ac.kaist.se.controller.sim;

import kr.ac.kaist.se.model.sos.SoS;
import kr.ac.kaist.se.simdata.input.SimConfiguration;
import kr.ac.kaist.se.simdata.input.SimScenario;

/**
 * Simulation engine for a simulation of a simulation model
 * Discrete-time event-based simulation
 *
 * @author ehcho, yjshin, ymbaek
 */
public class SimEngine {

    protected SoS simModel;
    protected SimConfiguration simConfig;
    protected SimScenario simScenario;


    public SimEngine(SoS simModel) {
        this.simModel = simModel;
    }


    public SimEngine(SoS simModel, SimConfiguration simConfig, SimScenario simScenario) {
        this.simModel = simModel;
        this.simConfig = simConfig;
        this.simScenario = simScenario;
    }

    private void initSimEngine(){

    }
}
