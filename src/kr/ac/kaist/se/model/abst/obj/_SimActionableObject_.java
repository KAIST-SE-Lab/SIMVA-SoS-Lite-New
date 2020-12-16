package kr.ac.kaist.se.model.abst.obj;

import kr.ac.kaist.se.controller.sim.SimEngine;
import kr.ac.kaist.se.model.abst.cap._SimAction_;
import kr.ac.kaist.se.model.abst.evnt.EnumEventType;
import kr.ac.kaist.se.model.intf.Actionable;
import kr.ac.kaist.se.simdata.evnt.SimLogEvent;
import kr.ac.kaist.se.simdata.output.intermediate.RunResult;
import kr.ac.kaist.se.simdata.output.intermediate.UpdateResult;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Abstract class for an object that can execute its own behaviors
 * (i.e., objects that are capable of doing individual actions)
 *
 * @author ymbaek
 */
public abstract class _SimActionableObject_ extends _SimObject_ implements Actionable {

    //List of capable actions
    protected ArrayList<_SimAction_> capableActionList = new ArrayList<>();

    //List of selected actions for execution of a particular tick
    protected ArrayList<_SimAction_> selectedActionList = new ArrayList<>();


    /**
     *
     * @param runResult
     * @return
     */
    public UpdateResult update(RunResult runResult, int tick){
//        timestamp = new Timestamp(System.currentTimeMillis());
//        System.out.println("[" + timestamp + "] (" + this.getClass().getSimpleName() + "/_SimActionableObject_(" + id + "):update)");

        UpdateResult updateResult = new UpdateResult();

        for (_SimAction_ action: runResult.getSelectedActionList()){
            SimLogEvent actionLogEvent = doAction(action);

            //TODO: Add proper SimLog object
//            updateResult.addLogToList(null);

            updateResult.addLogEventToList(actionLogEvent);
        }

        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "]  ----------------------------");


        return updateResult;
    }

    /* Abstract methods */

    /**
     * Initialization of actions
     */
    protected abstract void initActions();

    /**
     * Select an action(s) and store into selectedActionList
     */
    protected abstract void selectActions();

    /* Action add/remove of capableActionList and selectedActionList */

    public void addActionToCapableActionList(_SimAction_ aAction){
        capableActionList.add(aAction);
    }

    public void addActionToSelectedActionList(_SimAction_ aAction){
        selectedActionList.add(aAction);
    }

    public void removeActionFromCapableActionList(_SimAction_ aAction){
        capableActionList.remove(aAction);
    }

    public void removeActionFromSelectedActionList(_SimAction_ aAction){
        selectedActionList.remove(aAction);
    }

    public void clearSelectedActionList(){
//        int beforeSize = selectedActionList.size();
        selectedActionList.clear();

//        timestamp = new Timestamp(System.currentTimeMillis());
//        System.out.println("[" + timestamp + "] (" + this.getClass().getSimpleName() + ":clearSelectedActionList) before size:" + beforeSize);
    }


    /* Getters & Setters */

    public ArrayList<_SimAction_> getCapableActionList() {
        return capableActionList;
    }

    public void setCapableActionList(ArrayList<_SimAction_> capableActionList) {
        this.capableActionList = capableActionList;
    }

    public ArrayList<_SimAction_> getSelectedActionList() {
        return selectedActionList;
    }

    public void setSelectedActionList(ArrayList<_SimAction_> selectedActionList) {
        this.selectedActionList = selectedActionList;
    }
}
