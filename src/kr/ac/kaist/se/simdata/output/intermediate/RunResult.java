package kr.ac.kaist.se.simdata.output.intermediate;

import kr.ac.kaist.se.model.abst.cap._SimAction_;
import kr.ac.kaist.se.model.abst.obj._SimObject_;

import java.util.ArrayList;

/**
 * Data type to store execution results of run() method of _SimObject_
 *
 * @author ehcho, yjshin, ymbaek
 */
public class RunResult {

    private _SimObject_ runSubject;

    private ArrayList<_SimAction_> selectedActionList;
    private ArrayList<RunResult> subRunResults;

    public RunResult(){

    }

    public _SimObject_ getRunSubject() {
        return runSubject;
    }

    public void setRunSubject(_SimObject_ runSubject) {
        this.runSubject = runSubject;
    }

    public ArrayList<_SimAction_> getSelectedActionList() {
        return selectedActionList;
    }

    public void setSelectedActionList(ArrayList<_SimAction_> selectedActionList) {
        this.selectedActionList = selectedActionList;
    }

    public ArrayList<RunResult> getSubRunResults() {
        return subRunResults;
    }

    public void setSubRunResults(ArrayList<RunResult> subRunResults) {
        this.subRunResults = subRunResults;
    }

    public void addSubRunResult(RunResult aRunResult){
        this.subRunResults.add(aRunResult);
    }
}
