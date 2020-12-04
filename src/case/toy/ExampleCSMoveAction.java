import kr.ac.kaist.se.model.abst.obj._SimActionableObject_;
import kr.ac.kaist.se.model.sos.SoS;
import kr.ac.kaist.se.model.sos.cap.MoveAction;

public class ExampleCSMoveAction extends MoveAction {

    protected ExampleCSMoveAction(
            SoS accessibleSoS,
            _SimActionableObject_ actionSubject,
            String actionId,
            String actionName) {
        super(accessibleSoS, actionSubject, actionId, actionName);
    }

    protected ExampleCSMoveAction(
            SoS accessibleSoS,
            _SimActionableObject_ actionSubject,
            String actionId,
            String actionName,
            int actionDuration,
            float actionCost,
            float actionBenefit) {
        super(accessibleSoS, actionSubject, actionId, actionName, actionDuration, actionCost, actionBenefit);
    }
}
