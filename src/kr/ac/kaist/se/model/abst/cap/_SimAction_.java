package kr.ac.kaist.se.model.abst.cap;

import kr.ac.kaist.se.model.abst.obj._SimActionableObject_;
import kr.ac.kaist.se.model.sos.SoS;

/**
 * Abstract class to represent an action object
 *
 * @author ymbaek
 */
public abstract class _SimAction_ {

    protected SoS accessibleSoS;
    protected _SimActionableObject_ actionSubject;

    protected String actionId;
    protected String actionTag;
    protected String actionName;

    protected int actionDuration;
    protected float actionCost;
    protected float actionBenefit;


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

    public String getActionTag() {
        return actionTag;
    }

    public void setActionTag(String actionTag) {
        this.actionTag = actionTag;
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
