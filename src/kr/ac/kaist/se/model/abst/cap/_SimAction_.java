package kr.ac.kaist.se.model.abst.cap;

import kr.ac.kaist.se.model.abst.obj._SimActionableObject_;
import kr.ac.kaist.se.model.sos.SoS;

/**
 * Abstract class to represent an action object
 *
 * @author ymbaek
 */
public abstract class _SimAction_ {

    protected SoS accessibleSoS;    //Accessible SimModel (SoS)
    protected _SimActionableObject_ actionSubject;  //Subject who performs this action

    protected String actionId;      //id of action
    protected String actionName;    //name of action

    protected int actionDuration = -1;  //duration of action execution
    protected float actionCost = -1;    //cost for action execution
    protected float actionBenefit = -1; //benefit from action execution


    public _SimAction_(SoS accessibleSoS,
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

    protected abstract boolean checkPrecondition();

    //TODO: check (set) return
    public abstract void executeAction();


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
