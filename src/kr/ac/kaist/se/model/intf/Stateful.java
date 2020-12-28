package kr.ac.kaist.se.model.intf;

import kr.ac.kaist.se.simdata.evnt.SimLogEvent;

import java.util.ArrayList;

/**
 * Interface for objects that have states (i.e., have a state-machine)
 * Stateful objects can change their states.
 *
 * @author ymbaek
 */
public interface Stateful {

    /**
     * A method to change state of a stateful SimObject
     * @param newStateId  name of new state, which should be stored in objStates of _SimObject_
     * @return  SimLogEvents of state transition results
     */
    ArrayList<SimLogEvent> doStateTransition(String newStateId, int tick);

}
