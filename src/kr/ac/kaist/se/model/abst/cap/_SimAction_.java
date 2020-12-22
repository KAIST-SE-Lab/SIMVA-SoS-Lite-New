package kr.ac.kaist.se.model.abst.cap;

import kr.ac.kaist.se.model.abst.obj._SimActionableObject_;
import kr.ac.kaist.se.model.sos.SoS;
import kr.ac.kaist.se.simdata.evnt.SimLogEvent;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Abstract class to represent an action object
 *
 * @author ymbaek
 */
public abstract class _SimAction_ {

    protected Timestamp timestamp;    //Timestamp for stdout

    /* ArrayList to store SimLogEvents of executed actions for return */
    protected ArrayList<SimLogEvent> actionLogEvents = new ArrayList<>();

    protected SoS accessibleSoS;    //Accessible SimModel (SoS)
    protected _SimActionableObject_ actionSubject;  //Subject who performs this action

    protected String actionId;      //id of action
    protected String actionName;    //name of action

    protected int actionDuration = -1;  //duration of action execution
    protected float actionCost = -1;    //cost for action execution
    protected float actionBenefit = -1; //benefit from action execution

    protected  _SimAction_(SoS accessibleSoS,
                       _SimActionableObject_ actionSubject,
                       String actionId,
                       String actionName) {
        this.accessibleSoS = accessibleSoS;
        this.actionSubject = actionSubject;
        this.actionId = actionId;
        this.actionName = actionName;
    }

    protected _SimAction_(SoS accessibleSoS,
                          _SimActionableObject_ actionSubject,
                          String actionId,
                          String actionName,
                          int actionDuration,
                          float actionCost,
                          float actionBenefit) {
        this.accessibleSoS = accessibleSoS;
        this.actionSubject = actionSubject;
        this.actionId = actionId;
        this.actionName = actionName;
        this.actionDuration = actionDuration;
        this.actionCost = actionCost;
        this.actionBenefit = actionBenefit;
    }


    /**
     * A method to check precondition of this action
     * @return true if this action is executable
     */
    public abstract boolean checkPrecondition();

    /**
     * A method to actually execute this action
     * @param tick current tick of simulation
     * @return List of SimLogEvent generated after executing this action
     */
    //TODO: check (set) return
    public abstract ArrayList<SimLogEvent> executeAction(int tick);

    /**
     * A method to generate event specification for SimEventLog
     * @return Generated String-type log event specification
     */
    public abstract String generateLogEventSpec();


    public SoS getAccessibleSoS() {
        return accessibleSoS;
    }

    public void setAccessibleSoS(SoS accessibleSoS) {
        this.accessibleSoS = accessibleSoS;
    }

    public _SimActionableObject_ getActionSubject() {
        return actionSubject;
    }

    public void setActionSubject(_SimActionableObject_ actionSubject) {
        this.actionSubject = actionSubject;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public int getActionDuration() {
        return actionDuration;
    }

    public void setActionDuration(int actionDuration) {
        this.actionDuration = actionDuration;
    }

    public float getActionCost() {
        return actionCost;
    }

    public void setActionCost(float actionCost) {
        this.actionCost = actionCost;
    }

    public float getActionBenefit() {
        return actionBenefit;
    }

    public void setActionBenefit(float actionBenefit) {
        this.actionBenefit = actionBenefit;
    }
}
