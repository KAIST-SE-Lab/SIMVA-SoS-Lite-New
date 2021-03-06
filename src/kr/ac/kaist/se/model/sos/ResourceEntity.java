package kr.ac.kaist.se.model.sos;

import kr.ac.kaist.se.model.abst.obj._SimNonActionableObject_;
import kr.ac.kaist.se.model.abst.state._SimState_;
import kr.ac.kaist.se.model.intf.Movable;
import kr.ac.kaist.se.model.intf.Stateful;
import kr.ac.kaist.se.simdata.evnt.SimLogEvent;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Abstract class to represent a resource entity of an infrastructure.
 * Basically, a resource entity is non-actionable, but stateful and movable.
 *
 * @author ymbaek
 */
public abstract class ResourceEntity extends _SimNonActionableObject_
        implements Stateful, Movable {

    //SoS that this object belongs to
    protected SoS mySoS;
    //Infrastructure that this object belongs to
    protected Infrastructure myInfra;

    //TODO: Resource-based properties (e.g., Capacity of resource)

    public ResourceEntity(SoS mySoS, Infrastructure myInfra, String resId, String resName) {
        this.mySoS = mySoS;
        this.myInfra = myInfra;

        this.id = resId;
        this.name = resName;

        //If the default state need to be different, modify codes below.
        this.isStatic = true;
        this.isActivated = true;
        this.isAvailable = true;

        //A resource is a stateful object
        this.isStateful = true;
    }

    public ResourceEntity(SoS mySoS, Infrastructure myInfra, String resId, String resName, boolean isStatic, boolean isActivated, boolean isAvailable) {
        this.mySoS = mySoS;
        this.myInfra = myInfra;

        this.id = resId;
        this.name = resName;

        this.isStatic = isStatic;
        this.isActivated = isActivated;
        this.isAvailable = isAvailable;

        //A resource is a stateful object
        this.isStateful = true;
    }

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
