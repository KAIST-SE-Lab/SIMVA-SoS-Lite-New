package cap;

import kr.ac.kaist.se.model.abst.obj._SimActionableObject_;
import kr.ac.kaist.se.model.sos.SoS;
import kr.ac.kaist.se.model.sos.cap.FuncAction;

import java.sql.Timestamp;

public class ExampleFuncAction extends FuncAction {
    public ExampleFuncAction(SoS accessibleSoS, _SimActionableObject_ actionSubject, String actionId, String actionName) {
        super(accessibleSoS, actionSubject, actionId, actionName);
    }

    public ExampleFuncAction(SoS accessibleSoS, _SimActionableObject_ actionSubject, String actionId, String actionName, int actionDuration, float actionCost, float actionBenefit) {
        super(accessibleSoS, actionSubject, actionId, actionName, actionDuration, actionCost, actionBenefit);
    }

    @Override
    public boolean checkPrecondition() {
        return true;
    }

    @Override
    public boolean executeAction() {
        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (" + this.getClass().getSimpleName() + ":executeAction) FuncAction (" + actionId + ")");


        return true;
    }
}
