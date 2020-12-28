package kr.ac.kaist.se.model.abst.obj;

import kr.ac.kaist.se.model.abst.cap._SimAction_;
import kr.ac.kaist.se.model.abst.comm._SimMessage_;
import kr.ac.kaist.se.model.abst.state._SimState_;
import kr.ac.kaist.se.model.intf.Simulatable;
import kr.ac.kaist.se.model.sos.data.DimensionVar;
import kr.ac.kaist.se.model.sos.geo.ObjectLocation;
import kr.ac.kaist.se.model.sos.state.ObjectState;

import java.sql.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Abstract class for general simulation objects
 *
 * @author ymbaek
 */
public abstract class _SimObject_ implements Simulatable {

    protected Timestamp timestamp;    //Timestamp for stdout

    /** Basic information of a SimObject */
    //Universally unique object id (Every SimObject should have its own unique id)
    protected String id;
    //Name of an object
    protected String name;


    /** Properties and state information of a SimObject */
     //Static object / Dynamic object
    protected boolean isStatic;
    //Activated object / Deactivated object
    protected boolean isActivated;
    //Available object / Unavailable object
    protected boolean isAvailable;

    //States of an object
    protected ArrayList<ObjectState> objStates = new ArrayList<>();
    //Initial state of an object
    protected String initialStateId;
    //Current state of an object
    protected String curStateId;



    //Current location of this object
    protected ObjectLocation objLocation;   //Every SimObject has its geo-location

    //Queue for storing incoming messages from other objects
    protected Queue<_SimMessage_> msgQueue = new LinkedList<>();

    //ArrayList to store logEventIds
    private final ArrayList<String> logEventIdList = new ArrayList<>();


    /**
     * Print object information of a _SimObject_
     */
    protected void printObjInfo() {
        String thisClassName = this.getClass().getSimpleName();

        if (this.getClass() != null) {
            timestamp = new Timestamp(System.currentTimeMillis());
            //System.out.println("[" + timestamp + "] (" + thisClassName + ") An object is created.");
            System.out.println("[" + timestamp + "] (" + thisClassName + " Object Created) id: " + this.id +
                    ", name: " + this.name +
                    ", isStatic: " + this.isStatic +
                    ", isActivated: " + this.isActivated +
                    ", isAvailable: " + this.isAvailable
            );
        }

    }

//    public _SimObject_() {
//        initObjLocation();
//    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    /**
     * A method that returns current location of this object
     * @return ObjectLocation of this organization
     */
    public ObjectLocation getCurLocation() {
//        timestamp = new Timestamp(System.currentTimeMillis());
//        System.out.print("[" + timestamp + "] (" + this.getClass().getSimpleName() + "(" + id + "):getCurLocation) ");
//
//        //Updated location
//        int index = 0;
//        System.out.print("(");
//        for (DimensionVar locDimVar : objLocation.getObjLocDimVars()){
//            System.out.print(objLocation.getObjLocDimVars().get(index++).getDataCurValue());
//            if (index < objLocation.getObjLocDimVars().size()){
//                System.out.print(",");
//            }
//        }
//        System.out.println(")");

        return objLocation;
    }

    public void setObjLocation(ObjectLocation objLocation) {
        this.objLocation = objLocation;
    }

    public Queue<_SimMessage_> getMsgQueue() {
        return msgQueue;
    }

    public void setMsgQueue(Queue<_SimMessage_> msgQueue) {
        this.msgQueue = msgQueue;
    }

    public ArrayList<ObjectState> getObjStates() {
        return objStates;
    }

    public void setObjStates(ArrayList<ObjectState> objStates) {
        this.objStates = objStates;
    }

    public void addObjState(ObjectState aObjState){
        boolean isDuplicate = false;
        for (_SimState_ state : objStates){
            if (aObjState.getId().equals(state.getId())){
                isDuplicate = true;
            }
        }

        if (aObjState != null && isDuplicate == false){
            objStates.add(aObjState);
        }
    }

    public String getInitialStateId() {
        return initialStateId;
    }

    public void setInitialStateId(String initialStateId) {
        this.initialStateId = initialStateId;
    }

    public String getCurStateId() {
        return curStateId;
    }

    public void setCurStateId(String curStateId) {
        this.curStateId = curStateId;
    }

    /**
     * A method to get an actual state object (ObjectState) with id
     * @param stateId id to be found
     * @return a state found, which have the stateId
     */
    public ObjectState getCurState(String stateId){
        ObjectState returnState = null;

        for (ObjectState state : objStates){
            if (stateId.equals(state.getId())){
                returnState = state;
            }
        }

        return returnState;
    }

    public ObjectLocation getObjLocation() {
        return objLocation;
    }

    /**
     * Print current location of this object using getCurLocation()
     */
    protected void printCurLocation() {
//        timestamp = new Timestamp(System.currentTimeMillis());
//        System.out.print("[" + timestamp + "] (" + this.getClass().getSimpleName() + ") ObjctLocation is initialized: (");

        int index = 0;
        System.out.print("(");
        for (DimensionVar dimVar : objLocation.getObjLocDimVars()) {
            if (index + 1 < objLocation.getObjLocDimVars().size()) {
                System.out.print(dimVar.getDataCurValue() + ",");
            } else {
                System.out.print(dimVar.getDataCurValue());
            }
            index++;
        }
        System.out.println(")");
    }


    /**
     * Method to automatically generate an id of a SimLogEvent
     *
     * @param eventAction target action to be executed
     * @return Generated id
     */
    public String getLogEventIdAutomatically(_SimAction_ eventAction) {
        String newId = "";

        Random rand = new Random();
//        newId += rand.nextInt( 99999) + "";

//        boolean isDuplicate = false;

        while (true) {
            newId += String.format("EVNT_%S_%s_%s", eventAction.getClass().getSimpleName(), this.id, Integer.toHexString(rand.nextInt(99999)));
            if (!logEventIdList.contains(newId)) {
                break;
            }
        }

        return newId;
    }


}
