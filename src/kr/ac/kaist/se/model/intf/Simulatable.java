package kr.ac.kaist.se.model.intf;

import kr.ac.kaist.se.model.sos.geo.ObjectLocation;
import kr.ac.kaist.se.simdata.output.intermediate.RunResult;
import kr.ac.kaist.se.simdata.output.intermediate.UpdateResult;

/**
 * Interface for simulatable objects.
 * Simulatable objects can execute run(..) and update(..) methods.
 *
 * @author ymbaek
 */
public interface Simulatable {

    /**
     * Every SimObject has its current location information,
     * thus curLocation can be obtained by calling this method
     *
     * @return current location of this simulatable object
     */
    ObjectLocation getCurLocation();

    /**
     * The method run() executes own behaviors, such as actions,
     * by selecting actions among capable actions (a set of actions, i.e., actionList).
     *
     * @return selected actions (result of the action selection)
     */
    RunResult run();


    /**
     * The method update() actually executes behaviors allowed by the SimEngine.
     * @param runResult runResult object (i.e., actions allowed to be executed) of current tick
     * @return  list of action execution results (i.e., logs)
     */
    UpdateResult update(RunResult runResult);
}
