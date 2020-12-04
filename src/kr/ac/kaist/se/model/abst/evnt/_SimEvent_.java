package kr.ac.kaist.se.model.abst.evnt;

import java.sql.Timestamp;

/**
 * Abstract class for event-based classes
 * SimScenarioEvent and SimLogEvent are type of _SimEvent_
 *
 * @author ymbaek
 */
public abstract class _SimEvent_ {

    protected Timestamp timestamp;    //Timestamp for stdout

    protected String id;                //id of an event
    protected String name;              //name of an event (used as a label)
    protected EnumEventType eventType;   //type of an event


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EnumEventType getEventType() {
        return eventType;
    }

    public void setEventType(EnumEventType eventType) {
        this.eventType = eventType;
    }
}
