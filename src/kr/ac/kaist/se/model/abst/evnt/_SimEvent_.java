package kr.ac.kaist.se.model.abst.evnt;

/**
 * Abstract class for event-based classes
 * SimScenarioEvent and SimLogEvent are type of _SimEvent_
 *
 * @author ymbaek
 */
public abstract class _SimEvent_ {

    protected String id;                //id of an event
    protected String name;              //name of an event (used as a label)
    protected EnumEventType eventType;   //type of an event

}
