package kr.ac.kaist.se.model.sos.cap;

import kr.ac.kaist.se.model.abst.cap._SimAction_;
import kr.ac.kaist.se.model.abst.comm._SimMessage_;
import kr.ac.kaist.se.model.abst.evnt.EnumEventType;
import kr.ac.kaist.se.model.abst.obj._SimActionableObject_;
import kr.ac.kaist.se.model.sos.SoS;
import kr.ac.kaist.se.simdata.evnt.SimLogEvent;

import java.sql.Timestamp;
import java.util.ArrayList;

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
        if (message.isSendable()){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public ArrayList<SimLogEvent> executeAction(int tick) {

        //Clear of the actionLogEvents to make new logEvents
        actionLogEvents.clear();

        //Send a message
        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (CommAction:executeAction) message[" +
                message.getMsgId() + "|" +
                message.getMsgTag() + "|" +
                message.getMsgType() + "|" +
                message.getSenderId() + "->" +
                message.getReceiverId() + "|" +
                message.getMsgDataList() + "]");

        //Generate LogEvent
        actionLogEvents.add(new SimLogEvent(actionSubject.getLogEventIdAutomatically(this),
                EnumEventType.COMMUNICATION,
                new Timestamp(System.currentTimeMillis()),
                tick,
                actionSubject.getId(),
                actionSubject,
                "EVENT_SPEC"));


        //TODO: check return
        return actionLogEvents;
    }

    public _SimMessage_ getMessage() {
        return message;
    }

    public void setMessage(_SimMessage_ message) {
        this.message = message;
    }
}
