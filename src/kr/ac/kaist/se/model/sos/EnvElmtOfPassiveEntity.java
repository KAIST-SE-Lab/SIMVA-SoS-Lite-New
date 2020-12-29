package kr.ac.kaist.se.model.sos;

import kr.ac.kaist.se.model.abst.obj._SimNonActionableObject_;
import kr.ac.kaist.se.model.abst.state._SimState_;
import kr.ac.kaist.se.model.intf.Movable;
import kr.ac.kaist.se.model.intf.Stateful;
import kr.ac.kaist.se.simdata.evnt.SimLogEvent;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Abstract class to represent an active environment(-al) element.
 * An environment element can be either actionable or non-actionable,
 * depending on whether it contains capable actions in capableActionList.
 *
 * Interfaces: Simulatable, Actionable, Stateful, Movable
 *
 * @author ymbaek
 */
public abstract class EnvElmtOfPassiveEntity extends _SimNonActionableObject_
        implements Stateful, Movable {

    //SoS that this object belongs to
    protected SoS mySoS;
    //Environment that this object belongs to
    protected Environment myEnv;

    @Override
    public ArrayList<SimLogEvent> doStateTransition(String newStateId, int tick) {

        //TODO: Duplicate code
        boolean isExistState = false;
        for (_SimState_ state : objStates){
            if (newStateId.equals(state.getId())){
                isExistState = true;
            }
        }

        if (isExistState) {

            String previousStateId = curStateId;
            ArrayList<SimLogEvent> transitionLogEvents = new ArrayList<>();

            //If the new state is same as the current state (i.e., self-transition)
            if (newStateId.equals(curStateId)){
                //do nothing
            }else{
                curStateId = newStateId;
            }

            timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println("[" + timestamp + "] (Constituent:" + this.getClass().getSimpleName() + "(" + id + "):doStateTransition) A state transition (<" + previousStateId + "> -> <" + curStateId + ">) is executed.");

            //TODO: Make and add a SimLogEvent to transitionLogEvents
            /*
            new SimLogEvent(actionSubject.getLogEventIdAutomatically(this),
                    EnumEventType.LOCATION_CHANGE,
                    new Timestamp(System.currentTimeMillis()),
                    tick,
                    actionSubject.getId(),
                    actionSubject,
                    generateLogEventSpec())
             */


            return transitionLogEvents;
        } else {
            return null;
        }

    }
}
