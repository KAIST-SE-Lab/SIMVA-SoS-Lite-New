package kr.ac.kaist.se.model.intf;

import kr.ac.kaist.se.model.abst.cap._SimAction_;
import kr.ac.kaist.se.simdata.evnt.SimLogEvent;

import java.util.ArrayList;

/**
 * Interface for actionable objects.
 * Actionable objects can execute doActions(..) method.
 *
 * @author ymbaek
 */
public interface Actionable {

    /**
     * A method to do an action(s)
     * @param actionObj An actionable object who execute the action(s)
     * @param tick Simulation tick
     * @return SimLogEvents of action execution results
     */
    ArrayList<SimLogEvent> doAction(_SimAction_ actionObj, int tick);
}
