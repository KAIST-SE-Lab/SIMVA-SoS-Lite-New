package kr.ac.kaist.se.model.abst.state;

import java.util.ArrayList;

/**
 * Abstract class to represent a state of a SimObject
 *
 * @author ymbaek
 */
public abstract class _SimState_ {

    /** Basic information of a state */
    protected String id;        //id of a state
    protected String name;      //name of a state
    protected String tag;       //tag of a state

    /** State properties */
    protected boolean isInitial = false;    //true if it is an initial state
    protected int timeConstraint = -1;   //time constraint

    /** State transition */
    //Possible states to go to
    protected ArrayList<_SimState_> nextStates = new ArrayList<>();


    public _SimState_(String id, String name) {
        this.id = id;
        this.name = name;

        //Automatically set its tag with its name
        this.tag = this.name;
    }

    public _SimState_(String id, String name, String tag, boolean isInitial) {
        this.id = id;
        this.name = name;
        this.tag = tag;
        this.isInitial = isInitial;
    }

    public _SimState_(String id, String name, String tag, boolean isInitial, int timeConstraint) {
        this.id = id;
        this.name = name;
        this.tag = tag;
        this.isInitial = isInitial;
        this.timeConstraint = timeConstraint;
    }


    /**
     * Add a state as a next state
     * @param aState A state to be added into the list of nextStates
     */
    public void addNextState(_SimState_ aState){
        boolean isDuplicate = false;
        for (_SimState_ state : nextStates){
            if (aState.getId().equals(state.getId())){
                isDuplicate = true;
            }
        }

        if (aState != null && isDuplicate == false){
            nextStates.add(aState);
        }
    }

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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isInitial() {
        return isInitial;
    }

    public void setInitial(boolean initial) {
        isInitial = initial;
    }

    public int getTimeConstraint() {
        return timeConstraint;
    }

    public void setTimeConstraint(int timeConstraint) {
        this.timeConstraint = timeConstraint;
    }

    public ArrayList<_SimState_> getNextStates() {
        return nextStates;
    }

    public void setNextStates(ArrayList<_SimState_> nextStates) {
        this.nextStates = nextStates;
    }
}
