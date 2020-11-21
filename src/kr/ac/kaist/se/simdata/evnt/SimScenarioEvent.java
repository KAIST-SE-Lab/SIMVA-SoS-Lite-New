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

    Timestamp timestamp;
    int simTotalTime = 0;

    /* Target information */
    protected String targetObjId;       //Id of target object
    protected _SimObject_ targetObj;    //Target object

    protected EnumEventTargetScope targetScope; //Scope of target

    /* Behavioral information */
    protected EnumEventPredefBehavior predefBehavior;   //Predefined behavior of an event

    /* Temporal information */
    protected EnumEventOccPattern occPattern;   //Occurrence pattern
    protected int startTime = 0;                    //Start time for all events
    protected int duration = 1;                     //Duration for an event that has duration
    protected int period = -1;                       //Period for periodic events

    /* Probability information */
    protected boolean isProbabilistic;      //Probabilistic event or not
    protected EnumEventProbDist probDist;   //Probability distribution
    protected String probExp;               //Expression of probability (this should conform to probDist)

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
        this.duration = duration;
        this.period = period;
        this.isProbabilistic = isProbabilistic;
        this.probDist = probDist;
        this.probExp = probExp;
    }

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
            int unitEventStartTime = startTime;

            int appendIdNum = 0;
            String appendIdString;
            String unitEventId;

            // Instant event (single UnitEvent object)
            if (occPattern == EnumEventOccPattern.INSTANT || occPattern == EnumEventOccPattern.CONSTANT){

                if(occPattern == EnumEventOccPattern.INSTANT){
                    eventEndTime = unitEventStartTime + duration;
                }else if(occPattern == EnumEventOccPattern.CONSTANT){
                    eventEndTime = simTotalTime;
                }

                while(unitEventStartTime < eventEndTime){
                    //TODO: debug
                    appendIdString = String.format("%03d", appendIdNum);
                    unitEventId = id + "_" + appendIdString;

                    SimScenarioUnitEvent unitEvent = new SimScenarioUnitEvent(  unitEventId,
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
//                for(int evntDuration = 0; evntDuration < duration; evntDuration++){
//
//                    //TODO: debug
//                    appendIdString = String.format("%03d", appendIdNum);
//                    unitEventId = id + "_" + appendIdString;
//
//                    SimScenarioUnitEvent unitEvent = new SimScenarioUnitEvent(  unitEventId,
//                            name,
//                            eventType,
//                            targetObjId,
//                            targetObj,
//                            targetScope,
//                            predefBehavior,
//                            unitEventStartTime,
//                            isProbabilistic,
//                            probDist,
//                            probExp);
//
//                    unitEventList.add(unitEvent);
//                    unitEventStartTime++;
//                    appendIdNum++;
//                    numOfUnitEvents++;
//                }
            }
            // Constant or periodic event (multiple UnitEvent objects)
            else{
                //TODO: constant and periodic
                for(int periodNum = 0; periodNum < simTotalTime; periodNum++){

                }
            }

            timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println("[" + timestamp + "] (SimScenarioEvent: readUnitEvents) Event decomposition finished (numOfUnitEvents: " + numOfUnitEvents + ")");

            System.out.print("[" + timestamp + "] ");
            for (int numOfEvents = 0; numOfEvents < unitEventList.size(); numOfEvents++){
                System.out.print("(" + numOfEvents+ ")" + unitEventList.get(numOfEvents).getId() + "[" + unitEventList.get(numOfEvents).getStartTime() + "] ");
            }

            System.out.println();

            return unitEventList;
        }


    }



}
