package kr.ac.kaist.se.simdata.evnt;

import kr.ac.kaist.se.model.abst.evnt.EnumEventType;
import kr.ac.kaist.se.model.abst.evnt._SimEvent_;
import kr.ac.kaist.se.model.abst.obj._SimObject_;

import java.sql.Timestamp;

/**
 * An event that is used to recorded in a log (SimLog)
 * A SimLogEvent should contain timestamp and run/update results for each tick of simulation
 *
 * @author ymbaek
 */
public class SimLogEvent extends _SimEvent_ {

    private Timestamp timestamp;
    private int simTime;

    private String subjectObjId;
    private _SimObject_ subjectObj;

    private String eventSpec;


    public SimLogEvent() {
    }

    public SimLogEvent(String id,
                       EnumEventType eventType,
                       Timestamp timestamp,
                       int simTime,
                       String subjectObjId,
                       _SimObject_ subjectObj,
                       String eventSpec) {

        this.id = id;
        this.eventType = eventType;

        this.timestamp = timestamp;
        this.simTime = simTime;
        this.subjectObjId = subjectObjId;
        this.subjectObj = subjectObj;
        this.eventSpec = eventSpec;
    }



    public String getStringLogEvent(){
        String stringLogEvent = "";

        stringLogEvent += simTime + "|";
        stringLogEvent += timestamp + "|";
        stringLogEvent += id + "|";
        stringLogEvent += eventType + "|";
        stringLogEvent += subjectObjId + "|";
        stringLogEvent += subjectObj + "|";
        stringLogEvent += eventSpec;

        return stringLogEvent;
    }
}
