package cap;

import kr.ac.kaist.se.model.abst.evnt.EnumEventType;
import kr.ac.kaist.se.model.abst.obj._SimActionableObject_;
import kr.ac.kaist.se.model.sos.SoS;
import kr.ac.kaist.se.model.sos.cap.FuncAction;
import kr.ac.kaist.se.simdata.evnt.SimLogEvent;

import java.sql.Timestamp;
import java.util.ArrayList;

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
    public ArrayList<SimLogEvent> executeAction(int tick) {

        //Clear of the actionLogEvents to make new logEvents
        actionLogEvents.clear();

        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (" + this.getClass().getSimpleName() + ":executeAction) FuncAction (" + actionId + ")");

        //Generate LogEvent
        actionLogEvents.add(new SimLogEvent(actionSubject.getLogEventIdAutomatically(this),
                EnumEventType.NOT_DETERMINED,
                new Timestamp(System.currentTimeMillis()),
                tick,
                actionSubject.getId(),
                actionSubject,
                generateLogEventSpec()));

        return actionLogEvents;
    }

    @Override
    public String generateLogEventSpec() {
        return null;
    }
}
