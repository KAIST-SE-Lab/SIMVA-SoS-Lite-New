package kr.ac.kaist.se.simdata.evnt;

import kr.ac.kaist.se.controller.sim.SimEngine;
import kr.ac.kaist.se.model.abst.evnt.*;
import kr.ac.kaist.se.model.abst.obj._SimObject_;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * An event included in a simulation scenario (SimScenario)
 * SimScenarioEvent should contain:
 *      (i) target information (who will be affected)
 *      (ii) behavioral information (what to execute)
 *      (iii) temporal information (when to execute)
 *      (iv) probability information (what probability)
 *
 * @author ymbaek
 */
public class SimScenarioEvent extends _SimEvent_ {

    Timestamp timestamp;    //Timestamp for stdout
    int simTotalTime = 0;   //Total simulation time

    /* Target information */
    private String targetObjId;       //Id of target object
    private _SimObject_ targetObj;    //Target object

    private EnumEventTargetScope targetScope; //Scope of target

    /* Behavioral information */
    private EnumEventPredefBehavior predefBehavior;   //Predefined behavior of an event

    /* Temporal information */
    private EnumEventOccPattern occPattern;   //Occurrence pattern
    private int startTime = 0;                    //Start time for all events
    private int endTime = 0;                      //End time for all events
    private int duration = 1;                     //Duration for an event that has duration
    private int period = -1;                       //Period for periodic events

    /* Probability information */
    private boolean isProbabilistic;      //Probabilistic event or not
    private EnumEventProbDist probDist;   //Probability distribution
    private String probExp;               //Expression of probability (this should conform to probDist)

    public SimScenarioEvent(String id,
                            String name,
                            EnumEventType eventType,
                            int simTotalTime,
                            String targetObjId,
                            _SimObject_ targetObj,
                            EnumEventTargetScope targetScope,
                            EnumEventPredefBehavior predefBehavior,
                            EnumEventOccPattern occPattern,
                            int startTime,
                            int endTime,
                            int duration,
                            int period,
                            boolean isProbabilistic,
                            EnumEventProbDist probDist,
                            String probExp) {
        /* _SimEvent_ */
        this.id = id;
        this.name = name;
        this.eventType = eventType;

        /* SimScenarioEvent */
        this.simTotalTime = simTotalTime;
        this.targetObjId = targetObjId;
        this.targetObj = targetObj;
        this.targetScope = targetScope;
        this.predefBehavior = predefBehavior;
        this.occPattern = occPattern;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.period = period;
        this.isProbabilistic = isProbabilistic;
        this.probDist = probDist;
        this.probExp = probExp;
    }


    /**
     * A method for decomposing an event to unit events.
     * SimScenarioEvent object is decomposed into one or more SimScenarioUnitEvents,
     * according to occPattern of the SimScenarioEvent (INSTANT, CONSTANT, PERIODIC).
     *
     * @return List of decomposed unit events
     */
    public ArrayList<SimScenarioUnitEvent> readUnitEvents(){

        int numOfUnitEvents = 0;
        timestamp = new Timestamp(System.currentTimeMillis());

        if (startTime >= simTotalTime){
            System.out.println("[" + timestamp + "] (SimScenarioEvent: readUnitEvents) Non-executable event: id(" + id + ")");
            return null;
        } else{
            timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println("[" + timestamp + "] (SimScenarioEvent: readUnitEvents) Event decomposition (evnt_id(" + id + "))");

            ArrayList<SimScenarioUnitEvent> unitEventList = new ArrayList<>();

            int eventEndTime = 0;
            int eventEndTimeInPeriod = 0;
            int unitEventStartTime = startTime;
            int unitEventPeriodStartTime = startTime;

            int appendIdNum = 0;
            String appendIdString;
            String unitEventId;

            // Instant and constant event
            if (occPattern == EnumEventOccPattern.INSTANT || occPattern == EnumEventOccPattern.CONSTANT){

                if(occPattern == EnumEventOccPattern.INSTANT){
                    eventEndTime = unitEventStartTime + duration;
                }else if(occPattern == EnumEventOccPattern.CONSTANT){
                    eventEndTime = simTotalTime;
                }

                while(unitEventStartTime < eventEndTime && unitEventStartTime < endTime){
                    //TODO: debug
                    appendIdString = String.format("%03d", appendIdNum);
                    unitEventId = id + "_" + appendIdString;

                    SimScenarioUnitEvent unitEvent = new SimScenarioUnitEvent(  this,
                            unitEventId,
                            name,
                            eventType,
                            targetObjId,
                            targetObj,
                            targetScope,
                            predefBehavior,
                            unitEventStartTime,
                            isProbabilistic,
                            probDist,
                            probExp);

                    unitEventList.add(unitEvent);
                    unitEventStartTime++;
                    appendIdNum++;
                    numOfUnitEvents++;
                }
            }
            // periodic event
            else{
                int periodNum = 0;

                //TODO: periodic
//                for(int periodNum = 0; periodNum < simTotalTime; periodNum++){
                while(unitEventPeriodStartTime < simTotalTime && unitEventPeriodStartTime < endTime){

                    //For each period, eventEndTime is newly assigned.
                    eventEndTimeInPeriod = unitEventStartTime + duration;

                    while(unitEventStartTime < eventEndTimeInPeriod){
                        //TODO: debug
                        appendIdString = String.format("%03d", appendIdNum);
                        unitEventId = id + "_" + appendIdString;

                        SimScenarioUnitEvent unitEvent = new SimScenarioUnitEvent(  this,
                                unitEventId,
                                name,
                                eventType,
                                targetObjId,
                                targetObj,
                                targetScope,
                                predefBehavior,
                                unitEventStartTime,
                                isProbabilistic,
                                probDist,
                                probExp);

                        unitEventList.add(unitEvent);
                        unitEventStartTime++;
                        appendIdNum++;
                        numOfUnitEvents++;

                        if(unitEventStartTime >= simTotalTime || unitEventStartTime >= endTime){
                            break;
                        }
                    }

                    unitEventPeriodStartTime += period;
                    unitEventStartTime = unitEventPeriodStartTime;
                    periodNum++;
                }
            }

            timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println("[" + timestamp + "] (SimScenarioEvent: readUnitEvents) Event decomposition finished (numOfUnitEvents: " + numOfUnitEvents + ")");

            System.out.print("[" + timestamp + "] (SimScenarioEvent: readUnitEvents) ScenarioEvent decomposed (" + id + "): ");
            for (int numOfEvents = 0; numOfEvents < unitEventList.size(); numOfEvents++){
                System.out.print("(" + numOfEvents+ ")" + unitEventList.get(numOfEvents).getId() + "[" + unitEventList.get(numOfEvents).getStartTime() + "] ");
            }

            System.out.println();

            return unitEventList;
        }

    }


    /**
     * Behavior of event execution should be specified in this method.
     */
    public void executeEvent(){
        if (predefBehavior == EnumEventPredefBehavior.NOT_DETERMINED){
        }
    }

}
