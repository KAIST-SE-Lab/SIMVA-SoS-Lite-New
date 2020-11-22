package kr.ac.kaist.se.simdata.input;

import kr.ac.kaist.se.simdata.evnt.SimScenarioEvent;
import kr.ac.kaist.se.simdata.evnt.SimScenarioUnitEvent;

import java.util.ArrayList;

public class SimScenario {

    protected String scenarioName = "noname";

    protected boolean isDefaultScenario = false;
    protected String simScenarioFilePath;

    protected int numOfEvents = 0;
    protected int numOfUnitEvents = 0;

    protected ArrayList<SimScenarioEvent> eventList = new ArrayList<>();
    protected ArrayList<SimScenarioUnitEvent> unitEventList = new ArrayList<>();

    public SimScenario(){
    }

    public SimScenario(String scenarioName, boolean isDefaultScenario){
        this.scenarioName = scenarioName;
        this.isDefaultScenario = isDefaultScenario;
    }

//    public SimScenario(String simScenarioFilePath) {
//        this.simScenarioFilePath = simScenarioFilePath;
//    }

    public void addSimScenarioEvent(SimScenarioEvent simScenarioEvent){
        //Add simScenarioEvent
        eventList.add(simScenarioEvent);
        numOfEvents = eventList.size();

        //Add unit events (i.e., decomposed events) of the given SimScenarioEvent
        unitEventList.addAll(simScenarioEvent.readUnitEvents());
        numOfUnitEvents = unitEventList.size();
    }

    public String getScenarioName() {
        return scenarioName;
    }

    public void setScenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
    }

    public boolean isDefaultScenario() {
        return isDefaultScenario;
    }

    public void setDefaultScenario(boolean defaultScenario) {
        isDefaultScenario = defaultScenario;
    }

    public String getSimScenarioFilePath() {
        return simScenarioFilePath;
    }

    public void setSimScenarioFilePath(String simScenarioFilePath) {
        this.simScenarioFilePath = simScenarioFilePath;
    }

    public int getNumOfEvents() {
        return numOfEvents;
    }

    public void setNumOfEvents(int numOfEvents) {
        this.numOfEvents = numOfEvents;
    }

    public int getNumOfUnitEvents() {
        return numOfUnitEvents;
    }

    public void setNumOfUnitEvents(int numOfUnitEvents) {
        this.numOfUnitEvents = numOfUnitEvents;
    }

    public ArrayList<SimScenarioEvent> getEventList() {
        return eventList;
    }

    public void setEventList(ArrayList<SimScenarioEvent> eventList) {
        this.eventList = eventList;
    }

    public ArrayList<SimScenarioUnitEvent> getUnitEventList() {
        return unitEventList;
    }

    public void setUnitEventList(ArrayList<SimScenarioUnitEvent> unitEventList) {
        this.unitEventList = unitEventList;
    }
}
