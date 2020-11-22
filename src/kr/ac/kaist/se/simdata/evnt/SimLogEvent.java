package kr.ac.kaist.se.simdata.evnt;

import kr.ac.kaist.se.model.abst.evnt.EnumEventType;
import kr.ac.kaist.se.model.abst.evnt._SimEvent_;

/**
 * An event that is used to recorded in a log (SimLog)
 * A SimLogEvent should contain timestamp and run/update results for each tick of simulation
 *
 * @author ymbaek
 */
public class SimLogEvent extends _SimEvent_ {

    public SimLogEvent() {
    }

    public SimLogEvent(String id,
                       String name,
                       EnumEventType eventType) {

        this.id = id;
        this.name = name;
        this.eventType = eventType;
    }
}
