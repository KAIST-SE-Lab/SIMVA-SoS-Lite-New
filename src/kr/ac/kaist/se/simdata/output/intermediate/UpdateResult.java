package kr.ac.kaist.se.simdata.output.intermediate;

import kr.ac.kaist.se.simdata.output.SimLog;

import java.util.ArrayList;

/**
 * Data type to store execution results of update() method of _SimObject_
 *
 * @author ehcho, yjshin, ymbaek
 */
public class UpdateResult {

    ArrayList<SimLog> updateLogList;

    public UpdateResult() {
        this.updateLogList = new ArrayList<>();
    }

    public UpdateResult(ArrayList<SimLog> updateLogList) {
        this.updateLogList = updateLogList;
    }

    public ArrayList<SimLog> getUpdateLogList() {
        return updateLogList;
    }

    public void setLogList(ArrayList<SimLog> updateLog) {
        this.updateLogList = updateLog;
    }

    public void addLogToList(SimLog aLog){
        this.updateLogList.add(aLog);
    }

    public void addAllLogToList(ArrayList<SimLog> aLogList){
        this.updateLogList.addAll(aLogList);
    }
}
