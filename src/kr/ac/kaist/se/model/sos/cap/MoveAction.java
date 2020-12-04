package kr.ac.kaist.se.model.sos.cap;

import kr.ac.kaist.se.model.abst.cap._SimAction_;
import kr.ac.kaist.se.model.abst.obj._SimActionableObject_;
import kr.ac.kaist.se.model.sos.SoS;

/**
 * A concrete class to represent an action for geographical movement.
 *
 * @author ymbaek
 */
public abstract class MoveAction extends _SimAction_ {

    protected MoveAction(SoS accessibleSoS,
                         _SimActionableObject_ actionSubject,
                         String actionId,
                         String actionName) {
        super(accessibleSoS, actionSubject, actionId, actionName);
    }

    protected MoveAction(SoS accessibleSoS,
                         _SimActionableObject_ actionSubject,
                         String actionId,
                         String actionName,
                         int actionDuration,
                         float actionCost,
                         float actionBenefit) {
        super(accessibleSoS, actionSubject, actionId, actionName, actionDuration, actionCost, actionBenefit);
    }

    @Override
    protected boolean checkPrecondition() {
        return false;
    }

    @Override
    public void executeAction() {

    }
}
