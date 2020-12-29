package kr.ac.kaist.se.model.sos;

import kr.ac.kaist.se.model.abst.cap._SimAction_;
import kr.ac.kaist.se.model.abst.obj._SimActionableObject_;
import kr.ac.kaist.se.model.abst.state._SimState_;
import kr.ac.kaist.se.model.intf.Communicatable;
import kr.ac.kaist.se.model.intf.Movable;
import kr.ac.kaist.se.model.intf.Stateful;
import kr.ac.kaist.se.model.sos.cap.CommAction;
import kr.ac.kaist.se.model.sos.cap.FuncAction;
import kr.ac.kaist.se.model.sos.cap.MoveAction;
import kr.ac.kaist.se.simdata.evnt.SimLogEvent;
import kr.ac.kaist.se.simdata.output.intermediate.RunResult;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Abstract class to represent an active environment(-al) element.
 * An environment element can be either actionable or non-actionable,
 * depending on whether it contains capable actions in capableActionList.
 *
 * Interfaces: Simulatable, Actionable, Stateful, Communicatable, Movable
 *
 * @author ymbaek
 */
public abstract class EnvElmtOfActiveEntity extends _SimActionableObject_
        implements Stateful, Communicatable, Movable {

    //SoS that this object belongs to
    protected SoS mySoS;
    //Environment that this object belongs to
    protected Environment myEnv;

    @Override
    public RunResult run() {

        //TODO: duplicate code (Constituent, SystemEntity, ...)
        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (" + this.getClass().getSimpleName() + "(" + id + " of " + myEnv.getId() + "):run) size of capableActions:" +
                capableActionList.size() + " = " + capableActionList);

        // [Communicatable] Before selecting actions, read a message from its message queue
        readIncomingMsgs();

        //Clear existing selectedActionList
        clearSelectedActionList();

        //Select actions to execute
        selectActions();


        RunResult runResult = new RunResult(this, this.selectedActionList);
//        System.out.println("[" + timestamp + "]  ----------------------------");


        return runResult;
    }

    @Override
    public void readIncomingMsgs() {
        //TODO: read incoming messages
    }

    @Override
    protected void selectActions() {
        //TODO: select actions
        //TODO: duplicate code
        ArrayList<_SimAction_> possibleMoveActions = new ArrayList<>();

        for (_SimAction_ aAction : capableActionList) {
            //If aAction is not a MoveAction
            if (aAction instanceof FuncAction) {
                if (aAction.checkPrecondition()) {
                    selectedActionList.add(aAction);
                }
            }
            //If aAction is MoveAction
            else if (aAction instanceof MoveAction) {
                if (aAction.checkPrecondition()) {
                    possibleMoveActions.add(aAction);

                }
            } else if (aAction instanceof CommAction) {
                //Dynamically set a message based on makeMsgForCommAction() method.
                //The makeMsgForCommAction() method should be implemented in a concrete Constituent class.
                ((CommAction) aAction).setMessage(makeMsgForCommAction((CommAction) aAction));

                //TODO: Selection mechanism for CommAction (now: select all communication actions)
                if (aAction.checkPrecondition()) {
                    selectedActionList.add(aAction);
                }
            }
        }


        /* Selection of move action (current: random) */
        if (selectMoveActions(possibleMoveActions) != null) {
            selectedActionList.addAll(selectMoveActions(possibleMoveActions));
        }


        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (Constituent:" + this.getClass().getSimpleName() + ":post-doDecisionMaking) capableActionList(" +
                capableActionList.size() + "), selectedActionList(" + selectedActionList.size() + ") = " + selectedActionList);
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
