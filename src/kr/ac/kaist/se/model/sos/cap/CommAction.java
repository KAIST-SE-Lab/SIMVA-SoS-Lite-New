package kr.ac.kaist.se.model.sos.cap;

import kr.ac.kaist.se.model.abst.cap._SimAction_;
import kr.ac.kaist.se.model.abst.obj._SimActionableObject_;
import kr.ac.kaist.se.model.sos.SoS;

/**
 * A concrete class to represent an action for communication.
 * The communication is performed by sending a message to a specific object.
 *
 * @author ymbaek
 */
public class CommAction extends _SimAction_ {

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

    @Override
    public boolean checkPrecondition() {
        //TODO: Edit checkPrecondition phrase
        return true;
    }

    @Override
    public boolean executeAction() {
        return true;
    }
}
