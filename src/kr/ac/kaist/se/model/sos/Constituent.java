package kr.ac.kaist.se.model.sos;

import kr.ac.kaist.se.model.abst.cap._SimAction_;
import kr.ac.kaist.se.model.abst.comm._SimMessage_;
import kr.ac.kaist.se.model.abst.obj._SimActionableObject_;
import kr.ac.kaist.se.model.intf.Communicatable;
import kr.ac.kaist.se.model.intf.DecisionMakeable;
import kr.ac.kaist.se.model.intf.Movable;
import kr.ac.kaist.se.model.intf.Stateful;
import kr.ac.kaist.se.model.sos.cap.CommAction;
import kr.ac.kaist.se.model.sos.cap.FuncAction;
import kr.ac.kaist.se.model.sos.cap.MoveAction;
import kr.ac.kaist.se.model.sos.data.DataVar;
import kr.ac.kaist.se.model.sos.data.DimensionVar;
import kr.ac.kaist.se.model.sos.geo.ObjectLocation;
import kr.ac.kaist.se.simdata.output.intermediate.RunResult;

import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.util.*;

/**
 * Abstract class to represent a constituent system, called CS.
 *
 * According to the M2SoS, a Constituent is one and only object that can belong to an organization.
 * A Constituent can communicate (i.e., Communicatable) and make its own decisions (i.e., DecisionMakeable).
 *
 * Interfaces: Simulatable, Actionable, Stateful, Movable, Communicatable, DecisionMakeable
 *
 * @author ymbaek, ehcho, yjshin
 */
public abstract class Constituent extends _SimActionableObject_
        implements Stateful, Movable, Communicatable, DecisionMakeable {


    protected SoS mySoS;                //SoS that this object belongs to
    protected Organization myOrg;       //Organization that this object belongs to

//    protected ArrayList<MoveAction> capableMoveActionList;

    protected ArrayList<DataVar> knowledgeBase; //Knowledge base of a single constituent

    //TODO: knowledge base
    //TODO: decision making

    public Constituent(SoS simModel, Organization myOrg, String csId, String csName){
        this.mySoS = simModel;
        this.myOrg = myOrg;

        this.id = csId;
        this.name = csName;

        //If the default state need to be different, modify codes below.
        this.isStatic = true;
        this.isActivated = true;
        this.isAvailable = true;

        msgQueue = new LinkedList<_SimMessage_>();

        initActions();

        printObjInfo();
        //printCSInfo();
        initObjLocation();
    }

    public Constituent(SoS simModel, Organization myOrg, String csId, String csName, boolean isStatic, boolean isActivated, boolean isAvailable){
        this.mySoS = simModel;
        this.myOrg = myOrg;

        this.id = csId;
        this.name = csName;

        //If the default state need to be different, modify codes below.
        this.isStatic = isStatic;
        this.isActivated = isActivated;
        this.isAvailable = isAvailable;

        msgQueue = new LinkedList<_SimMessage_>();

        initActions();

        printObjInfo();
        //printCSInfo();
        initObjLocation();
    }


    @Override
    public RunResult run() {
        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (" + this.getClass().getSimpleName() + "(" + id + " of " + myOrg.getId() + "):run) size of capableActions:" +
                capableActionList.size() + " = " + capableActionList);
//        System.out.println("[" + timestamp + "] CapableActionList: " + capableActionList);

        //Before selecting actions, read a message from its message queue
        readIncomingMsgs();

        //TODO: Check clearSelectedActionList()
        //Clear existing selectedActionList
        clearSelectedActionList();

        //Select actions to execute
        selectActions();
        //Perform decision making to select actions
        //doDecisionMaking();


//        //RunResult for this CS's action execution
//        RunResult runResult = new RunResult(this, new ArrayList<>(0));
//        runResult.setSelectedActionList(this.selectedActionList);

//        timestamp = new Timestamp(System.currentTimeMillis());
//        System.out.println("[" + timestamp + "] (" + this.getClass().getSimpleName() + "(" + id + " of " + myOrg.getId() + "):run) size of selectedActionList:" +
//                selectedActionList.size() + "(" + selectedActionList + ")");
        System.out.println("[" + timestamp + "]  ----------------------------");

        RunResult runResult = new RunResult(this, this.selectedActionList);
//        System.out.println(runResult.getRunSubject() + " | " + runResult.getSelectedActionList());

        return runResult;
    }


    /* Methods executed by run() */

    @Override
    public void readIncomingMsgs() {
        if(msgQueue.size() != 0) {

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
     * @param dataList
     */
    private void addOrUpdateDataToKnowledgeBase(ArrayList<DataVar> dataList){
        knowledgeBase.addAll(dataList);

        //TODO: update if it already exists
    }

    @Override
    public void doDecisionMaking() {
        ArrayList<_SimAction_> possibleMoveActions = new ArrayList<>();


        for (_SimAction_ aAction : capableActionList){
            //If aAction is not a MoveAction
            if (!(aAction instanceof MoveAction)) {
                if (aAction.checkPrecondition()) {
                    selectedActionList.add(aAction);
//                    System.out.println("selectedActionList.size(): " + selectedActionList.size());
                }
            }
            //If aAction is MoveAction
            else{
                if (aAction.checkPrecondition()) {
//                    System.out.println("HHH");
                    possibleMoveActions.add(aAction);
//                    System.out.println("selectedActionList.size(): " + selectedActionList.size());

                }
            }
        }


        //TODO: MoveAction selection
        /* Selection of move action (current: random) */
        Random rand = new Random();
//        System.out.println(possibleMoveActions.size());
        int selectedMoveActionIndex = rand.nextInt(possibleMoveActions.size());
        //System.out.println(selectedMoveActionIndex);

        selectedActionList.add(possibleMoveActions.get(selectedMoveActionIndex));

        System.out.println("[" + timestamp + "] (Constituent:" + this.getClass().getSimpleName() + ":post-doDecisionMaking) capableActionList(" +
                capableActionList.size() + "), selectedActionList(" + selectedActionList.size() + ") = " + selectedActionList);
    }


    @Override
    protected void selectActions() {
//        timestamp = new Timestamp(System.currentTimeMillis());
//        System.out.println("[" + timestamp + "] (Constituent:" + this.getClass().getSimpleName() + ":pre-doDecisionMaking) capableActionList(" +
//                capableActionList.size() + "), selectedActionList(" + selectedActionList.size() + ")");

        doDecisionMaking();

    }

    @Override
    public void doAction(_SimAction_ actionObj) {
        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (Constituent:" + this.getClass().getSimpleName() + "(" + id + "):doAction) " + actionObj.getActionId() + " is executed.");

        if (actionObj instanceof MoveAction){
            actionObj.executeAction();
            //ObjectLocation curLoc = getCurLocation();
        }else if(actionObj instanceof CommAction){
            actionObj.executeAction();
        }else if(actionObj instanceof FuncAction){
            actionObj.executeAction();
        }

//        System.out.println();
    }


//    private void doMoveAction(MoveAction moveAction, )


//    //For debugging
//    private void printCSInfo(){
//        System.out.println(mySoS + "|" + mySoS.sosMap + "|" + myOrg + "|" + id + "|" + name);
//    }


    @Override
    public void move() {

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
