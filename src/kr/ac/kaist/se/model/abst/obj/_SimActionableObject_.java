package kr.ac.kaist.se.model.abst.obj;

import kr.ac.kaist.se.model.abst.cap._SimAction_;
import kr.ac.kaist.se.model.intf.Actionable;
import kr.ac.kaist.se.simdata.output.intermediate.RunResult;
import kr.ac.kaist.se.simdata.output.intermediate.UpdateResult;

import java.util.ArrayList;

/**
 * Abstract class for an object that can execute its own behaviors
 * (i.e., objects that are capable of doing individual actions)
 *
 * @author ymbaek
 */
public abstract class _SimActionableObject_ extends _SimObject_ implements Actionable {

    protected ArrayList<_SimAction_> capableActionList;
    protected ArrayList<_SimAction_> selectedActionList;


    /**
     *
     * @param runResult
     * @return
     */
    public UpdateResult update(RunResult runResult){
        UpdateResult updateResult = new UpdateResult();

        for (_SimAction_ action: runResult.getSelectedActionList()){
            doAction(action);

            //TODO: Add proper SimLog object
            updateResult.addLogToList(null);
        }

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
        selectedActionList.clear();
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
