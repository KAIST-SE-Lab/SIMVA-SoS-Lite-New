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

    //TODO: check return
    ArrayList<SimLogEvent> doAction(_SimAction_ actionObj, int tick);
}
