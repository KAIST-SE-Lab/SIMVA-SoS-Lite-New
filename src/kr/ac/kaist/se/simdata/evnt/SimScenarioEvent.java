package kr.ac.kaist.se.simdata.evnt;

import kr.ac.kaist.se.model.abst.evnt._SimEvent_;

/**
 * An event included in a simulation scenario (SimScenario)
 * SimScenarioEvent should contain:
 *      (i) target information (who will be affected)
 *      (ii) behavioral information (what to execute)
 *      (iii) temporal information (when to execute)
 *      (iv) probabilistic information (what probability)
 */
public class SimScenarioEvent extends _SimEvent_ {

    public void executeEvent(){

    }

}
