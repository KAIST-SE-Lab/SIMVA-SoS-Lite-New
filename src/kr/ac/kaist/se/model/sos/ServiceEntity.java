package kr.ac.kaist.se.model.sos;

import kr.ac.kaist.se.model.abst.cap._SimAction_;
import kr.ac.kaist.se.model.abst.obj._SimActionableObject_;
import kr.ac.kaist.se.model.intf.Communicatable;
import kr.ac.kaist.se.model.sos.cap.CommAction;
import kr.ac.kaist.se.model.sos.cap.FuncAction;
import kr.ac.kaist.se.model.sos.cap.MoveAction;
import kr.ac.kaist.se.simdata.output.intermediate.RunResult;

import java.sql.Timestamp;
import java.util.ArrayList;

public abstract class ServiceEntity extends _SimActionableObject_
        implements Communicatable {

    //SoS that this object belongs to
    protected SoS mySoS;
    //Infrastructure that this object belongs to
    protected Infrastructure myInfra;


    @Override
    public RunResult run() {
        //TODO: Duplicate code (<-> Constituent) (it can differ depending on its implementation)
        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (" + this.getClass().getSimpleName() + "(" + id + " of " + myInfra.getId() + "):run) size of capableActions:" +
                capableActionList.size() + " = " + capableActionList);

        // [Communicatable] Before selecting actions, read a message from its message queue
        readIncomingMsgs();

        //Clear existing selectedActionList
        clearSelectedActionList();

        //Select actions to execute
        selectActions();

        RunResult runResult = new RunResult(this, this.selectedActionList);
//        System.out.println("[" + timestamp + "]  ----------------------------");

        return runResult;
    }

    @Override
    protected void selectActions() {

        //TODO: Duplicate code (<-> Constituent) (it can differ depending on its implementation)
        for (_SimAction_ aAction : capableActionList) {
            //If aAction is not a MoveAction
            if (aAction instanceof FuncAction) {
                if (aAction.checkPrecondition()) {
                    selectedActionList.add(aAction);
                }
            }
            else if (aAction instanceof CommAction) {
                //Dynamically set a message based on makeMsgForCommAction() method.
                //The makeMsgForCommAction() method should be implemented in a concrete Constituent class.
                ((CommAction) aAction).setMessage(makeMsgForCommAction((CommAction) aAction));

                //TODO: Selection mechanism for CommAction (now: select all communication actions)
                if (aAction.checkPrecondition()) {
                    selectedActionList.add(aAction);
                }
            }
        }


        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (ServiceEntity:" + this.getClass().getSimpleName() + ") capableActionList(" +
                capableActionList.size() + "), selectedActionList(" + selectedActionList.size() + ") = " + selectedActionList);

    }
}
