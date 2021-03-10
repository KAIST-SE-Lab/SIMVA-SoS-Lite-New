package kr.ac.kaist.se.model.sos;

import kr.ac.kaist.se.model.abst.cap._SimAction_;
import kr.ac.kaist.se.model.abst.comm._SimMessage_;
import kr.ac.kaist.se.model.abst.obj._SimActionableObject_;
import kr.ac.kaist.se.model.abst.state._SimState_;
import kr.ac.kaist.se.model.intf.Communicatable;
import kr.ac.kaist.se.model.intf.DecisionMakeable;
import kr.ac.kaist.se.model.intf.Movable;
import kr.ac.kaist.se.model.intf.Stateful;
import kr.ac.kaist.se.model.sos.cap.CommAction;
import kr.ac.kaist.se.model.sos.cap.FuncAction;
import kr.ac.kaist.se.model.sos.cap.MoveAction;
import kr.ac.kaist.se.model.sos.data.DataVar;
import kr.ac.kaist.se.simdata.evnt.SimLogEvent;
import kr.ac.kaist.se.simdata.output.intermediate.RunResult;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Abstract class to represent a constituent system, called CS.
 * <p>
 * According to the M2SoS, a Constituent is one and only object that can belong to an organization.
 * A Constituent can communicate (i.e., Communicatable) and make its own decisions (i.e., DecisionMakeable).
 * <p>
 * Interfaces: Simulatable, Actionable, Stateful, Movable, Communicatable, DecisionMakeable
 *
 * @author ymbaek, ehcho, yjshin
 */
public abstract class Constituent extends _SimActionableObject_
        implements Stateful, Movable, Communicatable, DecisionMakeable {

    //SoS that this object belongs to
    protected SoS mySoS;
    //Organization that this object belongs to
    protected Organization myOrg;

    /**
     * Knowledge base of this constituent object
     */
    //Knowledge base of a single constituent
    protected ArrayList<DataVar> knowledgeBase;


    public Constituent(SoS simModel, Organization myOrg, String csId, String csName) {
        this.mySoS = simModel;
        this.myOrg = myOrg;

        this.id = csId;
        this.name = csName;

        //If the default state need to be different, modify codes below.
        this.isStatic = true;
        this.isActivated = true;
        this.isAvailable = true;

        //A constituent is a stateful object
        this.isStateful = true;

        msgQueue = new LinkedList<_SimMessage_>();

        initCapableActions();

        printObjInfo();
        //printCSInfo();
        initObjLocation();
    }

    public Constituent(SoS simModel, Organization myOrg, String csId, String csName, boolean isStatic, boolean isActivated, boolean isAvailable) {
        this.mySoS = simModel;
        this.myOrg = myOrg;

        this.id = csId;
        this.name = csName;

        //If the default state need to be different, modify codes below.
        this.isStatic = isStatic;
        this.isActivated = isActivated;
        this.isAvailable = isAvailable;

        //A constituent is a stateful object
        this.isStateful = true;

        msgQueue = new LinkedList<_SimMessage_>();

        initCapableActions();

        printObjInfo();
        //printCSInfo();
        initObjLocation();
    }


    @Override
    public RunResult run() {
        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (" + this.getClass().getSimpleName() + "(" + id + " of " + myOrg.getId() + "):run) size of capableActions:" +
                capableActionList.size() + " = " + capableActionList);

        // [Communicatable] Before selecting actions, read a message from its message queue
        readIncomingMsgs();

        //Clear existing selectedActionList
        clearSelectedActionList();

        //Select actions to execute
        selectActions();


        RunResult runResult = new RunResult(this, this.selectedActionList);
        System.out.println("[" + timestamp + "]  ----------------------------");


        return runResult;
    }


    /* Methods executed by run() */

    @Override
    public void readIncomingMsgs() {
        if (msgQueue.size() != 0) {

            timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println("[" + timestamp + "] (" + this.getClass().getSimpleName() + "(" + id + "):readIncomingMsgs) size of msgQueue:" +
                    msgQueue.size());

            int msgCnt = 0;
            ArrayList<DataVar> receivedDataList = new ArrayList<>();

            while (!msgQueue.isEmpty()) {
                //Objects.requireNonNull()
                receivedDataList.addAll(msgQueue.poll().getMsgDataList());
                msgCnt++;
            }

            //If there is a received message from the message queue
            if (msgCnt > 0) {
                timestamp = new Timestamp(System.currentTimeMillis());
                System.out.println("[" + timestamp + "] (" + this.getClass().getSimpleName() + "(" + id + "):readIncomingMsgs) " +
                        msgCnt + " messages are read (dataCnt:" + receivedDataList.size() + ")");

                addOrUpdateDataToKnowledgeBase(receivedDataList);
            } else {
                timestamp = new Timestamp(System.currentTimeMillis());
                System.out.println("[" + timestamp + "] (" + this.getClass().getSimpleName() + "(" + id + "):readIncomingMsgs) no message is read.");
            }
        }

    }

    /**
     * A method to add or update received msg data to CS's knowledge base
     *
     * @param dataList
     */
    private void addOrUpdateDataToKnowledgeBase(ArrayList<DataVar> dataList) {
        knowledgeBase.addAll(dataList);

        //TODO: update if it already exists
    }

    @Override
    public void doDecisionMaking() {


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
    protected void selectActions() {

        //Actions are selected based on its own decision making mechanism
        doDecisionMaking();

    }

    @Override
    public ArrayList<SimLogEvent> doAction(_SimAction_ actionObj, int tick) {
        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (Constituent:" + this.getClass().getSimpleName() + "(" + id + "):doAction) " + actionObj.getActionId() + " is executed.");

        ArrayList<SimLogEvent> actionLogEvents = new ArrayList<>();

        if (actionObj instanceof MoveAction) {
            actionLogEvents = actionObj.executeAction(tick);
            //ObjectLocation curLoc = getCurLocation();
        } else if (actionObj instanceof CommAction) {
            actionLogEvents = actionObj.executeAction(tick);
        } else if (actionObj instanceof FuncAction) {
            actionLogEvents = actionObj.executeAction(tick);
        }


        return actionLogEvents;
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


    /**
     * Initialization of object location
     */
    protected abstract void initObjLocation();


    /* Getters & Setters */


    public SoS getMySoS() {
        return mySoS;
    }

    public void setMySoS(SoS mySoS) {
        this.mySoS = mySoS;
    }

    public Organization getMyOrg() {
        return myOrg;
    }

    public void setMyOrg(Organization myOrg) {
        this.myOrg = myOrg;
    }
}
