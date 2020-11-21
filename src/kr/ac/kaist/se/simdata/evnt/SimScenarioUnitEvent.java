package kr.ac.kaist.se.simdata.evnt;

import kr.ac.kaist.se.model.abst.evnt.*;
import kr.ac.kaist.se.model.abst.obj._SimObject_;

/**
 * Unit event for scenario execution
 * @author ymbaek
 */
public class SimScenarioUnitEvent extends _SimEvent_{

    /* Target information */
    protected String targetObjId;       //Id of target object
    protected _SimObject_ targetObj;    //Target object

    protected EnumEventTargetScope targetScope; //Scope of target

    /* Behavioral information */
    protected EnumEventPredefBehavior predefBehavior;   //Predefined behavior of an event

    /* Temporal information */
    protected int startTime;

    /* Probability information */
    protected boolean isProbabilistic;      //Probabilistic event or not
    protected EnumEventProbDist probDist;   //Probability distribution
    protected String probExp;               //Expression of probability (this should conform to probDist)


    public SimScenarioUnitEvent(String id,
                                String name,
                                EnumEventType eventType,
                                String targetObjId,
                                _SimObject_ targetObj,
                                EnumEventTargetScope targetScope,
                                EnumEventPredefBehavior predefBehavior,
                                int startTime,
                                boolean isProbabilistic,
                                EnumEventProbDist probDist,
                                String probExp) {

        this.id = id;
        this.name = name;
        this.eventType = eventType;

        this.targetObjId = targetObjId;
        this.targetObj = targetObj;
        this.targetScope = targetScope;
        this.predefBehavior = predefBehavior;
        this.startTime = startTime;
        this.isProbabilistic = isProbabilistic;
        this.probDist = probDist;
        this.probExp = probExp;
    }

    public void executeEvent(){
        if (predefBehavior == EnumEventPredefBehavior.NOT_DETERMINED){

        }
    }


    public String getTargetObjId() {
        return targetObjId;
    }

    public void setTargetObjId(String targetObjId) {
        this.targetObjId = targetObjId;
    }

    public _SimObject_ getTargetObj() {
        return targetObj;
    }

    public void setTargetObj(_SimObject_ targetObj) {
        this.targetObj = targetObj;
    }

    public EnumEventTargetScope getTargetScope() {
        return targetScope;
    }

    public void setTargetScope(EnumEventTargetScope targetScope) {
        this.targetScope = targetScope;
    }

    public EnumEventPredefBehavior getPredefBehavior() {
        return predefBehavior;
    }

    public void setPredefBehavior(EnumEventPredefBehavior predefBehavior) {
        this.predefBehavior = predefBehavior;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public boolean isProbabilistic() {
        return isProbabilistic;
    }

    public void setProbabilistic(boolean probabilistic) {
        isProbabilistic = probabilistic;
    }

    public EnumEventProbDist getProbDist() {
        return probDist;
    }

    public void setProbDist(EnumEventProbDist probDist) {
        this.probDist = probDist;
    }

    public String getProbExp() {
        return probExp;
    }

    public void setProbExp(String probExp) {
        this.probExp = probExp;
    }
}
