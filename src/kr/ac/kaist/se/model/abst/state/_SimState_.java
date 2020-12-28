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
    protected boolean isInitial;    //true if it is an initial state
    protected int timeConstraint;   //time constraint

    /** State transition */
    //Possible states to go to
    protected ArrayList<_SimState_> nextStates = new ArrayList<>();

}
