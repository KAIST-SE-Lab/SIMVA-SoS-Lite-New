package kr.ac.kaist.se.model.sos.cap;

import kr.ac.kaist.se.model.abst.cap._SimAction_;
import kr.ac.kaist.se.model.abst.comm._SimMessage_;
import kr.ac.kaist.se.model.abst.obj._SimActionableObject_;
import kr.ac.kaist.se.model.sos.SoS;

/**
 * A concrete class to represent an action for communication.
 * The communication is performed by sending a message to a specific object.
 *
 * @author ymbaek
 */
public class CommAction extends _SimAction_ {

    private _SimMessage_ message;

    public CommAction(SoS accessibleSoS,
                         _SimActionableObject_ actionSubject,
                         String actionId,
                         String actionName) {
        super(accessibleSoS, actionSubject, actionId, actionName);
    }

    public CommAction(SoS accessibleSoS,
                         _SimActionableObject_ actionSubject,
                         String actionId,
                         String actionName,
                         int actionDuration,
                         float actionCost,
                         float actionBenefit) {
        super(accessibleSoS, actionSubject, actionId, actionName, actionDuration, actionCost, actionBenefit);
    }

    public CommAction(SoS accessibleSoS, _SimActionableObject_ actionSubject, String actionId, String actionName, _SimMessage_ message) {
        super(accessibleSoS, actionSubject, actionId, actionName);
        this.message = message;
    }

    public CommAction(SoS accessibleSoS, _SimActionableObject_ actionSubject, String actionId, String actionName, int actionDuration, float actionCost, float actionBenefit, _SimMessage_ message) {
        super(accessibleSoS, actionSubject, actionId, actionName, actionDuration, actionCost, actionBenefit);
        this.message = message;
    }

    @Override
    public boolean checkPrecondition() {
        //TODO: Edit checkPrecondition phrase
        return true;
    }

    @Override
    public boolean executeAction() {
        //Send a message
        return true;
    }

    public _SimMessage_ getMessage() {
        return message;
    }

    public void setMessage(_SimMessage_ message) {
        this.message = message;
    }
}
