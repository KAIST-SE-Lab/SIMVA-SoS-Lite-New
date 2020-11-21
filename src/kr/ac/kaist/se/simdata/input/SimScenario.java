package kr.ac.kaist.se.simdata.input;

import kr.ac.kaist.se.simdata.evnt.SimScenarioEvent;
import kr.ac.kaist.se.simdata.evnt.SimScenarioUnitEvent;

import java.util.ArrayList;

public class SimScenario {

    protected boolean isDefaultScenario = false;
    protected String simScenarioFilePath;

    protected ArrayList<SimScenarioEvent> eventList = new ArrayList<>();
    protected ArrayList<SimScenarioUnitEvent> unitEventList = new ArrayList<>();

    public SimScenario(){
    }

    public SimScenario(String simScenarioFilePath) {
        this.simScenarioFilePath = simScenarioFilePath;
    }

    public void addSimScenarioEvent(SimScenarioEvent simScenarioEvent){
        //Add simScenarioEvent
        eventList.add(simScenarioEvent);

        //Add unit events (i.e., decomposed events) of the given SimScenarioEvent
        unitEventList.addAll(simScenarioEvent.readUnitEvents());
    }
}
